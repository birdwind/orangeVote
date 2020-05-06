package com.orange.orange_vote.view.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.orange.orange_vote.base.annotation.IgnoreSwaggerParameter;
import com.orange.orange_vote.base.annotation.NullOrNotBlank;
import com.orange.orange_vote.base.annotation.PropertyMap;
import com.orange.orange_vote.base.view.abstracts.AbstractForm;
import com.orange.orange_vote.entity.model.Member;
import com.orange.orange_vote.entity.model.MemberRoleRelate;
import com.orange.orange_vote.entity.model.Role;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "會員更新")
public class MemberUpdateForm extends AbstractForm {

    private static final long serialVersionUID = 1L;

    @ApiParam(value = "會員UUID")
    @NotNull
    private String memberUuid;

    @ApiParam(value = "會員帳號")
    @NotNull
    private String username;

    @ApiParam(value = "會員暱稱")
    @NotNull
    private String nickname;

    @NullOrNotBlank
    private String modifyPassword;

    @ApiParam(value = "學校")
    @NullOrNotBlank
    private String school;

    @ApiParam(value = "系所")
    @NullOrNotBlank
    private String major;

    @ApiParam(value = "新增身分")
    private List<String> roleUuids;

    @ApiParam(value = "刪除身分")
    private List<String> deleteRoleUuids;

    @JsonIgnore
    private Member member;

    @JsonIgnore
    private List<Role> roleList;

    @JsonIgnore
    private List<MemberRoleRelate> memberRoleRelateList;
}
