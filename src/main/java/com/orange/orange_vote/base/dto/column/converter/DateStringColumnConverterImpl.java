package com.orange.orange_vote.base.dto.column.converter;

import com.orange.orange_vote.base.dto.mapper.provider.DateStringColumnProvider;
import org.springframework.stereotype.Component;
import java.lang.reflect.Field;
import java.util.Date;

@Component(value = "dateStringColumnConverterImpl")
final class DateStringColumnConverterImpl<S> implements DateStringColumnConverter<S>, DateStringColumnProvider<S> {


    @Override
    public DateStringColumnProvider<S> getProvider() {
        return this;
    }

    @Override
    public Date provide(S source, Field targetField) {
        return DateStringColumnProvider.cast(get(source, targetField));
    }
}
