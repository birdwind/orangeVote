package com.orange.orange_vote.base.dto.mapper.column;

import com.orange.orange_vote.base.view.BaseListItem;
import java.util.List;

public interface Header extends Column {

    static Header getInstance() {
        return new HeaderImpl();
    }

    static Header getInstance(String title, String type, Boolean required, List<? extends BaseListItem> keyValue) {
        return new HeaderImpl(title, type, required, keyValue);
    }

}
