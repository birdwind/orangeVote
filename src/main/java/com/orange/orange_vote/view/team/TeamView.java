package com.orange.orange_vote.view.team;

import com.orange.orange_vote.base.annotation.ViewType;
import com.orange.orange_vote.base.enums.ViewTypeEnum;
import com.orange.orange_vote.base.view.BaseView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamView implements BaseView {

    private static final long serialVersionUID = 1L;

    private String teamUuid;

    @ViewType(create = ViewTypeEnum.TEXT, update = ViewTypeEnum.TEXT)
    private String teamValue;

    @ViewType(update = ViewTypeEnum.TEXT)
    private String verification;

    @ViewType(create = ViewTypeEnum.HIDDEN, update = ViewTypeEnum.BOOLEAN)
    private Boolean status;

}
