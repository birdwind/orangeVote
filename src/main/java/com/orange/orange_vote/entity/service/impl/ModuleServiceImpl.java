package com.orange.orange_vote.entity.service.impl;

import com.google.common.collect.Lists;
import com.orange.orange_vote.base.security.model.CheckAuthority;
import com.orange.orange_vote.entity.dao.ModuleDao;
import com.orange.orange_vote.entity.model.Module;
import com.orange.orange_vote.entity.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import javax.transaction.Transactional;

@Service
@Transactional
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    private ModuleDao moduleDao;

    @Override
    public List<Module> getModulesByMemberId(Integer memberId) {
        if (CheckAuthority.isSuperAdmin()) {
            return moduleDao.findAllModules().orElse(Lists.newArrayList());
        }

        return moduleDao.findModulesByMemberCoreId(memberId).orElse(Lists.newArrayList());
    }
}
