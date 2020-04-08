package com.orange.orange_vote.view.team.converter;

import com.orange.orange_vote.base.dto.mapper.converter.abstracts.AbstractListConverter;
import com.orange.orange_vote.entity.model.Team;
import com.orange.orange_vote.view.team.TeamListItem;
import org.springframework.stereotype.Component;
import java.io.Serializable;

@Component
public class TeamListItemConverter extends AbstractListConverter<Team, TeamListItem> {

    @Override
    public Serializable getText(Team source) {
        return source.getTeamValue();
    }

    @Override
    public Serializable getValue(Team source) {
        return source.getTeamUuid();
    }

    @Override
    public void setOtherProperty(TeamListItem item, Team source) {

    }
}
