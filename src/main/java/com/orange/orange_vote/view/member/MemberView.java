package com.orange.orange_vote.view.member;

import com.orange.orange_vote.base.view.BaseView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberView implements BaseView {

    private static final long serialVersionUID = 1L;

    private String memberUuid;

    private String name;

    private String username;

    private String nickname;

    private String orangeId;

    private String school;

    private String major;
}
