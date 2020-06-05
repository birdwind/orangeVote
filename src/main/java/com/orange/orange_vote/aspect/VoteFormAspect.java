package com.orange.orange_vote.aspect;

import com.google.common.collect.Lists;
import com.orange.orange_vote.base.aop.CreateUpdateFormAspect;
import com.orange.orange_vote.base.exception.EntityNotFoundException;
import com.orange.orange_vote.base.security.model.SystemUser;
import com.orange.orange_vote.constans.TeamErrorConstantsEnums;
import com.orange.orange_vote.constans.VoteErrorConstantsEnums;
import com.orange.orange_vote.entity.model.Member;
import com.orange.orange_vote.entity.model.Team;
import com.orange.orange_vote.entity.model.Vote;
import com.orange.orange_vote.entity.model.VoteOption;
import com.orange.orange_vote.entity.service.TeamService;
import com.orange.orange_vote.entity.service.VoteOptionService;
import com.orange.orange_vote.entity.service.VoteService;
import com.orange.orange_vote.view.vote.VoteCreateForm;
import com.orange.orange_vote.view.vote.VoteUpdateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class VoteFormAspect extends CreateUpdateFormAspect<VoteCreateForm, VoteUpdateForm> {

    @Autowired
    private VoteService voteService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private VoteOptionService voteOptionService;

    @Override
    protected void putAuthenticate(VoteCreateForm form, BindingResult errors) throws EntityNotFoundException {
        form.setTeam(checkTeamExist(form.getTeamUuid(), errors));
    }

    @Override
    protected void postAuthenticate(VoteUpdateForm form, BindingResult errors) throws EntityNotFoundException {
        Member member = SystemUser.getMember();
        List<VoteOption> deleteVoteOptionList = Lists.newArrayList();
        List<VoteOption> updateVoteOptionList = Lists.newArrayList();

        Vote vote =
            voteService.getVoteByVoteUuidAndCreatorId(form.getVoteUuid(), member.getMemberId()).orElseThrow(() -> {
                return new EntityNotFoundException("voteUuid", VoteErrorConstantsEnums.VOTE_NOT_FOUND.valueOfCode(),
                    VoteErrorConstantsEnums.VOTE_NOT_FOUND.valueOfName());
            });

        form.setTeam(checkTeamExist(form.getTeamUuid(), errors));

        AtomicInteger index = new AtomicInteger();
        if (form.getUpdateOptionUuids() != null) {
            form.getUpdateOptionUuids().forEach(optionUuid -> {
                int i = index.getAndIncrement();
                String voteOptionValue = form.getUpdateOptionValues().get(i);
                VoteOption voteOption =
                    voteOptionService.getVoteOptionByVoteOptionUuidAndVote(optionUuid, vote).orElse(null);
                if (voteOption == null) {
                    errors.rejectValue("updateOptionUuids[" + i + "]",
                        VoteErrorConstantsEnums.VOTEOPTION_NOT_FOUND.valueOfCode(),
                        VoteErrorConstantsEnums.VOTEOPTION_NOT_FOUND.valueOfName());
                } else if (voteOptionValue == null) {
                    errors.rejectValue("updateOptionUuids[" + i + "]",
                        VoteErrorConstantsEnums.VOTEOPTION_UPDATE_VALUE_NOTSET.valueOfCode(),
                        VoteErrorConstantsEnums.VOTEOPTION_UPDATE_VALUE_NOTSET.valueOfName());
                } else if (voteOption.getMemberVoteOptionRelates().size() > 0) {
                    errors.rejectValue("updateOptionUuids[" + i + "]",
                        VoteErrorConstantsEnums.VOTEOPTION_UPDATE_FAILED.valueOfCode(),
                        VoteErrorConstantsEnums.VOTEOPTION_UPDATE_FAILED.valueOfName());
                } else {
                    voteOption.setVoteOptionValue(voteOptionValue);
                    updateVoteOptionList.add(voteOption);
                }
            });
        }
        form.setUpdateVoteOptionList(updateVoteOptionList);

        index.set(0);
        if (form.getDeleteOptionUuids() != null) {
            form.getDeleteOptionUuids().forEach(deleteOptionUuids -> {
                int i = index.getAndIncrement();
                VoteOption voteOption =
                    voteOptionService.getVoteOptionByVoteOptionUuidAndVote(deleteOptionUuids, vote).orElse(null);
                if (voteOption == null) {
                    errors.rejectValue("deleteOptionUuids[" + i + "]",
                        VoteErrorConstantsEnums.VOTEOPTION_NOT_FOUND.valueOfCode(),
                        VoteErrorConstantsEnums.VOTEOPTION_NOT_FOUND.valueOfName());
                } else if(voteOption.getMemberVoteOptionRelates().size() > 0){
                    errors.rejectValue("deleteOptionUuids[" + i + "]",
                            VoteErrorConstantsEnums.VOTEOPTION_UPDATE_FAILED.valueOfCode(),
                            VoteErrorConstantsEnums.VOTEOPTION_UPDATE_FAILED.valueOfName());
                }else {
                    deleteVoteOptionList.add(voteOption);
                }
            });
        }
        form.setDeleteVoteOptionList(deleteVoteOptionList);

        form.setVote(vote);
    }

    private Team checkTeamExist(String teamUuid, BindingResult errors) {
        Team team = teamService.getTeamByTeamUuid(teamUuid).orElse(null);
        if (team == null) {
            errors.rejectValue("teamUuid", TeamErrorConstantsEnums.TEAM_NOT_FOUND.valueOfCode(),
                TeamErrorConstantsEnums.TEAM_NOT_FOUND.valueOfName());
            return null;
        } else {
            return team;
        }
    }
}
