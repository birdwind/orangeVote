package com.orange.orange_vote.entity.service;

import com.orange.orange_vote.entity.model.MemberRoleRelate;
import java.util.Optional;

public interface MemberRoleRelateService {

    Optional<MemberRoleRelate> getMemberRoleRelateByRoleUuidAndMemberId(String roleUuid, Integer memberId);

}
