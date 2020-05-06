package com.orange.orange_vote.view.personal;

import com.orange.orange_vote.base.annotation.NullOrNotBlank;
import com.orange.orange_vote.base.view.abstracts.AbstractForm;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "個人資料更新")
public class PersonalUpdateForm extends AbstractForm {

    private static final long serialVersionUID = 1L;

    @ApiParam(value = "會員帳號")
    @NullOrNotBlank
    private String username;

    @ApiParam(value = "會員暱稱")
    @NullOrNotBlank
    private String nickname;

    @ApiParam(value = "學校")
    @NullOrNotBlank
    private String school;

    @ApiParam(value = "系所")
    @NullOrNotBlank
    private String major;

}
