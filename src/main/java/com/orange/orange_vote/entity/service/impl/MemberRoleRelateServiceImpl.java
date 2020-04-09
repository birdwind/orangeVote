package com.orange.orange_vote.entity.service.impl;

import com.orange.orange_vote.entity.dao.MemberRoleRelateDao;
import com.orange.orange_vote.entity.model.MemberRoleRelate;
import com.orange.orange_vote.entity.service.MemberRoleRelateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import javax.transaction.Transactional;

@Service
@Transactional
public class MemberRoleRelateServiceImpl implements MemberRoleRelateService {

    @Autowired
    private MemberRoleRelateDao memberRoleRelateDao;

    @Override
    public Optional<MemberRoleRelate> getMemberRoleRelateByRoleUuidAndMemberId(String roleUuid, Integer memberId) {
        return memberRoleRelateDao.findMemberRoleRelateByRoleUuidAndMemberId(roleUuid, memberId);
    }
}
