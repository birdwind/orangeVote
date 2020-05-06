package com.orange.orange_vote.entity.service.impl;

import com.google.common.collect.Lists;
import com.orange.orange_vote.base.security.model.SystemUser;
import com.orange.orange_vote.entity.dao.MemberTeamRelateDao;
import com.orange.orange_vote.entity.dao.TeamDao;
import com.orange.orange_vote.entity.model.Member;
import com.orange.orange_vote.entity.model.MemberTeamRelate;
import com.orange.orange_vote.entity.model.Team;
import com.orange.orange_vote.entity.service.TeamService;
import com.orange.orange_vote.enums.NumberEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import javax.transaction.Transactional;

@Service
@Transactional
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamDao teamDao;

    @Autowired
    private MemberTeamRelateDao memberTeamRelateDao;

    private AtomicInteger counter = new AtomicInteger(-1);

    @Override
    public String generateTeamNo() {
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
    public Optional<Team> getAllTeamByTeamUuid(String teamUuid) {
        return teamDao.findAllTeamByTeamUuid(teamUuid);
    }

    @Override
    public List<Team> getAllTeam() {
        Member member = SystemUser.getMember();
        return teamDao.findAllTeams(member).orElse(Lists.newArrayList());
    }

    @Override
    public Team saveTeam(Team team) {
        return teamDao.save(team);
    }

    @Override
    public Team updateTeam(Team team) {
        return teamDao.save(team);
    }

    @Override
    public Team getTeamByPassCode(String passCode, String teamUuid) {
        return teamDao.findTeamByPassCode(passCode, teamUuid).orElse(null);
    }

    @Override
    public Team joinTeam(Team team) {
        if (team == null) {
            return null;
        }
        Member member = SystemUser.getMember();
        MemberTeamRelate memberTeamRelate =
            memberTeamRelateDao.checkExistRelate(member.getMemberId(), team.getTeamId());
        return memberTeamRelate != null ? memberTeamRelate.getTeam()
            : memberTeamRelateDao.save(new MemberTeamRelate(member, team)).getTeam();
    }
}
