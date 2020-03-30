package com.orange.orange_vote.base.dto.mapper.column;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
final class BooleanColumnImpl extends AbstractColumn implements BooleanColumn {

    private Boolean value;

    BooleanColumnImpl() {
        super();
    }
}
