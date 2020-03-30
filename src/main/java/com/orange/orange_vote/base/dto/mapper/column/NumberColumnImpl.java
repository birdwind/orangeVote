package com.orange.orange_vote.base.dto.mapper.column;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
final class NumberColumnImpl extends AbstractColumn implements NumberColumn {

    private Number value;

    NumberColumnImpl() {
        super();
    }
}
