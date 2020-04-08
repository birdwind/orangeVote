package com.orange.orange_vote.aspect;

import com.orange.orange_vote.base.aop.CreateUpdateFormAspect;
import com.orange.orange_vote.base.exception.EntityNotFoundException;
import com.orange.orange_vote.constans.TeamErrorConstants;
import com.orange.orange_vote.constans.VoteErrorConstants;
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
        Vote vote = voteService.getVotesByVoteUuid(form.getVoteUuid()).orElseThrow(() -> {
            return new EntityNotFoundException("voteUuid", VoteErrorConstants.VOTEOPTION_NOT_FOUND);
        });

        checkTeamExist(form, errors);

        AtomicInteger index = new AtomicInteger();
        form.getOptions().forEach(voteOptionForm -> {
            int i = index.getAndIncrement();
            VoteOption voteOption = voteOptionService
                .getVoteOptionByVoteOptionUuidAndVote(voteOptionForm.getOptionUuid(), vote).orElse(null);
            if (voteOption == null) {
                errors.rejectValue("optionUuid[" + i + "]", VoteErrorConstants.VOTEOPTION_NOT_FOUND);
            } else {
                voteOptionForm.setVoteOption(voteOption);
            }
        });
        index.set(0);
        form.getDeleteOptions().forEach(voteOptionDeleteForm -> {
            int i = index.getAndIncrement();
            VoteOption voteOption = voteOptionService
                .getVoteOptionByVoteOptionUuidAndVote(voteOptionDeleteForm.getOptionUuid(), vote).orElse(null);
            if (voteOption == null) {
                errors.rejectValue("optionUuid[" + i + "]", VoteErrorConstants.VOTEOPTION_NOT_FOUND);
            } else {
                voteOptionDeleteForm.setVoteOption(voteOption);
            }
        });
    }

    private void checkTeamExist(VoteForm form, BindingResult errors) {
        Team team = teamService.getTeamByTeamUuid(form.getTeamUuid()).orElse(null);
        if (team == null) {
            errors.rejectValue("teamUuid", TeamErrorConstants.TEAM_NOT_FOUND);
        } else {
            form.setTeam(team);
        }
    }
}
