package com.orange.orange_vote.constans;

import com.orange.orange_vote.base.enums.BaseEnum;
import java.io.Serializable;

public enum MemberErrorConstantsEnums implements BaseEnum {

    MEMBER_NOT_FOUND(0, "Error.Member.NotFound"), MEMBER_SUSPENDED(1,
        "Error.Member.Suspended"), MEMBER_PASSWORD_INVALID(2,
            "Error.Member.Password.InValid"), MEMBER_ORANGEID_DUPLICATE(3,
                "Error.Member.OrangeId.Duplicate"), MEMBER_USERNAME_DUPLICATE(4, "Error.Member.Username.Duplicate");

    MemberErrorConstantsEnums(Integer errorCode, String errorMsg) {
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
