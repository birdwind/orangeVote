package com.orange.orange_vote.base.aop;

import com.orange.orange_vote.base.exception.ApiNotFoundException;
import com.orange.orange_vote.base.exception.PageNotFoundException;
import com.orange.orange_vote.base.utils.ServletUtils;
import org.springframework.core.convert.converter.Converter;
import java.io.Serializable;
import java.util.Optional;
import javax.annotation.Nonnull;

public abstract class AbstractAuthModelAspect<T extends Serializable> implements Converter<String, T> {

    public AbstractAuthModelAspect() {}

    @Override
    public final T convert(@Nonnull String source) {
        try {
            T target = authenticate(source);
            assertNotNull(target, source);
            return target;
        } catch (Throwable e) {
            throw ServletUtils.isApi() ? new ApiNotFoundException(e) : new PageNotFoundException(e);
        }
    }

    protected abstract T authenticate(String source) throws Throwable;

    private void assertNotNull(T target, String source) {
        Optional.ofNullable(target).orElseThrow(() -> {
            String msg = source + " not found";
            return ServletUtils.isApi() ? new ApiNotFoundException(msg) : new PageNotFoundException(msg);
        });
    }

}
