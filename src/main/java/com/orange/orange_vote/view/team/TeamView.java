package com.orange.orange_vote.view.team;

import com.orange.orange_vote.base.view.BaseView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamView implements BaseView {

    private static final long serialVersionUID = 1L;

    private String teamUuid;

    private String teamValue;

    private String passCode;

    private boolean status;

}
