package com.orange.orange_vote.entity.service;

import com.orange.orange_vote.entity.model.Function;
import java.util.List;

public interface FunctionService {

    List<Function> getFunctionsByModuleId(Integer moduleId);

}
