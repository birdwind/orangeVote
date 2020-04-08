package com.orange.orange_vote.view.voteOption;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.orange.orange_vote.base.annotation.NullOrNotBlank;
import com.orange.orange_vote.base.annotation.PropertyMap;
import com.orange.orange_vote.base.view.abstracts.AbstractForm;
import com.orange.orange_vote.entity.model.Vote;
import com.orange.orange_vote.entity.model.VoteOption;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberVoteOptionRelateForm extends AbstractForm {

    private static final long serialVersionUID = 1L;

    @NotNull
    private String voteUuid;

    private List<String> optionUuids;

    private List<VoteOptionForm> addOptions;

    @JsonIgnore
    private List<VoteOption> voteOptions;

    @JsonIgnore
    private Vote vote;

}
