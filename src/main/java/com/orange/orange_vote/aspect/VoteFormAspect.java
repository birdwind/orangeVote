package com.orange.orange_vote.aspect;

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
import com.orange.orange_vote.view.vote.VoteForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class VoteFormAspect extends CreateUpdateFormAspect<VoteForm, VoteForm> {

    @Autowired
    private VoteService voteService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private VoteOptionService voteOptionService;

    @Override
    protected void putAuthenticate(VoteForm form, BindingResult errors) throws EntityNotFoundException {
        checkTeamExist(form, errors);
    }

    @Override
    protected void postAuthenticate(VoteForm form, BindingResult errors) throws EntityNotFoundException {
        Member member = SystemUser.getMember();
        Vote vote =
            voteService.getVoteByVoteUuidAndCreatorId(form.getVoteUuid(), member.getMemberId()).orElseThrow(() -> {
                return new EntityNotFoundException("voteUuid", VoteErrorConstantsEnums.VOTE_NOT_FOUND.valueOfCode(),
                    VoteErrorConstantsEnums.VOTE_NOT_FOUND.valueOfName());
            });

        checkTeamExist(form, errors);

        AtomicInteger index = new AtomicInteger();
        if (form.getOptions() != null) {
            form.getOptions().forEach(voteOptionForm -> {
                int i = index.getAndIncrement();
                VoteOption voteOption = voteOptionService
                    .getVoteOptionByVoteOptionUuidAndVote(voteOptionForm.getOptionUuid(), vote).orElse(null);
                if (voteOption == null && voteOptionForm.getOptionUuid() != null) {
                    errors.rejectValue("options[" + i + "]", VoteErrorConstantsEnums.VOTEOPTION_NOT_FOUND.valueOfCode(),
                        VoteErrorConstantsEnums.VOTEOPTION_NOT_FOUND.valueOfName());
                } else {
                    voteOptionForm.setVoteOption(voteOption);
                }
            });
        }

        index.set(0);
        if (form.getDeleteOptions() != null) {
            form.getDeleteOptions().forEach(voteOptionDeleteForm -> {
                int i = index.getAndIncrement();
                VoteOption voteOption = voteOptionService
                    .getVoteOptionByVoteOptionUuidAndVote(voteOptionDeleteForm.getOptionUuid(), vote).orElse(null);
                if (voteOption == null) {
                    errors.rejectValue("deleteOptions[" + i + "]",
                        VoteErrorConstantsEnums.VOTEOPTION_NOT_FOUND.valueOfCode(),
                        VoteErrorConstantsEnums.VOTEOPTION_NOT_FOUND.valueOfName());
                } else {
                    voteOptionDeleteForm.setVoteOption(voteOption);
                }
            });
        }

        form.setVote(vote);

    }

    private void checkTeamExist(VoteForm form, BindingResult errors) {
        Team team = teamService.getTeamByTeamUuid(form.getTeamUuid()).orElse(null);
        if (team == null) {
            errors.rejectValue("teamUuid", TeamErrorConstantsEnums.TEAM_NOT_FOUND.valueOfCode(),
                TeamErrorConstantsEnums.TEAM_NOT_FOUND.valueOfName());
        } else {
            form.setTeam(team);
        }
    }
}
