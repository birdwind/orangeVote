package com.orange.orange_vote.base.view;

import java.io.Serializable;

public interface BaseResource extends BaseView {

    Boolean getStatus();

    void setStatus(Boolean status);

    Integer getHttpStatus();

    void setHttpStatus(Integer httpStatus);

    String getRequestUrl();

    void setRequestUrl(String requestUrl);

    String getMethod();

    void setMethod(String method);

    String getResourceName();

    void setResourceName(String resourceName);

    String getTimeStamp();

    void setTimeStamp(String timeStamp);

    Serializable getResponse();

    void setResponse(Serializable response);
}
