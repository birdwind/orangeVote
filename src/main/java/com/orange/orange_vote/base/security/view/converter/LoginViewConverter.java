package com.orange.orange_vote.base.security.view.converter;

import com.orange.orange_vote.base.security.view.LoginView;
import com.orange.orange_vote.base.dto.mapper.converter.abstracts.AbstractViewConverter;
import com.orange.orange_vote.entity.model.Member;
import org.springframework.stereotype.Component;

@Component
public class LoginViewConverter extends AbstractViewConverter<Member, LoginView> {
    @Override
    public LoginView convert(Member source) {
        return complexMapping(source, LoginView.class);
    }
}
