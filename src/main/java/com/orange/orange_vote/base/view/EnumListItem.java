package com.orange.orange_vote.base.view;


import com.orange.orange_vote.base.enums.BaseEnum;
import com.orange.orange_vote.base.view.abstracts.AbstractListItem;

public final class EnumListItem extends AbstractListItem {

    public EnumListItem(BaseEnum baseEnum) {
        setText(baseEnum.valueOfName());
        setValue(baseEnum.valueOf());
    }

}
