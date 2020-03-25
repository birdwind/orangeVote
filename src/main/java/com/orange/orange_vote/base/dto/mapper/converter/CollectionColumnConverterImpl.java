package com.orange.orange_vote.base.dto.mapper.converter;

import com.orange.orange_vote.base.dto.mapper.column.CollectionColumn;
import com.orange.orange_vote.base.dto.mapper.provider.CollectionColumnProvider;
import org.springframework.stereotype.Component;
import java.lang.reflect.Field;
import java.util.Collection;

@Component(value = "collectionColumnConverterImpl")
final class CollectionColumnConverterImpl<S> implements CollectionColumnConverter<S>, CollectionColumnProvider<S> {

    @Override
    public CollectionColumnProvider<S> getProvider() {
        return this;
    }

    @Override
    public Collection<?> provide(S source, Field targetField) {
        return CollectionColumnProvider.cast(get(source, targetField));
    }
}
