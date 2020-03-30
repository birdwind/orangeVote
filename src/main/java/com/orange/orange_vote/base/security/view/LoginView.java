package com.orange.orange_vote.base.security.view;

import com.orange.orange_vote.base.view.abstracts.AbstractView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginView extends AbstractView {

    private String orangeId;

    private String username;

    private String status;
}
