package com.orange.orange_vote.base.dto.mapper.column;

import java.util.Collection;
import lombok.Data;

@Data
final class CollectionColumnImpl extends AbstractColumn implements CollectionColumn{

    private Collection<?> value;

    CollectionColumnImpl(){super();}
}
