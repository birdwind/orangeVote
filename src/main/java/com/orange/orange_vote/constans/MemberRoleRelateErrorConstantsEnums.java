package com.orange.orange_vote.constans;

import com.orange.orange_vote.base.enums.BaseEnum;
import java.io.Serializable;

public enum MemberRoleRelateErrorConstantsEnums implements BaseEnum {

    MEMBER_ROLE_RELATE_NOTFOUND(0, "Error.Member.Role.NotFound"), MEMBER_ROLE_RELATE_DUPLICATE(1, "Error.Member.Role.Duplicate");

    MemberRoleRelateErrorConstantsEnums(Integer errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    private Integer errorCode;

    private String errorMsg;

    @Override
    public Serializable valueOf() {
        return this.errorMsg;
    }

    @Override
    public String valueOfName() {
        return this.errorMsg;
    }

    public String valueOfCode() {
        return String.valueOf(this.errorCode);
    }
}
