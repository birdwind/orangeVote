/*
 * Copyright (c) 2019. Create by Terry Huang (黃昭維)
 */

package com.gss.pfd.snbdc.rbac.base.dto.form;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class AbstractForm implements BaseForm {

    public String toJson() {
        Gson gson = new GsonBuilder()
            .setExclusionStrategies(new EntityExclusionStrategy(), new SuperClassExclusionStrategy()).create();
        return gson.toJson(this);
    }

}
