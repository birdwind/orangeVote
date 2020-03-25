package com.orange.orange_vote.base.dto.mapper.column;

public interface NumberColumn extends Column {

    static NumberColumn getInstance() {
        return new NumberColumnImpl();
    }

    Number getValue();

    void setValue(Number value);
}
