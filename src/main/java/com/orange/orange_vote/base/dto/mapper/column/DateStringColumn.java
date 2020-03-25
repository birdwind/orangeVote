package com.orange.orange_vote.base.dto.mapper.column;


public interface DateStringColumn extends StringColumn{
    static DateStringColumn getInstance(){
        return new DateStringColumnImpl();
    }
}
