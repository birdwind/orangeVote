package com.orange.orange_vote.view.vote;

import com.orange.orange_vote.base.annotation.DateTimeFormatter;
import com.orange.orange_vote.base.enums.DateTimeFormatType;
import com.orange.orange_vote.base.view.abstracts.AbstractListItem;
import com.orange.orange_vote.view.voteOption.VoteOptionDetailListItem;
import com.orange.orange_vote.view.voteOption.VoteOptionListItem;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VotedListItem extends AbstractListItem {

    private static final long serialVersionUID = 1L;

    private String content;

    private Integer multiSelection;

    private String team;

    private String expireDate;

    private Boolean isAllowAdd;

    private Boolean isSign;

    private Boolean isEnd;

    private Boolean isVoted;

    private List<VoteOptionListItem> options;

    private List<VoteOptionDetailListItem> optionsDetail;

}
