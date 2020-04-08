package com.orange.orange_vote.view.team.converter;

import com.orange.orange_vote.base.dto.mapper.converter.abstracts.AbstractFormConverter;
import com.orange.orange_vote.base.security.model.SystemUser;
import com.orange.orange_vote.entity.model.Team;
import com.orange.orange_vote.entity.service.TeamService;
import com.orange.orange_vote.view.team.TeamForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.UUID;

@Component
public class TeamFormConverter extends AbstractFormConverter<TeamForm, Team> {

    @Autowired
    private TeamService teamService;

    @Override
    public Team convert(TeamForm source) {
        Team target;
        if (source.getTeam() == null) {
            target = simpleMapping(source, Team.class);
            target.setTeamUuid(UUID.randomUUID().toString());
            target.setTeamNo(teamService.generateFunctionNo());
            target.setCreator(SystemUser.getMember());
            target.setStatus(true);
        }else{
            target = source.getTeam();
            target.setPassCode(source.getVerification());
            target.setUpdateDate(new Date());
        }
        return target;
    }

    public Team convert(TeamForm source, Boolean status) {
        Team target = convert(source);
        target.setStatus(status);
        return target;
    }

}
