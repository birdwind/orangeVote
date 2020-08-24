package com.orange.orange_vote.view.personal;

import com.orange.orange_vote.base.view.abstracts.AbstractView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonalOrangePaperworkView extends AbstractView {

    private static final long serialVersionUID = 1L;

    private String name;

    private String nickname;

    private String identity;

    private String orangeId;

    private String school;

    private String major;

    private String graduation;
}
