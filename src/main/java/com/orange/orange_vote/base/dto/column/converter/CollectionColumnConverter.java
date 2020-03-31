package com.orange.orange_vote.base.dto.column.converter;

import com.orange.orange_vote.base.dto.mapper.column.CollectionColumn;
import com.orange.orange_vote.base.dto.mapper.provider.CollectionColumnProvider;
import java.lang.reflect.Field;

public interface CollectionColumnConverter<S> extends ColumnConverter<S, CollectionColumn> {

    @Override
    CollectionColumnProvider<S> getProvider();

    @Override
    default CollectionColumn apply(S s, Field field) {
        CollectionColumn collectionColumn = CollectionColumn.getInstance();
        collectionColumn.setValue(getProvider().provide(s, field));
        return collectionColumn;
    }

    ;
}
