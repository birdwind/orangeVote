package com.orange.orange_vote.view.role.converter;

import com.orange.orange_vote.base.dto.mapper.converter.abstracts.AbstractFormConverter;
import com.orange.orange_vote.entity.model.Role;
import com.orange.orange_vote.view.role.RoleForm;
import org.springframework.stereotype.Component;

@Component
public class RoleFormConverter extends AbstractFormConverter<RoleForm, Role> {

    @Override
    public Role convert(RoleForm source) {
        return null;
    }
}
