package com.orange.orange_vote.entity.service;

import com.orange.orange_vote.entity.model.Member;
import com.orange.orange_vote.entity.model.Team;
import java.util.List;
import java.util.Optional;

public interface TeamService {

    String generateTeamNo();

    Optional<Team> getTeamByTeamValue(String teamValue);

    Optional<Team> getTeamByTeamUuid(String teamUuid);

    Optional<Team> getAllTeamByTeamUuid(String teamUuid);

    List<Team> getAllTeams();

    Optional<Team> checkTeamJoinedByMemberAndTeamId(Member member, int teamId);

    Team saveTeam(Team team);

    Team updateTeam(Team team);

    Team getTeamByPassCode(String passCode, String teamUuid);

    Team joinTeam(Team team);

}
