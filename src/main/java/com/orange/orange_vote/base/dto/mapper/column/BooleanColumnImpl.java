package com.orange.orange_vote.base.dto.mapper.column;

import lombok.Data;

@Data
final class BooleanColumnImpl extends AbstractColumn implements BooleanColumn{

    private Boolean value;

    BooleanColumnImpl(){super();}
}
