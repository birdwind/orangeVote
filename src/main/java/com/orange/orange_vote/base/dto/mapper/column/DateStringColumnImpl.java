package com.orange.orange_vote.base.dto.mapper.column;

import lombok.Data;

@Data
final class DateStringColumnImpl extends AbstractColumn implements DateStringColumn{

    private String value;

    DateStringColumnImpl(){super();}
}
