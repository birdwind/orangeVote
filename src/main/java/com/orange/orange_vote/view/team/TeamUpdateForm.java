package com.orange.orange_vote.view.team;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.orange.orange_vote.base.annotation.NullOrNotBlank;
import com.orange.orange_vote.base.view.abstracts.AbstractForm;
import com.orange.orange_vote.entity.model.Team;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamUpdateForm extends AbstractForm {

    private static final long serialVersionUID = 1L;

    @ApiParam(value = "團隊Uuid")
    @NullOrNotBlank
    private String teamUuid;

    @ApiParam(value = "團隊名稱")
    @NullOrNotBlank
    private String teamValue;

    @ApiParam(value = "團隊驗證碼")
    @NullOrNotBlank
    private String passCode;

    @JsonIgnore
    private Team team;
}
