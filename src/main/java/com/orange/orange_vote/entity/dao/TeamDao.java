package com.orange.orange_vote.entity.dao;

import com.orange.orange_vote.base.repo.BaseRepository;
import com.orange.orange_vote.entity.model.Team;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface TeamDao extends BaseRepository<Team, Integer> {

    @Query(value = "SELECT t FROM Team t WHERE t.status = true AND t.teamValue = ?1")
    Optional<Team> findTeamByTeamValue(String teamValue);

    @Query(value = "SELECT t FROM Team t WHERE t.status = true AND t.teamUuid = ?1")
    Optional<Team> findTeamByTeamUuid(String teamUuid);

    @Query(value = "SELECT COUNT(t) FROM Team t")
    Integer countTeams();

}
