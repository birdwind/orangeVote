package com.orange.orange_vote.base.dto.column.converter;

import com.orange.orange_vote.base.dto.mapper.column.StringColumn;
import com.orange.orange_vote.base.dto.mapper.provider.StringColumnProvider;
import java.lang.reflect.Field;

public interface StringColumnConverter<S> extends ColumnConverter<S, StringColumn> {

    @Override
    StringColumnProvider<S> getProvider();

    @Override
    default StringColumn apply(S source, Field field){
        StringColumn stringColumn = StringColumn.getInstance();
        stringColumn.setValue(getProvider().provide(source, field));
        return stringColumn;
    }
}
