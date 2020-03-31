package com.orange.orange_vote.base.view;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.orange.orange_vote.base.repo.AbstractModel;

public class EntityExclusionStrategy implements ExclusionStrategy {

    @Override
    public boolean shouldSkipField(FieldAttributes fieldAttributes) {
        return AbstractModel.class.isAssignableFrom(fieldAttributes.getDeclaredClass());
    }

    @Override
    public boolean shouldSkipClass(Class<?> aClass) {
        return false;
    }

}
