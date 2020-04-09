package com.orange.orange_vote.view.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.orange.orange_vote.base.annotation.NullOrNotBlank;
import com.orange.orange_vote.base.annotation.PropertyMap;
import com.orange.orange_vote.base.constans.BaseErrorConstants;
import com.orange.orange_vote.base.view.abstracts.AbstractForm;
import com.orange.orange_vote.entity.model.MemberRoleRelate;
import com.orange.orange_vote.entity.model.Role;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberRoleRelateForm extends AbstractForm {

    private static final long serialVersionUID = 1L;

    private String relateUuid;

    private List<String> roleUuids;

    private List<String> deleteRoleUuids;

    @JsonIgnore
    private List<Role> roles;

    @JsonIgnore
    private List<MemberRoleRelate> deleteRoles;

}
