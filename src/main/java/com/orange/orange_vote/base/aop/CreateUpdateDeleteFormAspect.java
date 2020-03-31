package com.orange.orange_vote.base.aop;


import com.orange.orange_vote.base.view.BaseForm;

public abstract class CreateUpdateDeleteFormAspect<C extends BaseForm, U extends BaseForm, D extends BaseForm>
    extends AbstractAuthFormAspect<C, U, D> {

}
