package com.orange.orange_vote.entity.service.impl;

import com.orange.orange_vote.entity.dao.MemberRoleRelateDao;
import com.orange.orange_vote.entity.dao.RoleDao;
import com.orange.orange_vote.entity.model.MemberRoleRelate;
import com.orange.orange_vote.entity.model.Role;
import com.orange.orange_vote.entity.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private MemberRoleRelateDao memberRoleRelateDao;

    @Override
    public Optional<Role> getRoleByRoleUuid(String roleUuid) {
        return roleDao.findRoleByRoleUuid(roleUuid);
    }

    @Override
    public List<Role> getRolesByMemberId(Integer memberId) {
        return roleDao.findRolesByMemberId(memberId);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDao.findAllRoles();
    }

    @Override
    public Optional<MemberRoleRelate> getMemberRoleRelateByRoleUuidAndMemberId(String roleUuid, Integer memberId) {
        return memberRoleRelateDao.findMemberRoleRelateByRoleUuidAndMemberId(roleUuid, memberId);
    }
}
