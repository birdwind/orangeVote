package com.orange.orange_vote.base.repo;

public abstract class AbstractModel implements BaseModel {

    @Override
    public boolean isCreate() {
        return getId() == 0;
    }

}
