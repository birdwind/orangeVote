package com.orange.orange_vote.view.team.converter;

import com.orange.orange_vote.base.dto.mapper.converter.abstracts.AbstractFormConverter;
import com.orange.orange_vote.entity.model.Team;
import com.orange.orange_vote.entity.service.TeamService;
import com.orange.orange_vote.view.team.TeamUpdateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class TeamFormConverter extends AbstractFormConverter<TeamUpdateForm, Team> {

    @Override
    public Team convert(TeamUpdateForm source) {
        Team target = simpleMapping(source, source.getTeam());
        target.setUpdateDate(new Date());
        return target;
    }
}
