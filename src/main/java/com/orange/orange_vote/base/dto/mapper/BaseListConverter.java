package com.orange.orange_vote.base.dto.mapper;

import com.orange.orange_vote.base.dto.basic.BaseConverter;
import com.orange.orange_vote.base.repo.BaseModel;
import com.orange.orange_vote.base.view.BaseListItem;
import java.io.Serializable;

public interface BaseListConverter<S extends BaseModel, L extends BaseListItem> extends BaseConverter<S, L> {

    Serializable getText(S source);

    Serializable getValue(S source);

    void setOtherProperty(L item, S source);

}
