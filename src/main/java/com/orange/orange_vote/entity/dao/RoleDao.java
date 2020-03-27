package com.orange.orange_vote.entity.dao;

import com.orange.orange_vote.base.repo.BaseRepository;
import com.orange.orange_vote.entity.model.Role;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface RoleDao extends BaseRepository<Role, Integer> {

    @Query(value = "SELECT r FROM Role r JOIN MemberRoleRelate mmr ON r.roleId = mmr.role.roleId " +
            "WHERE mmr.member.memberId = ?1")
    List<Role> findRolesByMemberId(Integer memberId);

}
