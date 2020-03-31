package com.orange.orange_vote.entity.service;

import com.orange.orange_vote.entity.model.FunctionOperatorRelate;
import java.util.Collection;
import java.util.List;

public interface FunctionOperatorRelateService {

    List<FunctionOperatorRelate> getFunctionOperatorRelatesByFunctionIds(Collection<Integer> functionIds);

}
