package com.orange.orange_vote.view.system.converter;

import com.orange.orange_vote.base.dto.mapper.provider.PrimitiveProvider;
import com.orange.orange_vote.base.dto.mapper.converter.abstracts.AbstractViewConverter;
import com.orange.orange_vote.entity.model.Member;
import com.orange.orange_vote.entity.service.ModuleService;
import com.orange.orange_vote.view.module.converter.ModuleViewConverter;
import com.orange.orange_vote.view.system.ModuleAuthView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModuleAuthViewConverter extends AbstractViewConverter<Member, ModuleAuthView> {

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private ModuleViewConverter moduleViewConverter;

    private PrimitiveProvider<Member> modulesProvider = (source, field) -> PrimitiveProvider
        .cast(moduleViewConverter.convert(moduleService.getModulesByMemberId(source.getMemberId())));

    @Override
    public ModuleAuthView convert(Member source) {
        addValueProvider("modules", modulesProvider);
        return complexMapping(source, ModuleAuthView.class);
    }

}
