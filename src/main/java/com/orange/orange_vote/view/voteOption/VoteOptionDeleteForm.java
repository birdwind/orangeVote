package com.orange.orange_vote.view.voteOption;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.orange.orange_vote.base.view.abstracts.AbstractForm;
import com.orange.orange_vote.entity.model.VoteOption;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoteOptionDeleteForm extends AbstractForm {

    private static final long serialVersionUID = 1L;

    @NotNull
    private String optionUuid;

    @JsonIgnore
    private VoteOption voteOption;

}
