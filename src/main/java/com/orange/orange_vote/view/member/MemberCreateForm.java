package com.orange.orange_vote.view.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.orange.orange_vote.base.annotation.NullOrNotBlank;
import com.orange.orange_vote.base.view.abstracts.AbstractForm;
import com.orange.orange_vote.entity.model.Role;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "新增會員Form")
public class MemberCreateForm extends AbstractForm {

    private static final long serialVersionUID = 1L;

    @ApiParam(value = "椪柑證號", defaultValue = "20200505001")
    @NotNull
    private String orangeId;

    @ApiParam(value = "姓名", defaultValue = "小椪柑")
    @NotNull
    private String name;

    @ApiParam(value = "密碼", defaultValue = "12345678", format = "至少一個英文字母並大於8個字元")
    @NotNull
    private String password;

    @ApiParam(value = "學校", defaultValue = "新北高級中學")
    @NullOrNotBlank
    private String school;

    @ApiParam(value = "會員身分Uuid陣列")
    private List<String> roleUuids;

    @JsonIgnore
    private List<Role> roleList;
}
