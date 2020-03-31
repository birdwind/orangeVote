package com.orange.orange_vote.view.function.converter;

import com.orange.orange_vote.base.dto.mapper.provider.PrimitiveProvider;
import com.orange.orange_vote.base.enums.OperatorType;
import com.orange.orange_vote.base.utils.LocaleI18nUtils;
import com.orange.orange_vote.base.dto.mapper.converter.abstracts.AbstractViewConverter;
import com.orange.orange_vote.entity.model.Function;
import com.orange.orange_vote.view.function.FunctionView;
import org.springframework.stereotype.Component;

@Component
public class FunctionViewConverter extends AbstractViewConverter<Function, FunctionView> {

    private final PrimitiveProvider<Function> backUrlProvider =
        (source, field) -> OperatorType.PAGE.url() + source.getBackUrl();

    private final PrimitiveProvider<Function> functionValueProvider =
        (source, field) -> LocaleI18nUtils.getString(source.getFunctionValue());

    @Override
    public FunctionView convert(Function source) {
        addValueProvider("backUrl", backUrlProvider);
        addValueProvider("functionValue", functionValueProvider);

        return complexMapping(source, FunctionView.class);
    }

}
