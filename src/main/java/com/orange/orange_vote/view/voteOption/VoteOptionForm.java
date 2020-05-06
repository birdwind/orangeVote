package com.orange.orange_vote.view.voteOption;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.orange.orange_vote.base.annotation.NullOrNotBlank;
import com.orange.orange_vote.base.annotation.PropertyMap;
import com.orange.orange_vote.base.view.abstracts.AbstractForm;
import com.orange.orange_vote.entity.model.VoteOption;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoteOptionForm extends AbstractForm {

    private static final long serialVersionUID = 1L;

    @ApiParam(value = "選項Uuid")
    @NullOrNotBlank
    @PropertyMap(value = "voteOptionUuid")
    private String optionUuid;

    @ApiParam(value = "選項內容")
    @NotNull
    @PropertyMap(value = "voteOptionValue")
    private String value;

    @JsonIgnore
    private VoteOption voteOption;

}
