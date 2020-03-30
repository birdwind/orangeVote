package com.orange.orange_vote.base.view.abstracts;

import com.google.common.collect.Lists;
import com.orange.orange_vote.base.utils.DateTimeUtils;
import com.orange.orange_vote.base.utils.ServletUtils;
import com.orange.orange_vote.base.view.BaseFieldError;
import com.orange.orange_vote.base.view.BasePacker;
import com.orange.orange_vote.base.view.BaseResource;
import com.orange.orange_vote.base.view.BaseView;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;

public abstract class AbstractResourcePacker<R extends BaseResource> implements BasePacker<R> {

    private Class<R> clazz;

    @SuppressWarnings("unchecked")
    public AbstractResourcePacker() {
        this.clazz = (Class<R>) (((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    @Override
    public R getInstance() {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException
            | NoSuchMethodException e) {
            return null;
        }
    }

    @Override
    public R pack() {
        R resource = getInstance();
        set(resource, Lists.newArrayList());
        return resource;
    }

    @Override
    public <V extends BaseView> R pack(Collection<V> views) {
        R resource = getInstance();
        set(resource, views);
        return resource;
    }

    @Override
    public R pack(BaseView view) {
        R resource = getInstance();
        set(resource, view);
        return resource;
    }

    @Override
    public R packErrors() {
        R resource = getInstance();
        resource.setResponse(HttpStatus.NOT_FOUND.toString());
        setOtherProperties(null, resource, HttpStatus.NOT_FOUND);
        return resource;
    }

    @Override
    public R packErrors(HttpStatus httpStatus) {
        R resource = getInstance();
        resource.setResponse(httpStatus.toString());
        setOtherProperties(null, resource, httpStatus);
        return resource;
    }

    @Override
    public R packErrors(HttpStatus httpStatus, String response) {
        R resource = getInstance();
        resource.setResponse(response);
        setOtherProperties(null, resource, httpStatus);
        return resource;
    }

    @Override
    public R packErrors(String url, HttpStatus httpStatus, String response) {
        R resource = getInstance();
        resource.setResponse(response);
        setOtherProperties(url, resource, httpStatus);
        return resource;
    }

    @Override
    public R packErrors(HttpStatus httpStatus, String response, Integer errorCode, String errorMsg) {
        R resource = getInstance();
        resource.setResponse(response);
        resource.setErrorCode(errorCode);
        resource.setErrorMsg(errorMsg);
        setOtherProperties(null, resource, httpStatus);
        return resource;
    }

    // 預設表單欄位錯誤是 412 PRECONDITION_FAILED
    @Override
    public R packFieldErrors(Collection<? extends BaseFieldError> errors) {
        R resource = getInstance();
        resource.setResponse((Serializable) errors);
        setOtherProperties(null, resource, HttpStatus.PRECONDITION_FAILED);
        return resource;
    }

    private void set(R resource, Object response) {
        if (response != null) {
            resource.setResponse((Serializable) response);
            setOtherProperties(null, resource, HttpStatus.OK);
        } else {
            resource.setResponse("Resource Not Found");
            setOtherProperties(null, resource, HttpStatus.NOT_FOUND);
        }
    }

    private void setOtherProperties(String url, R resource, HttpStatus httpStatus) {
        HttpServletRequest request =
            ServletUtils.getRequest().orElseThrow(() -> new HttpServerErrorException(HttpStatus.SERVICE_UNAVAILABLE));
        resource.setHttpStatus(httpStatus.value());
        resource.setStatus(HttpStatus.OK.equals(httpStatus));
        resource.setRequestUrl(url == null ? ServletUtils.getRequestUrl() : url);
        resource.setMethod(request.getMethod());
        resource.setTimeStamp(DateTimeUtils.getCurrentTimestamp());
    }

}
