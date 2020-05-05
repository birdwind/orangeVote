package com.orange.orange_vote.view.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.orange.orange_vote.base.annotation.IgnoreSwaggerParameter;
import com.orange.orange_vote.base.annotation.NullOrNotBlank;
import com.orange.orange_vote.base.annotation.PropertyMap;
import com.orange.orange_vote.base.view.abstracts.AbstractForm;
import com.orange.orange_vote.entity.model.Member;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "會員更新")
public class MemberUpdateForm extends AbstractForm {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "會員UUID")
    @NotNull
    private String memberUuid;

    @ApiModelProperty(value = "會員帳號")
    @NotNull
    private String username;

    @ApiModelProperty(value = "會員暱稱")
    @NotNull
    private String nickname;

//    @NullOrNotBlank
//    @Size(min = 8)
//    private String modifyPassword;

    @ApiModelProperty(value = "學校")
    @NullOrNotBlank
    private String school;

    @ApiModelProperty(value = "系所")
    @NullOrNotBlank
    private String major;

    @JsonIgnore
    private Member member;
}
