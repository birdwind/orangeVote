package com.orange.orange_vote.entity.service;

import com.orange.orange_vote.entity.model.MemberRoleRelate;
import com.orange.orange_vote.entity.model.Role;
import java.util.List;
import java.util.Optional;

public interface RoleService {

    Optional<Role> getRoleByRoleUuid(String roleUuid);

    List<Role> getRolesByMemberId(Integer memberId);

    List<Role> getAllRoles();

    Optional<MemberRoleRelate> getMemberRoleRelateByRoleUuidAndMemberId(String roleUuid, Integer memberId);

}
