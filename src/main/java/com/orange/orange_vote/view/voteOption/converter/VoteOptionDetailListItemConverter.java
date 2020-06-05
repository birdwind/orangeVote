package com.orange.orange_vote.view.voteOption.converter;

import com.orange.orange_vote.base.dto.mapper.converter.abstracts.AbstractListConverter;
import com.orange.orange_vote.base.security.model.SystemUser;
import com.orange.orange_vote.entity.model.Member;
import com.orange.orange_vote.entity.model.MemberVoteOptionRelate;
import com.orange.orange_vote.entity.model.VoteOption;
import com.orange.orange_vote.entity.service.VoteOptionService;
import com.orange.orange_vote.view.voteOption.VoteOptionDetailListItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Collectors;

@Component
public class VoteOptionDetailListItemConverter extends AbstractListConverter<VoteOption, VoteOptionDetailListItem> {

    @Autowired
    private VoteOptionService voteOptionService;

    @Override
    public Serializable getText(VoteOption source) {
        return source.getVoteOptionValue();
    }

    @Override
    public Serializable getValue(VoteOption source) {
        return source.getVoteOptionUuid();
    }

    @Override
    public void setOtherProperty(VoteOptionDetailListItem item, VoteOption source) {

        Member member = SystemUser.getMember();

        if (source.getVote().getIsSign()) {
            item.setSelecteds(source.getMemberVoteOptionRelates().stream().map(MemberVoteOptionRelate::getMember)
                .map(Member::getNickname).collect(Collectors.toList()));
        }

        BigDecimal countSelected = voteOptionService.countOptionBeSelectedByVoteId(source.getVote().getVoteId());

        BigDecimal percent;
        if (source.getSelectedCount() == null) {
            percent = BigDecimal.ZERO;
        } else {
            percent = countSelected.equals(BigDecimal.ZERO) ? BigDecimal.ZERO
                : source.getSelectedCount().divide(countSelected, 2, RoundingMode.HALF_UP)
                    .multiply(new BigDecimal(100));
        }
        item.setPercent(percent);
    }
}
