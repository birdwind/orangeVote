package com.orange.orange_vote.aspect;

import com.google.common.collect.Lists;
import com.orange.orange_vote.base.aop.UpdateFormAspect;
import com.orange.orange_vote.base.exception.EntityNotFoundException;
import com.orange.orange_vote.base.security.model.SystemUser;
import com.orange.orange_vote.constans.VoteErrorConstantsEnums;
import com.orange.orange_vote.entity.model.Member;
import com.orange.orange_vote.entity.model.Vote;
import com.orange.orange_vote.entity.model.VoteOption;
import com.orange.orange_vote.entity.service.MemberVoteOptionRelateService;
import com.orange.orange_vote.entity.service.VoteOptionService;
import com.orange.orange_vote.entity.service.VoteService;
import com.orange.orange_vote.view.voteOption.MemberVoteOptionRelateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MemberVoteOptionRelateFormAspect extends UpdateFormAspect<MemberVoteOptionRelateForm> {

    @Autowired
    private VoteService voteService;

    @Autowired
    private VoteOptionService voteOptionService;

    @Autowired
    private MemberVoteOptionRelateService memberVoteOptionRelateService;

    @Override
    protected void postAuthenticate(MemberVoteOptionRelateForm form, BindingResult errors)
        throws EntityNotFoundException {
        Member member = SystemUser.getMember();
        Vote vote =
            voteService.getVoteByVoteUuidAndMemberId(form.getVoteUuid(), member.getMemberId()).orElseThrow(() -> {
                return new EntityNotFoundException("voteUuid", VoteErrorConstantsEnums.VOTE_NOT_FOUND.valueOfCode(),
                    VoteErrorConstantsEnums.VOTE_NOT_FOUND.valueOfName());
            });

        // 檢查是否已投過票
        if (memberVoteOptionRelateService.isVoteAlready(vote.getVoteId(), member)) {
            errors.rejectValue("voteUuid", VoteErrorConstantsEnums.VOTE_ALREADY.valueOfCode(),
                VoteErrorConstantsEnums.VOTE_ALREADY.valueOfName());
            return;
        }

        if (!vote.getIsAllowAdd() && form.getAddOptions() != null) {
            errors.rejectValue("addOptions", VoteErrorConstantsEnums.VOTEOPTION_NOT_ALLOW_ADD.valueOfCode(),
                VoteErrorConstantsEnums.VOTEOPTION_NOT_ALLOW_ADD.valueOfName());
            return;
        }

        if (form.getAddOptions() == null) {
            form.setAddOptions(Lists.newArrayList());
        }

        if (form.getOptionUuids() == null) {
            form.setOptionUuids(Lists.newArrayList());
        }

        // 檢查是否選擇大於多選限制
        if (vote.getMultiSelection() < form.getOptionUuids().size() + form.getAddOptions().size()) {
            errors.rejectValue("optionUuids", VoteErrorConstantsEnums.VOTEOPTION_MULTISELECT.valueOfCode(),
                VoteErrorConstantsEnums.VOTEOPTION_MULTISELECT.valueOfName());
            if (form.getAddOptions() != null) {
                errors.rejectValue("addOptions", VoteErrorConstantsEnums.VOTEOPTION_MULTISELECT.valueOfCode(),
                    VoteErrorConstantsEnums.VOTEOPTION_MULTISELECT.valueOfName());
            }
            return;
        }

        List<VoteOption> voteOptions = Lists.newArrayList();

        if (form.getOptionUuids() != null) {
            AtomicInteger index = new AtomicInteger();
            form.getOptionUuids().forEach(optionUuid -> {
                int i = index.getAndIncrement();
                VoteOption voteOption =
                    voteOptionService.getVoteOptionByVoteOptionUuidAndVote(optionUuid, vote).orElse(null);
                if (voteOption == null) {
                    errors.rejectValue("optionUuids[" + i + "]",
                        VoteErrorConstantsEnums.VOTEOPTION_NOT_FOUND.valueOfCode(),
                        VoteErrorConstantsEnums.VOTEOPTION_NOT_FOUND.valueOfName());
                } else {
                    voteOptions.add(voteOption);
                }
            });
        }

        form.setVote(vote);

        form.setVoteOptions(voteOptions);
    }
}
