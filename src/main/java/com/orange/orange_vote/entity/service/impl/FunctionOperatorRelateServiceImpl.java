package com.orange.orange_vote.entity.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.orange.orange_vote.base.security.model.CheckAuthority;
import com.orange.orange_vote.entity.dao.FunctionOperatorRelateDao;
import com.orange.orange_vote.entity.model.FunctionOperatorRelate;
import com.orange.orange_vote.entity.service.FunctionOperatorRelateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;

@Service
@Transactional
public class FunctionOperatorRelateServiceImpl implements FunctionOperatorRelateService {

    @Autowired
    private FunctionOperatorRelateDao functionOperatorRelateDao;

    @Override
    public List<FunctionOperatorRelate> getFunctionOperatorRelatesByFunctionIds(Collection<Integer> functionIds) {
        if (functionIds.isEmpty())
            return Lists.newArrayList();

        return CheckAuthority.isSuperAdmin()
            ? functionOperatorRelateDao.findAllFunctionOperatorRelatesByFunctionIds(functionIds)
                .orElse(Lists.newArrayList())
            : functionOperatorRelateDao.findFunctionOperatorRelatesByFunctionIds(functionIds).orElse(Lists.newArrayList());
    }

}
