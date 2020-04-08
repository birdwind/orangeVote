package com.orange.orange_vote.validator;

import com.google.common.collect.Lists;
import com.orange.orange_vote.base.validator.CreateUpdateFormValidator;
import com.orange.orange_vote.view.member.MemberRoleRelateForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
public class MemberRoleRelateFormValidator
    extends CreateUpdateFormValidator<MemberRoleRelateForm, MemberRoleRelateForm> {

    @Override
    protected void putValidate(MemberRoleRelateForm form, BindingResult errors) {
        validate(form, errors);
    }

    @Override
    protected void postValidate(MemberRoleRelateForm form, BindingResult errors) {
        validate(form, errors);
    }

    private void validate(MemberRoleRelateForm form, BindingResult errors) {
        if (form.getRoleUuids() == null) {
            form.setRoleUuids(Lists.newArrayList());
        }
        if (form.getDeleteRoleUuids() == null) {
            form.setDeleteRoleUuids(Lists.newArrayList());
        }
    }
}
