package com.orange.orange_vote.base.aop;

import com.orange.orange_vote.base.exception.EntityNotFoundException;
import com.orange.orange_vote.base.view.BaseForm;
import com.orange.orange_vote.base.view.VoidForm;
import org.springframework.validation.BindingResult;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class CreateUpdateFormAspect<C extends BaseForm, U extends BaseForm>
    extends AbstractAuthFormAspect<C, U, VoidForm> {

    @SuppressWarnings("unchecked")
    protected final void init() {
        Type[] types = (((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments());

        setCreateFromClazz((Class<C>) types[0]);
        setUpdateFromClazz((Class<U>) types[1]);
        setDeleteFromClazz(null);
    }

    @Override
    protected abstract void putAuthenticate(C form, BindingResult errors) throws EntityNotFoundException;

    @Override
    protected abstract void postAuthenticate(U form, BindingResult errors) throws EntityNotFoundException;

    @Override
    protected final void deleteAuthenticate(VoidForm form, BindingResult errors) throws EntityNotFoundException {

    }

}
