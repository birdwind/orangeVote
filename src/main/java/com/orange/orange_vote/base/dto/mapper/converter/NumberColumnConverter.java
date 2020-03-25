package com.orange.orange_vote.base.dto.mapper.converter;

import com.orange.orange_vote.base.dto.mapper.column.NumberColumn;
import com.orange.orange_vote.base.dto.mapper.provider.NumberColumnProvider;
import com.orange.orange_vote.base.dto.mapper.provider.ValueProvider;
import java.lang.reflect.Field;

public interface NumberColumnConverter<S> extends ColumnConverter<S, NumberColumn> {

    @Override
    NumberColumnProvider<S> getProvider();

    @Override
    default NumberColumn apply(S s, Field field){
        NumberColumn numberColumn = NumberColumn.getInstance();
        numberColumn.setValue(getProvider().provide(s, field));
        return numberColumn;
    };
}
