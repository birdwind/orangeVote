package com.orange.orange_vote.base.aop;

import com.orange.orange_vote.base.exception.EntityNotFoundException;
import com.orange.orange_vote.base.view.BaseForm;
import com.orange.orange_vote.base.view.VoidForm;
import org.springframework.validation.BindingResult;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class CreateFormAspect<C extends BaseForm> extends AbstractAuthFormAspect<C, VoidForm, VoidForm> {

    @SuppressWarnings("unchecked")
    protected final void init() {
        Type[] types = (((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments());

        setCreateFromClazz((Class<C>) types[0]);
        setUpdateFromClazz(null);
        setDeleteFromClazz(null);
    }

    @Override
    protected abstract void putAuthenticate(C form, BindingResult errors) throws EntityNotFoundException;

    @Override
    protected final void postAuthenticate(VoidForm form, BindingResult errors) throws EntityNotFoundException {

    }

    @Override
    protected final void deleteAuthenticate(VoidForm form, BindingResult errors) throws EntityNotFoundException {

    }

}
