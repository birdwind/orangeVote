package com.orange.orange_vote.view.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.orange.orange_vote.base.view.abstracts.AbstractForm;
import com.orange.orange_vote.entity.model.Member;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDeleteForm extends AbstractForm {

    private static final long serialVersionUID = 1L;

    @NotNull
    private List<String> memberUuids;

    @JsonIgnore
    private List<Member> members;

}
