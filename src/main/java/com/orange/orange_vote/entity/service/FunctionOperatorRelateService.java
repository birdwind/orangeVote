package com.orange.orange_vote.entity.service;

import com.orange.orange_vote.entity.model.FunctionOperatorRelate;
import java.util.Collection;
import java.util.Set;

public interface FunctionOperatorRelateService {

    Set<FunctionOperatorRelate> getFunctionOperatorRelatesByFunctionIds(Collection<Integer> functionIds);

}
