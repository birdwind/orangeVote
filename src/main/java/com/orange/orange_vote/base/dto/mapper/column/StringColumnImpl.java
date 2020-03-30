package com.orange.orange_vote.base.dto.mapper.column;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
final class StringColumnImpl extends AbstractColumn implements StringColumn {
    private String value;

    StringColumnImpl() {
        super();
    }
}
