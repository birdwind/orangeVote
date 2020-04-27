package com.orange.orange_vote.validator;

import com.google.common.collect.Lists;
import com.orange.orange_vote.base.validator.UpdateDeleteFormValidator;
import com.orange.orange_vote.view.member.MemberDeleteForm;
import com.orange.orange_vote.view.member.MemberUpdateForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
public class MemberFormValidator extends UpdateDeleteFormValidator<MemberUpdateForm, MemberDeleteForm> {

    @Override
    protected void postValidate(MemberUpdateForm form, BindingResult errors) {
//        if (!isValid(form.getModifyPassword())) {
//            errors.rejectValue("modifyPassword", MemberErrorConstants.MEMBER_PASSWORD_INVALID);
//        }
    }

    @Override
    protected void deleteValidate(MemberDeleteForm form, BindingResult errors) {
        if (form.getMemberUuids() == null) {
            form.setMemberUuids(Lists.newArrayList());
        }
    }

    public static boolean isValid(String pwd) {
        if (pwd == null || pwd.length() < 8) {
            return false;
        }
        boolean containUpper = false;
        boolean containDigit = false;
        int i = 0;
        while (i < pwd.length()) {
            if (containDigit && containUpper) {
                break;
            }
            if (Character.isUpperCase(pwd.charAt(i))) {
                containUpper = true;
            }
            if (Character.isDigit(pwd.charAt(i))) {
                containDigit = true;
            }
            i++;
        }
        return containDigit & containUpper;
    }
}
