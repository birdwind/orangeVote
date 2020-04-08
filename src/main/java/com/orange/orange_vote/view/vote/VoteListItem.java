package com.orange.orange_vote.view.vote;

import com.orange.orange_vote.base.view.abstracts.AbstractListItem;
import com.orange.orange_vote.view.voteOption.VoteOptionListItem;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoteListItem extends AbstractListItem {

    private static final long serialVersionUID = 1L;

    private String content;

    private Integer multiSelection;

    private Boolean isAllowAdd;

    private Boolean isSign;

    private List<VoteOptionListItem> option;
}
