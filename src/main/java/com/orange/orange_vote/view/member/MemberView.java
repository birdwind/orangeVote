package com.orange.orange_vote.view.member;

import com.orange.orange_vote.base.annotation.ViewType;
import com.orange.orange_vote.base.enums.ViewTypeEnum;
import com.orange.orange_vote.base.view.BaseView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberView implements BaseView {

    private static final long serialVersionUID = 1L;

    private String memberUuid;

    @ViewType(create = ViewTypeEnum.TEXT, update = ViewTypeEnum.LABEL)
    private String name;

    @ViewType(both = ViewTypeEnum.TEXT)
    private String username;

    @ViewType(both = ViewTypeEnum.TEXT)
    private String nickname;

    @ViewType(create = ViewTypeEnum.TEXT, update = ViewTypeEnum.LABEL)
    private String orangeId;

    @ViewType(both = ViewTypeEnum.TEXT)
    private String school;

}
