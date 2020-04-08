package com.orange.orange_vote.entity.dao;

import com.orange.orange_vote.base.repo.BaseRepository;
import com.orange.orange_vote.entity.model.Role;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface RoleDao extends BaseRepository<Role, Integer> {

    @Query(value = "SELECT r FROM Role r JOIN MemberRoleRelate mmr ON r.roleId = mmr.role.roleId "
        + "WHERE mmr.member.memberId = ?1")
    List<Role> findRolesByMemberId(Integer memberId);

    @Query(value = "SELECT r FROM Role r WHERE r.status = true AND r.roleUuid = ?1")
    Optional<Role> findRoleByRoleUuid(String roleUuid);

    @Query(value = "SELECT r FROM Role r WHERE r.status = true")
    List<Role> findAllRoles();
}
