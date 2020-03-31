package com.orange.orange_vote.aspect;

import com.orange.orange_vote.base.aop.CreateUpdateFormAspect;
import com.orange.orange_vote.base.exception.EntityNotFoundException;
import com.orange.orange_vote.constans.TeamErrorConstants;
import com.orange.orange_vote.entity.model.Team;
import com.orange.orange_vote.entity.service.TeamService;
import com.orange.orange_vote.view.team.TeamForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import java.util.Optional;

@Component
public class TeamFormAspect extends CreateUpdateFormAspect<TeamForm, TeamForm> {

    @Autowired
    private TeamService teamService;

    @Override
    protected void putAuthenticate(TeamForm form, BindingResult errors) throws EntityNotFoundException {
        Optional<Team> teamOptional = teamService.getTeamByTeamValue(form.getTeamValue());
        if (teamOptional.isPresent()) {
            errors.rejectValue("teamValue", TeamErrorConstants.TEAM_VALUE_DUPLICATE);
        }
    }

    @Override
    protected void postAuthenticate(TeamForm form, BindingResult errors) throws EntityNotFoundException {
        Team team = teamService.getTeamByTeamUuid(form.getTeamUuid()).orElse(null);
        if (team == null) {
            errors.rejectValue("teamUuid", TeamErrorConstants.TEAM_NOT_FOUND);
        }else{
            form.setTeam(team);
        }
    }
}
