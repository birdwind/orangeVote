package com.orange.orange_vote.base.dto.column.converter;

import com.orange.orange_vote.base.dto.mapper.column.BooleanColumn;
import com.orange.orange_vote.base.dto.mapper.provider.BooleanColumnProvider;
import java.lang.reflect.Field;

public interface BooleanColumnConverter<S> extends ColumnConverter<S, BooleanColumn> {

    @Override
    BooleanColumnProvider<S> getProvider();

    @Override
    default BooleanColumn apply(S s, Field field) {
        BooleanColumn booleanColumn = BooleanColumn.getInstance();
        booleanColumn.setValue(getProvider().provide(s, field));
        return booleanColumn;
    }

    ;
}
