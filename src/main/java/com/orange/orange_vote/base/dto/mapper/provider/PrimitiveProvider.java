package com.orange.orange_vote.base.dto.mapper.provider;

import java.io.Serializable;
import java.lang.reflect.Field;

public interface PrimitiveProvider<S> extends ValueProvider<S> {

    static Serializable cast(Object value) {
        return (Serializable) value;
    }

    @Override
    Serializable provide(final S source, final Field targetField);

}
