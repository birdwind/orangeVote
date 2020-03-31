package com.orange.orange_vote.base.dto.mapper.converter.abstracts;

import com.orange.orange_vote.base.dto.basic.SimpleConverter;
import com.orange.orange_vote.base.repo.BaseModel;
import com.orange.orange_vote.base.view.BaseForm;

public abstract class AbstractFormConverter<S extends BaseForm, T extends BaseModel> implements SimpleConverter<S, T> {

}
