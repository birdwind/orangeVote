package com.orange.orange_vote.base.view.abstracts;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.orange.orange_vote.base.view.BaseForm;
import com.orange.orange_vote.base.view.EntityExclusionStrategy;
import com.orange.orange_vote.base.view.SuperClassExclusionStrategy;

public abstract class AbstractForm implements BaseForm {

    public String toJson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setExclusionStrategies(new EntityExclusionStrategy(), new SuperClassExclusionStrategy());
        Gson gson = gsonBuilder.create();
        return gson.toJson(this);
    }

}
