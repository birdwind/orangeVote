package com.orange.orange_vote.base.view;

import com.orange.orange_vote.base.dto.mapper.column.Header;
import java.util.Collection;
import java.util.Map;

public interface BaseVote extends BaseView {

    Map<String, Header> getHeader();

    void setHeader(Map<String, Header> header);

    Collection<? extends BaseRow> getContents();

    void setContents(Collection<? extends BaseRow> contents);

}
