package com.orange.orange_vote.base.dto.column.converter;

import com.orange.orange_vote.base.dto.mapper.provider.NumberColumnProvider;
import org.springframework.stereotype.Component;
import java.lang.reflect.Field;

@Component(value = "numberColumnConverterImpl")
final class NumberColumnConverterImpl<S> implements NumberColumnConverter<S>, NumberColumnProvider<S> {

    @Override
    public NumberColumnProvider<S> getProvider() {
        return this;
    }

    @Override
    public Number provide(S source, Field targetField) {
        return NumberColumnProvider.cast(get(source, targetField));
    }
}
