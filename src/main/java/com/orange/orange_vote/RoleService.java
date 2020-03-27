package com.orange.orange_vote;

import com.orange.orange_vote.entity.model.Role;
import java.util.List;
import java.util.Optional;

public interface RoleService {

//    Optional<Role> getRoleByUuid(String roleUuid);

    List<Role> getRolesByMemberId(Integer memberId);
}
