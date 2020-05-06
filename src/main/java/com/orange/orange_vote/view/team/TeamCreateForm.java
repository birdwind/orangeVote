package com.orange.orange_vote.view.team;

import com.orange.orange_vote.base.annotation.NullOrNotBlank;
import com.orange.orange_vote.base.view.abstracts.AbstractForm;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamCreateForm extends AbstractForm {

    private static final long serialVersionUID = 1L;

    @ApiParam(value = "團隊名稱")
    @NullOrNotBlank
    private String teamValue;

    @ApiParam(value = "團隊驗證碼")
    @NullOrNotBlank
    private String passCode;

}
