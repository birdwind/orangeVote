package com.orange.orange_vote.base.dto.mapper.column;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.orange.orange_vote.base.view.BaseListItem;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
final class HeaderImpl implements Header {

    private String title;

    private String type;

    private Boolean required;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String search;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<? extends BaseListItem> keyValue;

    HeaderImpl() {
        super();
    }

    HeaderImpl(String title, String type, Boolean required, List<? extends BaseListItem> keyValue) {
        super();
        this.title = title;
        this.type = type;
        this.required = required;
        this.keyValue = keyValue;
    }

}
