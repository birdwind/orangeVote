package com.orange.orange_vote.view.vote.converter;

import com.orange.orange_vote.base.dto.mapper.converter.abstracts.AbstractFormConverter;
import com.orange.orange_vote.entity.model.Vote;
import com.orange.orange_vote.view.vote.VoteUpdateForm;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class VoteUpdateFormConverter extends AbstractFormConverter<VoteUpdateForm, Vote> {

    @Override
    public Vote convert(VoteUpdateForm source) {
        Vote target = simpleMapping(source, source.getVote());
        target.setUpdateDate(new Date());
        return target;
    }
}
