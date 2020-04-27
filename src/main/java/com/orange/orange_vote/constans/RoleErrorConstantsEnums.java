package com.orange.orange_vote.constans;

import com.orange.orange_vote.base.enums.BaseEnum;
import java.io.Serializable;

public enum RoleErrorConstantsEnums implements BaseEnum {

    ROLE_NOTFOUND(0, "Error.Role.NotFound"), MEMBER_ROLE_DUPLICATE(1, "Error.MemberRole.Duplicate");

    RoleErrorConstantsEnums(Integer errorCode, String errorMsg) {
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
    public java.lang.String valueOfName() {
        return this.errorMsg;
    }

    public String valueOfCode() {
        return String.valueOf(this.errorCode);
    }
}
