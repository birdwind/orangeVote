package com.orange.orange_vote.entity.service;

import com.orange.orange_vote.entity.model.Team;
import java.util.Optional;

public interface TeamService {

    String generateFunctionNo();

    Optional<Team> getTeamByTeamValue(String teamValue);

    Optional<Team> getTeamByTeamUuid(String teamUuid);

    Team saveTeam(Team team);

    Team updateTeam(Team team);

}
