package com.orange.orange_vote.aspect;

import com.orange.orange_vote.base.aop.AbstractAuthModelAspect;
import com.orange.orange_vote.base.exception.EntityNotFoundException;
import com.orange.orange_vote.base.security.model.SystemUser;
import com.orange.orange_vote.constans.TeamErrorConstants;
import com.orange.orange_vote.constans.VoteErrorConstants;
import com.orange.orange_vote.entity.model.Member;
import com.orange.orange_vote.entity.model.Team;
import com.orange.orange_vote.entity.model.Vote;
import com.orange.orange_vote.entity.service.TeamService;
import com.orange.orange_vote.entity.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeamModelAspect extends AbstractAuthModelAspect<Team> {

    @Autowired
    private TeamService teamService;

    @Override
    protected Team authenticate(String source) throws Throwable {
        return teamService.getTeamByTeamUuid(source).orElse(null);
    }
}
