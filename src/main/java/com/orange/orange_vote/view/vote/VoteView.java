package com.orange.orange_vote.view.vote;

import com.orange.orange_vote.base.annotation.DateTimeFormatter;
import com.orange.orange_vote.base.annotation.ViewType;
import com.orange.orange_vote.base.dto.mapper.column.BooleanColumn;
import com.orange.orange_vote.base.dto.mapper.column.CollectionColumn;
import com.orange.orange_vote.base.dto.mapper.column.DateStringColumn;
import com.orange.orange_vote.base.dto.mapper.column.NumberColumn;
import com.orange.orange_vote.base.dto.mapper.column.StringColumn;
import com.orange.orange_vote.base.enums.DateTimeFormatType;
import com.orange.orange_vote.base.enums.ViewTypeEnum;
import com.orange.orange_vote.base.view.BaseView;
import com.orange.orange_vote.view.voteOption.VoteOptionListItem;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoteView implements BaseView {

    private static final long serialVersionUID = 1L;

    private String voteUuid;

    @ViewType(both = ViewTypeEnum.TEXT)
    private StringColumn voteName;

    @ViewType(both = ViewTypeEnum.TEXT)
    private StringColumn content;

    @ViewType(both = ViewTypeEnum.UNSIGNED_INTEGER)
    private NumberColumn multiSelection;

    @ViewType(both = ViewTypeEnum.BOOLEAN)
    private BooleanColumn isAllowAdd;

    @ViewType(both = ViewTypeEnum.BOOLEAN)
    private BooleanColumn isOpen;

    @ViewType(both = ViewTypeEnum.BOOLEAN)
    private BooleanColumn isSign;

    @DateTimeFormatter(value = DateTimeFormatType.MINUTE)
    @ViewType(both = ViewTypeEnum.TEXT)
    private DateStringColumn expiredDate;

    @ViewType(both = ViewTypeEnum.LABEL)
    private List<VoteOptionListItem> voteOptions;

}
