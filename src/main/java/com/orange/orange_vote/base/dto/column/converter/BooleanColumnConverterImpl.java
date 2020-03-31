package com.orange.orange_vote.base.dto.column.converter;

import com.orange.orange_vote.base.dto.mapper.provider.BooleanColumnProvider;
import org.springframework.stereotype.Component;
import java.lang.reflect.Field;

@Component(value = "booleanColumnConverterImpl")
final class BooleanColumnConverterImpl<S> implements BooleanColumnConverter<S>, BooleanColumnProvider<S> {

    @Override
    public Boolean provide(S source, Field targetField) {
        return BooleanColumnProvider.cast(get(source, targetField));
    }

    @Override
    public BooleanColumnProvider<S> getProvider() {
        return this;
    }
}
