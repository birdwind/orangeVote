package com.orange.orange_vote.view.team;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.orange.orange_vote.base.annotation.NullOrNotBlank;
import com.orange.orange_vote.base.constans.BaseErrorConstants;
import com.orange.orange_vote.base.view.abstracts.AbstractForm;
import com.orange.orange_vote.entity.model.Team;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamForm extends AbstractForm {

    private static final long serialVersionUID = 1L;

    @NullOrNotBlank(message = BaseErrorConstants.NULL)
    private String teamUuid;

    @NullOrNotBlank(message = BaseErrorConstants.NULL)
    private String teamValue;

    @NullOrNotBlank(message = BaseErrorConstants.NULL)
    private String verification;

    @JsonIgnore
    private Team team;
}
