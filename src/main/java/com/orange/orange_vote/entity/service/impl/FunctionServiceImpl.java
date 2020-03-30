package com.orange.orange_vote.entity.service.impl;

import com.google.common.collect.Lists;
import com.orange.orange_vote.base.security.model.CheckAuthority;
import com.orange.orange_vote.entity.dao.FunctionDao;
import com.orange.orange_vote.entity.model.Function;
import com.orange.orange_vote.entity.service.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;

@Service
@Transactional
public class FunctionServiceImpl implements FunctionService {

    @Autowired
    private FunctionDao functionDao;

    @Override
    public List<Function> getFunctionsByModuleId(Integer moduleId) {
        return CheckAuthority.isSuperAdmin()
                ? functionDao.findAllFunctionsByModuleId(moduleId).orElse(Lists.newArrayList())
                : functionDao.findFunctionsByModuleId(moduleId).orElse(Lists.newArrayList());
    }
}
