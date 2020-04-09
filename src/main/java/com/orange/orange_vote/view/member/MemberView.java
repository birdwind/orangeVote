package com.orange.orange_vote.view.member;

import com.orange.orange_vote.base.annotation.ViewType;
import com.orange.orange_vote.base.dto.mapper.column.StringColumn;
import com.orange.orange_vote.base.enums.ViewTypeEnum;
import com.orange.orange_vote.base.view.BaseView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberView implements BaseView {

    private static final long serialVersionUID = 1L;

    private String memberUuid;

    @ViewType(create = ViewTypeEnum.TEXT, update = ViewTypeEnum.LABEL)
    private StringColumn name;

    @ViewType(both = ViewTypeEnum.TEXT)
    private StringColumn username;

    @ViewType(both = ViewTypeEnum.TEXT)
    private StringColumn nickname;

    @ViewType(create = ViewTypeEnum.TEXT, update = ViewTypeEnum.LABEL)
    private StringColumn orangeId;

    @ViewType(both = ViewTypeEnum.TEXT)
    private StringColumn school;

}
