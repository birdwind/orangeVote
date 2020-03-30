package com.orange.orange_vote.base.dto.mapper.column;

import java.util.Collection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
final class CollectionColumnImpl extends AbstractColumn implements CollectionColumn {

    private Collection<?> value;

    CollectionColumnImpl() {
        super();
    }
}
