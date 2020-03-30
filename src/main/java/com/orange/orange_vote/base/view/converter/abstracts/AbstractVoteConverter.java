package com.orange.orange_vote.base.view.abstracts;

import com.google.common.collect.Maps;
import com.orange.orange_vote.base.enums.ViewTypeEnum;
import com.orange.orange_vote.base.repo.BaseModel;
import com.orange.orange_vote.base.utils.LocaleI18nUtils;
import com.orange.orange_vote.base.view.BaseRow;
import com.orange.orange_vote.base.view.BaseVote;
import com.orange.orange_vote.base.view.BaseVoteConverter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public abstract class AbstractVoteConverter<S extends BaseModel, R extends BaseRow, V extends BaseVote>
        extends AbstractViewConverter<S, R> implements BaseVoteConverter<S, R, V> {

    private static final ViewTypeEnum DEFAULT_VIEW_TYPE = ViewTypeEnum.LABEL;

    private static final Boolean DEFAULT_REQUIRED = false;

    private Class<S> sourceClass;

    private Class<R> rowClass;

    private Class<V> voteClass;

    @Getter
    @Setter
    private boolean isCreate;

    @Getter
    private String prefix;

    @Getter
    private Map<String, ViewTypeEnum> headerViewTypeMap = Maps.newHashMap();

    @Getter
    private Map<String, Boolean> headerRequiredMap = Maps.newHashMap();

    public AbstractVoteConverter() {
        Type[] types = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments();

        this.sourceClass = (Class<S>) types[0];
        this.sourceClass = (Class<S>) types[1];
        this.sourceClass = (Class<S>) types[2];

        this.prefix = LocaleI18nUtils.getI18nPrefix(sourceClass);
    }

    @Override
    public V getInstance() {
        try {
            return voteClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException
                | NoSuchMethodException e) {
            return null;
        }
    }

    @Override
    public Class<R> getRowClass() {
        return this.rowClass;
    }

    @Override
    public ViewTypeEnum getDefaultViewType() {
        return DEFAULT_VIEW_TYPE;
    }


    @Override
    public Boolean getDefaultRequired() {
        return DEFAULT_REQUIRED;
    }

    @Override
    protected void addTypeProvider(String name, ViewTypeEnum viewTypeEnum) {
        headerViewTypeMap.put(name, viewTypeEnum);
    }

    @Override
    protected void addTypeProvider(ViewTypeEnum viewTypeEnum, String... names) {
        for (String n : names) {
            headerViewTypeMap.put(n, viewTypeEnum);
        }
    }

    @Override
    protected void addRequiredProvider(String name, Boolean required) {
        headerRequiredMap.put(name, required);
    }

    @Override
    protected void addRequiredProvider(Boolean required, String... names) {
        for (String n : names) {
            headerRequiredMap.put(n, required);
        }
    }

    @Override
    public void addProviders() {
        // default no providers
    }
}
