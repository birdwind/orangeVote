package com.orange.orange_vote.view.vote;

import com.orange.orange_vote.base.annotation.DateTimeFormatter;
import com.orange.orange_vote.base.annotation.PropertyMap;
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

    private String voteName;

    private String content;

    @PropertyMap(value = "voteTeamRelates.team.teamValue")
    private String team;

    private Integer multiSelection;

    private Boolean isAllowAdd;

    private Boolean isOpen;

    private Boolean isSign;

    private Boolean isVoted;

    @DateTimeFormatter(value = DateTimeFormatType.MINUTE)
    private String expiredDate;

    private List<VoteOptionListItem> voteOptions;

}
