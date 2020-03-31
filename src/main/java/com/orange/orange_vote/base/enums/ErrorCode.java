package com.orange.orange_vote.base.enums;

import com.google.common.collect.Maps;
import com.orange.orange_vote.base.constans.BaseErrorConstants;
import org.apache.commons.lang3.StringUtils;
import java.io.Serializable;
import java.util.Map;
import java.util.Optional;

public enum ErrorCode implements BaseEnum {
    SUCCESS(0, "Success"), NO_PERMISSION(1, BaseErrorConstants.NO_PERMISSION), NOT_LOGIN(2,
        BaseErrorConstants.NOT_LOGIN), RESOURCE_NOTFUND(3, BaseErrorConstants.RESOURCE_NOT_FOUND), API_NOT_FOUND(4,
            BaseErrorConstants.API_NOT_FOUND), BAD_REQUEST(5, BaseErrorConstants.BAD_REQUEST), DATA_INCOMPLETE(6,
                BaseErrorConstants.DATA_INCOMPLETE), SERVER_ERROR(7, BaseErrorConstants.SERVER_ERROR), LOGIN_ERROR(9,
                    BaseErrorConstants.LOGIN_SUCCESS), EXPIRED_SESSION(10, BaseErrorConstants.EXPIRED_SESSION), FIELD_MISS(11, BaseErrorConstants.FIELD_MISS);

    private static Map<Integer, ErrorCode> map;

    private Integer key;

    private String value;

    ErrorCode(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public static String getErrorMsg(Integer key) {
        if (map == null) {
            init();
        }

        return Optional.ofNullable(map.get(key)).map(m -> m.value).orElse(StringUtils.EMPTY);
    }

    public static Integer getErrorCode(String errorMsg) {
        if (map == null) {
            init();
        }

        return map.values().stream().filter(m -> StringUtils.equals(errorMsg, m.value)).map(ErrorCode::errorCode)
            .findFirst().orElse(null);
    }

    private static void init() {
        map = Maps.newHashMap();

        for (ErrorCode errorCode : values()) {
            map.put(errorCode.key, errorCode);
        }
    }

    public Integer errorCode() {
        return this.key;
    }

    public String errorMsg() {
        return this.value;
    }

    @Override
    public Serializable valueOf() {
        return this.key;
    }

    @Override
    public String valueOfName() {
        return this.value;
    }
}
