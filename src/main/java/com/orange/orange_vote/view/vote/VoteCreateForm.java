package com.orange.orange_vote.view.vote;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.orange.orange_vote.base.annotation.DateTimeFormatter;
import com.orange.orange_vote.base.annotation.NullOrNotBlank;
import com.orange.orange_vote.base.enums.DateTimeFormatType;
import com.orange.orange_vote.base.view.abstracts.AbstractForm;
import com.orange.orange_vote.entity.model.Team;
import com.orange.orange_vote.view.voteOption.VoteOptionForm;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoteCreateForm extends AbstractForm {

    private static final long serialVersionUID = 1L;

    @ApiParam(value = "投票標題")
    @NullOrNotBlank
    private String voteName;

    @ApiParam(value = "投票說明")
    @NotNull
    private String content;

    @ApiParam(value = "多選數量(單選擇為1)")
    @Min(value = 1)
    @NotNull
    private Integer multiSelection;

    @ApiParam(value = "截止日")
    @DateTimeFormatter(value = DateTimeFormatType.MINUTE)
    @NotNull
    private Date expiredDate;

    @ApiParam(value = "團隊Uuid")
    @NotNull
    private String teamUuid;

    @ApiParam(value = "允許添加")
    @NotNull
    private Boolean isAllowAdd;

    @ApiParam(value = "開啟投票")
    @NotNull
    private Boolean isOpen;

    @ApiParam(value = "記名")
    @NotNull
    private Boolean isSign;

//    @ApiParam(value = "投票選項")
//    private List<VoteOptionForm> options;
//
    @ApiParam(value = "投票選項")
    private List<String> optionValues;

    @JsonIgnore
    private Team team;

}
