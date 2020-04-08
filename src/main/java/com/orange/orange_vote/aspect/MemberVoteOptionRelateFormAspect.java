package com.orange.orange_vote.aspect;

import com.google.common.collect.Lists;
import com.orange.orange_vote.base.aop.CreateFormAspect;
import com.orange.orange_vote.base.aop.UpdateFormAspect;
import com.orange.orange_vote.base.exception.EntityNotFoundException;
import com.orange.orange_vote.constans.VoteErrorConstants;
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
        Vote vote = voteService.getVotesByVoteUuid(form.getVoteUuid()).orElseThrow(() -> {
            return new EntityNotFoundException("voteUuid", VoteErrorConstants.VOTEOPTION_NOT_FOUND);
        });

        //檢查是否已投過票
        if(memberVoteOptionRelateService.isVoteAlready(vote.getVoteId())){
            errors.rejectValue("voteUuid", VoteErrorConstants.VOTE_ALREADY);
            return;
        }

        //檢查是否選擇大於多選限制
        if(vote.getMultiSelection() < form.getOptionUuids().size()) {
            errors.rejectValue("optionUuids", VoteErrorConstants.VOTEOPTION_MULTISELECT);
            return;
        }

        List<VoteOption> voteOptions = Lists.newArrayList();

        AtomicInteger index = new AtomicInteger();
        form.getOptionUuids().forEach(optionUuid -> {
            int i = index.getAndIncrement();
            VoteOption voteOption = voteOptionService
                    .getVoteOptionByVoteOptionUuidAndVote(optionUuid, vote).orElse(null);
            if (voteOption == null) {
                errors.rejectValue("optionUuids[" + i + "]", VoteErrorConstants.VOTEOPTION_NOT_FOUND);
            } else {
                voteOptions.add(voteOption);
            }
        });

        form.setVoteOptions(voteOptions);
    }
}
