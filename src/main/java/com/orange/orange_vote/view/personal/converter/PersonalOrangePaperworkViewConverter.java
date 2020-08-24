package com.orange.orange_vote.view.personal.converter;

import com.orange.orange_vote.base.dto.mapper.converter.abstracts.AbstractViewConverter;
import com.orange.orange_vote.base.dto.mapper.provider.PrimitiveProvider;
import com.orange.orange_vote.base.utils.DateTimeUtils;
import com.orange.orange_vote.entity.model.Member;
import com.orange.orange_vote.entity.model.MemberRoleRelate;
import com.orange.orange_vote.entity.model.Role;
import com.orange.orange_vote.view.personal.PersonalOrangePaperworkView;
import org.springframework.stereotype.Component;
import java.util.Calendar;
import java.util.Objects;

@Component
public class PersonalOrangePaperworkViewConverter extends AbstractViewConverter<Member, PersonalOrangePaperworkView> {

    private PrimitiveProvider<Member> identityProvider = (source, field) -> source.getMemberRoleRelates().stream()
        .map(MemberRoleRelate::getRole).map(Role::getRoleKey).findFirst().orElse("");

    private PrimitiveProvider<Member> graduationProvider = (source, targetField) -> {
        Calendar createDate = Calendar.getInstance();
        Calendar firstDate = Calendar.getInstance();
        createDate.setTime(source.getCreateDate());
        firstDate.setTime(Objects.requireNonNull(DateTimeUtils.parseDate("2001/06/30")));
        int graduation = createDate.get(Calendar.YEAR) - firstDate.get(Calendar.YEAR);
        if(createDate.get(Calendar.MONTH) > firstDate.get(Calendar.MONTH)){
            graduation += 1;
        }
        return graduation;
    };

    @Override
    public PersonalOrangePaperworkView convert(Member source) {
        addValueProvider("identity", identityProvider);
        addValueProvider("graduation", graduationProvider);
        return complexMapping(source, PersonalOrangePaperworkView.class);
    }
}
