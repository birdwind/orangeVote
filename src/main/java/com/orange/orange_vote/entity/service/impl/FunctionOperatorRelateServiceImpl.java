package com.orange.orange_vote.entity.service.impl;

import com.google.common.collect.Sets;
import com.orange.orange_vote.base.security.model.CheckAuthority;
import com.orange.orange_vote.entity.dao.FunctionOperatorRelateDao;
import com.orange.orange_vote.entity.model.FunctionOperatorRelate;
import com.orange.orange_vote.entity.service.FunctionOperatorRelateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Set;
import javax.transaction.Transactional;

@Service
@Transactional
public class FunctionOperatorRelateServiceImpl implements FunctionOperatorRelateService {

    @Autowired
    private FunctionOperatorRelateDao functionOperatorRelateDao;

    @Override
    public Set<FunctionOperatorRelate> getFunctionOperatorRelatesByFunctionIds(Collection<Integer> functionIds) {
        if (functionIds.isEmpty())
            return Sets.newHashSet();

        return CheckAuthority.isSuperAdmin()
            ? functionOperatorRelateDao.findAllFunctionOperatorRelatesByFunctionIds(functionIds)
                .orElse(Sets.newHashSet())
            : functionOperatorRelateDao.findFunctionOperatorRelatesByFunctionIds(functionIds).orElse(Sets.newHashSet());
    }

}
