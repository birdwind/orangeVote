package com.orange.orange_vote.base.dto.basic;

public interface ComplexConverter<S, T> extends BaseConverter<S, T> {

    T complexMapping(S source, Class<T> targetClass);

    T complexMapping(S source, T target);

}
