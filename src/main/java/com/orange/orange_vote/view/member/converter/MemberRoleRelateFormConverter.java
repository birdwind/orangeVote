package com.orange.orange_vote.view.member.converter;

import com.orange.orange_vote.base.dto.mapper.converter.abstracts.AbstractFormConverter;
import com.orange.orange_vote.base.repo.BaseModel;
import com.orange.orange_vote.entity.model.Member;
import com.orange.orange_vote.entity.model.MemberRoleRelate;
import com.orange.orange_vote.view.member.MemberRoleRelateForm;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MemberRoleRelateFormConverter extends AbstractFormConverter<MemberRoleRelateForm, MemberRoleRelate> {

    @Override
    public MemberRoleRelate convert(MemberRoleRelateForm source) {
        return null;
    }

    public List<MemberRoleRelate> convert(MemberRoleRelateForm source, Member member) {
        List<MemberRoleRelate> memberRoleRelates =
            source.getRoles().stream().map(role -> new MemberRoleRelate(member, role)).collect(Collectors.toList());
        if(source.getDeleteRoles() != null) {
            memberRoleRelates.addAll(source.getDeleteRoles().stream().peek(BaseModel::delete).collect(Collectors.toList()));
        }
        return memberRoleRelates;
    }
}
