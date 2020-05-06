package com.orange.orange_vote.aspect;

import com.orange.orange_vote.base.aop.CreateUpdateFormAspect;
import com.orange.orange_vote.base.exception.EntityNotFoundException;
import com.orange.orange_vote.constans.TeamErrorConstantsEnums;
import com.orange.orange_vote.entity.model.Team;
import com.orange.orange_vote.entity.service.TeamService;
import com.orange.orange_vote.view.team.TeamCreateForm;
import com.orange.orange_vote.view.team.TeamUpdateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import java.util.Optional;

@Component
public class TeamFormAspect extends CreateUpdateFormAspect<TeamCreateForm, TeamUpdateForm> {

    @Autowired
    private TeamService teamService;

    @Override
    protected void putAuthenticate(TeamCreateForm form, BindingResult errors) throws EntityNotFoundException {
        Optional<Team> teamOptional = teamService.getTeamByTeamValue(form.getTeamValue());
        if (teamOptional.isPresent()) {
            errors.rejectValue("teamValue", TeamErrorConstantsEnums.TEAM_VALUE_DUPLICATE.valueOfCode(),
                TeamErrorConstantsEnums.TEAM_VALUE_DUPLICATE.valueOfName());
        }
    }

    @Override
    protected void postAuthenticate(TeamUpdateForm form, BindingResult errors) throws EntityNotFoundException {
        Team team = teamService.getAllTeamByTeamUuid(form.getTeamUuid()).orElse(null);
        if (team == null) {
            errors.rejectValue("teamUuid", TeamErrorConstantsEnums.TEAM_NOT_FOUND.valueOfCode(),
                TeamErrorConstantsEnums.TEAM_NOT_FOUND.valueOfName());
        } else {
            form.setTeam(team);
        }
    }
}
