package com.orange.orange_vote.base.view;

import org.springframework.http.HttpStatus;
import java.util.Collection;

public interface BasePacker<R extends BaseResource> {

    R getInstance();

    R pack();

    <V extends BaseView> R pack(V view);

    <V extends BaseView> R pack(Collection<V> views);

    R packErrors();

    R packErrors(HttpStatus httpStatus);

    R packErrors(HttpStatus httpStatus, String response);

    R packErrors(String url, HttpStatus httpStatus, String response);

    R packFieldErrors(Collection<? extends BaseFieldError> errors);

}
