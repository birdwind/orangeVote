package com.orange.orange_vote.base.view;

import java.io.Serializable;

public interface BaseListItem extends BaseRow {

    Serializable getText();

    void setText(Serializable text);

    Serializable getValue();

    void setValue(Serializable value);

}
