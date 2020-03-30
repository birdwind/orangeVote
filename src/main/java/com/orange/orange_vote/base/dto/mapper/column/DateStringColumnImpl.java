package com.orange.orange_vote.base.dto.mapper.column;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
final class DateStringColumnImpl extends AbstractColumn implements DateStringColumn{

    private String value;

    DateStringColumnImpl(){super();}
}
