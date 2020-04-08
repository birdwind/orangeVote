package com.orange.orange_vote.view.vote;

import com.orange.orange_vote.base.annotation.DateTimeFormatter;
import com.orange.orange_vote.base.annotation.ViewType;
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
    private String voteName;

    @ViewType(both = ViewTypeEnum.TEXT)
    private String content;

    @ViewType(both = ViewTypeEnum.TEXT)
    private Integer multiSelection;

    @ViewType(both = ViewTypeEnum.BOOLEAN)
    private Boolean isAllowAdd;

    @ViewType(both = ViewTypeEnum.BOOLEAN)
    private Boolean isOpen;

    @ViewType(both = ViewTypeEnum.BOOLEAN)
    private Boolean isSign;

    @DateTimeFormatter(value = DateTimeFormatType.MINUTE)
    @ViewType(both = ViewTypeEnum.TEXT)
    private Date expiredDate;

    private List<VoteOptionListItem> voteOptions;

}
