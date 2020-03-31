package com.orange.orange_vote.view.team.converter;

import com.orange.orange_vote.base.dto.mapper.converter.abstracts.AbstractViewConverter;
import com.orange.orange_vote.entity.model.Team;
import com.orange.orange_vote.view.team.TeamView;
import org.springframework.stereotype.Component;

@Component
public class TeamViewConverter extends AbstractViewConverter<Team, TeamView> {
    @Override
    public TeamView convert(Team source) {
        return complexMapping(source, TeamView.class);
    }
}
