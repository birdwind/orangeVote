package com.orange.orange_vote.view.vote;

import com.orange.orange_vote.base.annotation.DateTimeFormatter;
import com.orange.orange_vote.base.annotation.ViewType;
import com.orange.orange_vote.base.dto.mapper.column.BooleanColumn;
import com.orange.orange_vote.base.dto.mapper.column.DateStringColumn;
import com.orange.orange_vote.base.dto.mapper.column.NumberColumn;
import com.orange.orange_vote.base.dto.mapper.column.StringColumn;
import com.orange.orange_vote.base.enums.DateTimeFormatType;
import com.orange.orange_vote.base.enums.ViewTypeEnum;
import com.orange.orange_vote.base.view.BaseView;
import com.orange.orange_vote.view.team.TeamListItem;
import com.orange.orange_vote.view.voteOption.VoteOptionDetailListItem;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoteDetailView implements BaseView {

    private static final long serialVersionUID = 1L;

    private String voteUuid;

    @ViewType(both = ViewTypeEnum.TEXT)
    private StringColumn voteName;

    @ViewType(both = ViewTypeEnum.TEXT)
    private StringColumn content;

    private List<TeamListItem> teams;

    @DateTimeFormatter(value = DateTimeFormatType.MINUTE)
    @ViewType(both = ViewTypeEnum.TEXT)
    private DateStringColumn expiredDate;

    private List<VoteOptionDetailListItem> options;

    @ViewType(both = ViewTypeEnum.BOOLEAN)
    private BooleanColumn isAllowAdd;

    @ViewType(both = ViewTypeEnum.BOOLEAN)
    private BooleanColumn isOpen;

    @ViewType(both = ViewTypeEnum.BOOLEAN)
    private BooleanColumn isSign;

    @ViewType(both = ViewTypeEnum.UNSIGNED_INTEGER)
    private NumberColumn multiSelection;

}
