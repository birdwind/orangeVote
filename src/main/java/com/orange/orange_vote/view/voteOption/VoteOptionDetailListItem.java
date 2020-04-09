package com.orange.orange_vote.view.voteOption;

import com.orange.orange_vote.base.view.abstracts.AbstractListItem;
import java.math.BigDecimal;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoteOptionDetailListItem extends AbstractListItem {

    private static final long serialVersionUID = 1L;

    private BigDecimal percent;

    private List<String> selecteds;
}
