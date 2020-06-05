package com.orange.orange_vote.entity.dao;

import com.orange.orange_vote.base.repo.BaseRepository;
import com.orange.orange_vote.entity.model.Member;
import com.orange.orange_vote.entity.model.Team;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface TeamDao extends BaseRepository<Team, Integer> {

    @Query(value = "SELECT t FROM Team t WHERE t.status = true AND t.teamValue = ?1")
    Optional<Team> findTeamByTeamValue(String teamValue);

    @Query(value = "SELECT t FROM Team t WHERE t.status = true AND t.teamUuid = ?1")
    Optional<Team> findTeamByTeamUuid(String teamUuid);

    @Query(value = "SELECT t FROM Team t WHERE t.status = true")
    Optional<List<Team>> findAllTeams();

    @Query(value = "SELECT t FROM Team t JOIN MemberTeamRelate mtr ON mtr.team = t AND mtr.member = ?1 " +
            "WHERE t.status = true AND t.teamId = ?2")
    Optional<Team> findTeamJoinedByMemberAndTeamId(Member member, int teamId);

    @Query(value = "SELECT COUNT(t) FROM Team t")
    Integer countTeams();

    @Query(value = "SELECT t FROM Team t WHERE t.status = true AND t.passCode = ?1 AND t.teamUuid = ?2")
    Optional<Team> findTeamByPassCode(String passCode, String teamUuid);

    @Query(value = "SELECT t FROM Team t WHERE t.teamUuid = ?1")
    Optional<Team> findAllTeamByTeamUuid(String teamUuid);

}
