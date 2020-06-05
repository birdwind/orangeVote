package com.orange.orange_vote.constans;

import com.orange.orange_vote.base.enums.BaseEnum;
import java.io.Serializable;

public enum VoteErrorConstantsEnums implements BaseEnum {
    VOTE_NOT_FOUND(0, "Error.Vote.NotFound"),
    VOTE_ALREADY(1, "Error.Vote.isVoted"),
    VOTEOPTION_NOT_FOUND(2, "Error.Vote.Option.NotFound"),
    VOTEOPTION_MULTISELECT(3, "Error.Vote.Option.MultiSelect"),
    VOTEOPTION_NOT_ALLOW_ADD(4, "Error.Vote.Option.NotAllowAdd"),
    VOTEOPTION_UPDATE_VALUE_NOTSET(5, "Error.Vote.Option.UpdateValueNotSet"),
    VOTEOPTION_UPDATE_FAILED(6, "Error.Vote.Option.BeSelected");

    VoteErrorConstantsEnums(Integer errorCode, String errorMsg) {
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
