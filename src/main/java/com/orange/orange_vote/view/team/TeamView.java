package com.orange.orange_vote.view.team;

import com.orange.orange_vote.base.annotation.ViewType;
import com.orange.orange_vote.base.dto.mapper.column.BooleanColumn;
import com.orange.orange_vote.base.dto.mapper.column.StringColumn;
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
    private StringColumn teamValue;

    @ViewType(update = ViewTypeEnum.TEXT)
    private StringColumn passCode;

    @ViewType(create = ViewTypeEnum.HIDDEN, update = ViewTypeEnum.BOOLEAN)
    private BooleanColumn status;

}
