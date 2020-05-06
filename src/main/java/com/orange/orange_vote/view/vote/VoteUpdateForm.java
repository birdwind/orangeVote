package com.orange.orange_vote.view.vote;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.orange.orange_vote.base.annotation.NullOrNotBlank;
import com.orange.orange_vote.base.view.abstracts.AbstractForm;
import com.orange.orange_vote.entity.model.Team;
import com.orange.orange_vote.entity.model.Vote;
import com.orange.orange_vote.entity.model.VoteOption;
import com.orange.orange_vote.view.voteOption.VoteOptionDeleteForm;
import com.orange.orange_vote.view.voteOption.VoteOptionForm;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoteUpdateForm extends AbstractForm {

    private static final long serialVersionUID = 1L;

    @NullOrNotBlank
    private String voteUuid;

    @NullOrNotBlank
    private String voteName;

    @NotNull
    private String content;

    @Min(value = 1)
    @NotNull
    private Integer multiSelection;

    @NotNull
    private Date expiredDate;

    @NotNull
    private String teamUuid;

    @NotNull
    private Boolean isAllowAdd;

    @NotNull
    private Boolean isOpen;

    @NotNull
    private Boolean isSign;

    private List<VoteOptionForm> options;

    private List<String> deleteOptionUuids;

    @JsonIgnore
    private Vote vote;

    @JsonIgnore
    private Team team;

    @JsonIgnore
    private List<VoteOption> voteOptionList;

}