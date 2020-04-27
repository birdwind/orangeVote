package com.orange.orange_vote.constans;

import com.orange.orange_vote.base.enums.BaseEnum;
import java.io.Serializable;

public enum TeamErrorConstantsEnums implements BaseEnum {

    TEAM_NOT_FOUND(0, "Error.Team.NotFound"), TEAMUUID_NOT_NULL(1, "Error.Team.TeamUuidNull"), TEAM_VALUE_DUPLICATE(2,
        "Error.Team.TeamValueDuplicate");

    TeamErrorConstantsEnums(Integer errorCode, String errorMsg) {
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
