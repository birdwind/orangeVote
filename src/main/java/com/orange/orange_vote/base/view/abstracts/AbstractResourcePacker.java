package com.orange.orange_vote.base.view.abstracts;

import com.google.common.collect.Lists;
import com.orange.orange_vote.base.enums.ErrorCode;
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
        return pack(Lists.newArrayList(), null, null);
    }

    @Override
    public R pack(Integer errorCode, String errorMsg) {
        return pack(Lists.newArrayList(), errorCode, errorMsg);
    }

    @Override
    public R pack(BaseView view) {
        return pack(Lists.newArrayList(view), null, null);
    }

    @Override
    public R pack(BaseView view, Integer errorCode, String errorMsg) {
        return pack(Lists.newArrayList(view), errorCode, errorMsg);
    }

    @Override
    public <V extends BaseView> R pack(Collection<V> views) {
        return pack(views, null, null);
    }

    @Override
    public <V extends BaseView> R pack(Collection<V> views, Integer errorCode, String errorMsg) {
        R resource = getInstance();
        set(resource, views, errorCode, errorMsg);
        return resource;
    }

    @Override
    public R packErrors() {
        return packErrors(HttpStatus.NOT_FOUND, null, null);
    }

    @Override
    public R packErrors(HttpStatus httpStatus, Integer errorCode, String errorMsg) {
        return packErrors(httpStatus, httpStatus.toString(), errorCode, errorMsg);
    }

    @Override
    public R packErrors(HttpStatus httpStatus, String response, Integer errorCode, String errorMsg) {
        return packErrors(null, httpStatus, response, errorCode, errorMsg);
    }

    @Override
    public R packErrors(String url, HttpStatus httpStatus, String response, Integer errorCode, String errorMsg) {
        R resource = getInstance();
        setError(resource, response, httpStatus, errorCode, errorMsg);
        setOtherProperties(url, resource, httpStatus);
        return resource;
    }

    // 預設表單欄位錯誤是 412 PRECONDITION_FAILED
    @Override
    public R packFieldErrors(Collection<? extends BaseFieldError> errors, Integer errorCode, String errorMsg) {
        R resource = getInstance();
        resource.setResponseFieldError((Serializable) errors);
        resource.setErrorCode(errorCode);
        resource.setErrorMsg(errorMsg);
        setOtherProperties(null, resource, HttpStatus.PRECONDITION_FAILED);
        return resource;
    }

    @Override
    public R packNotFoundErrors(String response) {
        R resource = getInstance();
        setError(resource, response, HttpStatus.PRECONDITION_FAILED, ErrorCode.DATA_INCOMPLETE.errorCode(),
            ErrorCode.DATA_INCOMPLETE.errorMsg());
        setOtherProperties(null, resource, HttpStatus.PRECONDITION_FAILED);
        return resource;
    }

    private void set(R resource, Object response, Integer errorCode, String errorMsg) {
        if (response != null) {
            resource.setResponse((Serializable) response);
            resource.setErrorCode(errorCode == null ? ErrorCode.SUCCESS.errorCode() : errorCode);
            resource.setErrorMsg(errorMsg == null ? ErrorCode.SUCCESS.errorMsg() : errorMsg);
            setOtherProperties(null, resource, HttpStatus.OK);
        } else {
            resource.setResponseError("Resource Not Found");
            resource.setErrorCode(ErrorCode.RESOURCE_NOTFUND.errorCode());
            resource.setErrorMsg(ErrorCode.RESOURCE_NOTFUND.errorMsg());
            setOtherProperties(null, resource, HttpStatus.NOT_FOUND);
        }
    }

    private void setError(R resource, Object response, HttpStatus httpStatus, Integer errorCode, String errorMsg) {
        if (response != null) {
            resource.setResponseError((Serializable) response);
            resource.setErrorMsg(errorMsg);
            resource.setErrorCode(errorCode);
        } else {
            resource.setResponseError(httpStatus.toString());
            resource.setErrorMsg(ErrorCode.RESOURCE_NOTFUND.errorMsg());
            resource.setErrorCode(ErrorCode.RESOURCE_NOTFUND.errorCode());
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
