package com.orange.orange_vote.view.role.converter;

import com.orange.orange_vote.base.dto.mapper.converter.abstracts.AbstractListConverter;
import com.orange.orange_vote.entity.model.Role;
import com.orange.orange_vote.view.role.RoleListItem;
import org.springframework.stereotype.Component;
import java.io.Serializable;

@Component
public class RoleListItemConverter extends AbstractListConverter<Role, RoleListItem> {

    @Override
    public Serializable getText(Role source) {
        return source.getRoleKey();
    }

    @Override
    public Serializable getValue(Role source) {
        return source.getRoleUuid();
    }

    @Override
    public void setOtherProperty(RoleListItem item, Role source) {

    }
}
