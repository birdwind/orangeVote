package com.orange.orange_vote.view.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.orange.orange_vote.base.annotation.NullOrNotBlank;
import com.orange.orange_vote.base.annotation.PropertyMap;
import com.orange.orange_vote.base.view.abstracts.AbstractForm;
import com.orange.orange_vote.entity.model.Member;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberUpdateForm extends AbstractForm {

    private static final long serialVersionUID = 1L;

    @NotNull
    private String memberUuid;

    @NotNull
    private String username;

    @NotNull
    private String nickname;

    @NullOrNotBlank
    @Size(min = 8)
    private String modifyPassword;

    @NullOrNotBlank
    private String school;

    @JsonIgnore
    private Member member;
}
