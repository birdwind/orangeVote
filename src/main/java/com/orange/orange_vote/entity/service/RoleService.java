package com.orange.orange_vote.entity.service;

import com.orange.orange_vote.entity.model.Role;
import java.util.List;

public interface RoleService {

//    Optional<Role> getRoleByUuid(String roleUuid);

    List<Role> getRolesByMemberId(Integer memberId);
}
