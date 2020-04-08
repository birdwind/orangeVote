package com.orange.orange_vote.view.voteOption.converter;

import com.orange.orange_vote.base.dto.mapper.converter.abstracts.AbstractFormConverter;
import com.orange.orange_vote.entity.model.VoteOption;
import com.orange.orange_vote.view.voteOption.VoteOptionDeleteForm;
import org.springframework.stereotype.Component;

@Component
public class VoteOptionFormDeleteConverter extends AbstractFormConverter<VoteOptionDeleteForm, VoteOption> {

    @Override
    public VoteOption convert(VoteOptionDeleteForm source) {
        return source.getVoteOption();
    }
}
