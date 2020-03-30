package com.orange.orange_vote.entity.service;

import com.orange.orange_vote.entity.model.Module;
import java.util.List;

public interface ModuleService {

    List<Module> getModulesByMemberId(Integer memberId);

}
