package com.orange.orange_vote.base.view.abstracts;

import com.orange.orange_vote.base.view.BaseListItem;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractListItem implements BaseListItem {

    private Serializable text;

    private Serializable value;
}
