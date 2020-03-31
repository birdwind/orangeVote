package com.orange.orange_vote.base.view.converter.abstracts;

import com.orange.orange_vote.base.repo.BaseModel;
import com.orange.orange_vote.base.view.abstracts.AbstractListItem;
import com.orange.orange_vote.base.view.converter.BaseListConverter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class AbstractListConverter<S extends BaseModel, L extends AbstractListItem>
    implements BaseListConverter<S, L> {

    private Class<L> clazz;

    @SuppressWarnings("unchecked")
    public AbstractListConverter() {
        Type[] types = (((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments());
        this.clazz = (Class<L>) types[1];
    }

    @Override
    public final L convert(S source) {
        try {
            L target = clazz.getDeclaredConstructor().newInstance();

            target.setText(getText(source));
            target.setValue(getValue(source));
            setOtherProperty(target, source);

            return target;
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException
            | IllegalAccessException e) {
            return null;
        }
    }

}
