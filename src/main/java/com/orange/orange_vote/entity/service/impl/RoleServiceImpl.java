package com.orange.orange_vote.entity.service.impl;

import com.orange.orange_vote.entity.service.RoleService;
import com.orange.orange_vote.entity.dao.RoleDao;
import com.orange.orange_vote.entity.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import javax.transaction.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;
//
//    @Override
//    public Optional<Role> getRoleByUuid(String roleUuid) {
//        return Optional.empty();
//    }

    @Override
    public List<Role> getRolesByMemberId(Integer memberId) {
        return roleDao.findRolesByMemberId(memberId);
    }
}
