package com.orange.orange_vote.view.team.converter;

import com.orange.orange_vote.base.dto.mapper.converter.abstracts.AbstractFormConverter;
import com.orange.orange_vote.base.security.model.SystemUser;
import com.orange.orange_vote.entity.model.Team;
import com.orange.orange_vote.entity.service.TeamService;
import com.orange.orange_vote.view.team.TeamCreateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeamCreateFormConverter extends AbstractFormConverter<TeamCreateForm, Team> {

    @Autowired
    private TeamService teamService;

    @Override
    public Team convert(TeamCreateForm source) {
        return simpleMapping(source, new Team(teamService.generateTeamNo(), SystemUser.getMember()));
    }
}
