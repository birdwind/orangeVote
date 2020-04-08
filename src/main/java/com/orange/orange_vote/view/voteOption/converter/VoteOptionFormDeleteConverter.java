package com.orange.orange_vote.view.voteOption.converter;

import com.google.common.collect.Lists;
import com.orange.orange_vote.base.dto.mapper.converter.abstracts.AbstractFormConverter;
import com.orange.orange_vote.entity.model.Vote;
import com.orange.orange_vote.entity.model.VoteOption;
import com.orange.orange_vote.view.voteOption.VoteOptionDeleteForm;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.List;

@Component
public class VoteOptionFormDeleteConverter extends AbstractFormConverter<VoteOptionDeleteForm, VoteOption> {

    @Override
    public VoteOption convert(VoteOptionDeleteForm source) {
        VoteOption target = source.getVoteOption();
        target.setUpdateDate(new Date());
        return target;
    }

    public VoteOption convert(VoteOptionDeleteForm source, Vote vote) {
        return convert(source);
    }

    public List<VoteOption> convertList(List<VoteOptionDeleteForm> sources, Vote vote) {
        List<VoteOption> voteOptions = Lists.newArrayList();
        if(sources != null) {
            sources.forEach(source -> {
                voteOptions.add(convert(source, vote));
            });
        }
        return voteOptions;
    }
}
