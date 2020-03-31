package com.orange.orange_vote.base.enums;

import com.orange.orange_vote.base.view.EnumListItem;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public interface BaseEnum {

    static List<EnumListItem> getList(Class<? extends BaseEnum> clazz) {
        BaseEnum[] enumConstants = clazz.getEnumConstants();
        return Arrays.stream(enumConstants).map(EnumListItem::new).collect(Collectors.toList());
    }

    Serializable valueOf();

    String valueOfName();

}
