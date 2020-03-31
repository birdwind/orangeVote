package com.orange.orange_vote.base.aop;

import com.orange.orange_vote.base.exception.EntityNotFoundException;
import com.orange.orange_vote.base.view.BaseForm;
import com.orange.orange_vote.base.view.VoidForm;
import org.springframework.validation.BindingResult;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class DeleteFormAspect<D extends BaseForm> extends AbstractAuthFormAspect<VoidForm, VoidForm, D> {

    @SuppressWarnings("unchecked")
    protected final void init() {
        Type[] types = (((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments());

        setCreateFromClazz(null);
        setUpdateFromClazz(null);
        setDeleteFromClazz((Class<D>) types[0]);
    }

    @Override
    protected final void putAuthenticate(VoidForm form, BindingResult errors) throws EntityNotFoundException {

    }

    @Override
    protected final void postAuthenticate(VoidForm form, BindingResult errors) throws EntityNotFoundException {

    }

    @Override
    protected abstract void deleteAuthenticate(D form, BindingResult errors) throws EntityNotFoundException;

}
