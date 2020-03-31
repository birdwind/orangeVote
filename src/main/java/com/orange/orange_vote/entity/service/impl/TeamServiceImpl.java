package com.orange.orange_vote.entity.service.impl;

import com.orange.orange_vote.entity.dao.TeamDao;
import com.orange.orange_vote.entity.model.Team;
import com.orange.orange_vote.entity.service.TeamService;
import com.orange.orange_vote.enums.NumberEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import javax.transaction.Transactional;

@Service
@Transactional
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamDao teamDao;

    private AtomicInteger counter = new AtomicInteger(-1);

    @Override
    public String generateFunctionNo() {
        if (counter.get() == -1) {
            counter.set(teamDao.countTeams());
        }
        return NumberEnum.TEAM.valueOf() + counter.getAndIncrement();
    }

    @Override
    public Optional<Team> getTeamByTeamValue(String teamValue) {
        return teamDao.findTeamByTeamValue(teamValue);
    }

    @Override
    public Optional<Team> getTeamByTeamUuid(String teamUuid) {
        return teamDao.findTeamByTeamUuid(teamUuid);
    }

    @Override
    public Team saveTeam(Team team) {
        return teamDao.save(team);
    }

    @Override
    public Team updateTeam(Team team) {
        return teamDao.save(team);
    }
}
