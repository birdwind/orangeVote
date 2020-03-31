package com.orange.orange_vote.base.dto.column.converter;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.google.common.collect.Sets;
import com.orange.orange_vote.base.annotation.DateTimeFormatter;
import com.orange.orange_vote.base.dto.mapper.provider.CollectionColumnProvider;
import com.orange.orange_vote.base.dto.mapper.provider.PrimitiveProvider;
import com.orange.orange_vote.base.enums.DateTimeFormatType;

@Component(value = "primitiveColumnConverterImpl")
final class PrimitiveColumnConverterImpl<S> implements PrimitiveColumnConverter<S>, PrimitiveProvider<S> {

    @Override
    public PrimitiveProvider<S> getProvider() {
        return this;
    }

    @Override
    public Serializable provide(S source, Field targetField) {
        Object object = get(source, targetField);

        if (targetField.getType() == String.class && object == null) {
            return "";
        } else if (targetField.getType() == String.class && Date.class.isAssignableFrom(object.getClass())) {
            DateTimeFormatType dateTimeFormatType =
                Optional.ofNullable(targetField.getAnnotation(DateTimeFormatter.class)).map(DateTimeFormatter::value)
                    .orElse(DateTimeFormatType.DEFAULT);
            return dateTimeFormatType.format(object);
        } else if (targetField.getType() == Set.class) {
            return Sets.newHashSet(CollectionColumnProvider.cast(object));
        } else {
            return PrimitiveProvider.cast(object);
        }
    }
}
