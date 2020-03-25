package com.orange.orange_vote.base.view.abstracts;

import com.orange.orange_vote.base.dto.mapper.column.Header;
import com.orange.orange_vote.base.view.BaseRow;
import com.orange.orange_vote.base.view.BaseVote;
import java.util.Collection;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractVote implements BaseVote {

    private Map<String, Header> header;

    private Collection<? extends BaseRow> contents;

}
