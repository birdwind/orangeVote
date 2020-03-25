package com.orange.orange_vote.base.dto.mapper.column;

import lombok.Data;

@Data
final class StringColumnImpl extends AbstractColumn implements StringColumn {
    private String value;

    StringColumnImpl() {
        super();
    }
}
