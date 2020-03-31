package com.orange.orange_vote.base.dto.mapper;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.orange.orange_vote.base.annotation.Ignore;
import com.orange.orange_vote.base.dto.basic.BaseConverter;
import com.orange.orange_vote.base.dto.mapper.column.Header;
import com.orange.orange_vote.base.enums.ViewTypeEnum;
import com.orange.orange_vote.base.repo.BaseModel;
import com.orange.orange_vote.base.view.BaseRow;
import com.orange.orange_vote.base.view.BaseVote;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public interface BaseVoteConverter <S extends BaseModel, R extends BaseRow, V extends BaseVote>
        extends BaseConverter<S, R>, HeaderConverter {

    V getInstance();

    Class<R> getRowClass();

    default V convertToVote(S source){return convertToVote(Lists.newArrayList(source));}

    default V convertToVote(Collection<S> sources){
        V vote = getInstance();

        vote.setContents(convert(sources));
        addProviders();
        vote.setHeader(getHeader());
        getHeaderRequiredMap().clear();
        getHeaderViewTypeMap().clear();
        return vote;
    }

    void addProviders();

    Map<String, ViewTypeEnum> getHeaderViewTypeMap();

    Map<String, Boolean> getHeaderRequiredMap();

    default Map<String, Header> getHeader() {
        Map<String, Header> header = Maps.newLinkedHashMap();
        Arrays.stream(getRowClass().getDeclaredFields())
                .filter(field -> !Modifier.isStatic(field.getModifiers()) && !field.isAnnotationPresent(Ignore.class))
                .forEach(field -> {
                    String n = field.getName();
                    String v = Optional.ofNullable(getHeaderViewTypeMap().get(n)).map(ViewTypeEnum::value).orElse(null);
                    Boolean r = Optional.ofNullable(getHeaderRequiredMap().get(n)).orElse(null);
                    header.put(n, Header.getInstance(getTitle(field), v == null ? getType(field) : v,
                            r == null ? getRequired(field) : r, getKeyValue(field)));
                });
        return header;
    }
}
