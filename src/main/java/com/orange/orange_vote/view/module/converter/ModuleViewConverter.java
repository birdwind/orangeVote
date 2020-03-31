package com.orange.orange_vote.view.module.converter;

import com.orange.orange_vote.base.dto.mapper.provider.PrimitiveProvider;
import com.orange.orange_vote.base.utils.LocaleI18nUtils;
import com.orange.orange_vote.base.dto.mapper.converter.abstracts.AbstractViewConverter;
import com.orange.orange_vote.entity.model.Module;
import com.orange.orange_vote.entity.service.FunctionService;
import com.orange.orange_vote.view.function.converter.FunctionViewConverter;
import com.orange.orange_vote.view.module.ModuleView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModuleViewConverter extends AbstractViewConverter<Module, ModuleView> {

    private final PrimitiveProvider<Module> moduleValueProvider =
        (source, field) -> LocaleI18nUtils.getString(source.getModuleValue());

    @Autowired
    private FunctionService functionService;

    @Autowired
    private FunctionViewConverter functionViewConverter;

    private final PrimitiveProvider<Module> functionProvider = (source, field) -> PrimitiveProvider
        .cast(functionViewConverter.convert(functionService.getFunctionsByModuleId(source.getModuleId())));

    @Override
    public ModuleView convert(Module source) {
        addValueProvider("moduleValue", moduleValueProvider);
        addValueProvider("function", functionProvider);

        return complexMapping(source, ModuleView.class);
    }

}
