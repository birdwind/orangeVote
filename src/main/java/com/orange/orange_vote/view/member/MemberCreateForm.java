package com.orange.orange_vote.view.member;

import com.orange.orange_vote.base.annotation.NullOrNotBlank;
import com.orange.orange_vote.base.view.abstracts.AbstractForm;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberCreateForm extends AbstractForm {

    private static final long serialVersionUID = 1L;

    @NotNull
    private String orangeId;

    @NotNull
    private String name;

    @NotNull
    @Size(min = 8)
    private String password;

    @NullOrNotBlank
    private String school;
}
