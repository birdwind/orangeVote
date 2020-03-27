package com.orange.orange_vote.entity.dao;

import com.orange.orange_vote.base.repo.BaseRepository;
import com.orange.orange_vote.entity.model.Member;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface MemberDao extends BaseRepository<Member, Integer> {

    @Query(value = "SELECT m FROM Member m WHERE m.username = ?1")
    Optional<Member> findMemberCoreByUsername(String username);

    @Modifying
    @Query(value = "UPDATE Member m set m.session = ?1 WHERE m.status = true AND m.memberId = ?2")
    void updateSession(String session, Integer memberId);
}
