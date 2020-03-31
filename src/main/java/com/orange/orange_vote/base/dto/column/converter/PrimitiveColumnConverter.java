package com.orange.orange_vote.base.dto.column.converter;

import com.orange.orange_vote.base.dto.mapper.provider.PrimitiveProvider;
import java.io.Serializable;
import java.lang.reflect.Field;

public interface PrimitiveColumnConverter<S> extends ColumnConverter<S, Serializable> {

    @Override
    PrimitiveProvider<S> getProvider();

    @Override
    default Serializable apply(S s, Field field) {
        return getProvider().provide(s, field);
    }
}
