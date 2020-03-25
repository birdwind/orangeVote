package com.orange.orange_vote.base.exception;

import com.orange.orange_vote.base.system.SystemResource;
import com.orange.orange_vote.base.system.converter.SystemFieldErrorConverter;
import com.orange.orange_vote.base.system.converter.SystemResourcePacker;
import org.springframework.aop.framework.AopConfigException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import java.io.IOException;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ControllerExceptionTranslator {

    @Autowired
    private SystemResourcePacker systemResourcePacker;

    @Autowired
    private SystemFieldErrorConverter systemFieldErrorConverter;

    //SQL錯誤
    @ResponseBody
    @ExceptionHandler(DataIntegrityViolationException.class)
    public SystemResource sqlException(DataIntegrityViolationException e) {
        return systemResourcePacker.packErrors(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    public SystemResource invalidDataAccessApiUsageException(InvalidDataAccessApiUsageException e) {
        return systemResourcePacker.packErrors(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    // AOP (或其他) 拋出表單驗證錯誤
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public SystemResource methodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        SystemResource systemResource =
                systemResourcePacker.packFieldErrors(systemFieldErrorConverter.convert(result.getFieldErrors()));

        //TODO: System log儲存至DataBase
//        SystemLog log = new SystemLog();
//        log.setResponseContent(systemResource.toJson());
//        systemLogService.saveSystemLog(log);
        return systemResource;
    }

    @ResponseBody
    @ExceptionHandler(RelateNotMatchException.class)
    public SystemResource bindRelateNotMatchException(RelateNotMatchException e) {
        return systemResourcePacker.packErrors(e.getHttpStatus(), e.getMessage());
    }

    // BaseValidator (Spring Validator) 拋出表單驗證錯誤
    @ResponseBody
    @ExceptionHandler(BindException.class)
    public SystemResource bindException(BindException e) {
        BindingResult result = e.getBindingResult();
        SystemResource systemResource =
                systemResourcePacker.packFieldErrors(systemFieldErrorConverter.convert(result.getFieldErrors()));

        //TODO: System log儲存至DataBase
//        SystemLog log = new SystemLog();
//        log.setResponseContent(systemResource.toJson());
//        systemLogService.saveSystemLog(log);
        return systemResource;
    }

    @ResponseBody
    @ExceptionHandler(AccessResourceException.class)
    public SystemResource bindAccessResourceException(AccessResourceException e) {
        return systemResourcePacker.packErrors(HttpStatus.FORBIDDEN, e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public SystemResource httpMessageNotReadableException(HttpMessageNotReadableException e) {
        return systemResourcePacker.packErrors(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    // Http 請求的提交內容格式不符合 controller 定義的
    @ResponseBody
    @ExceptionHandler(MissingServletRequestPartException.class)
    public SystemResource missingServletRequestPartException(MissingServletRequestPartException e) {
        return systemResourcePacker.packErrors(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    // Http 請求用了錯誤的提交方法 (例如：要求 formData 卻使用 application/json)
    // throw InvalidContentTypeException but catch MultipartException
    @ResponseBody
    @ExceptionHandler(MultipartException.class)
    public SystemResource handleMultipartException(MultipartException e) {
        return systemResourcePacker.packErrors(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public void handleBadCredentialsException() {}

    @ExceptionHandler({AopConfigException.class})
    public void handleAopConfigExceptionException(HttpServletRequest httpServletRequest,
                                                  HttpServletResponse httpServletResponse) throws IOException {
        httpServletRequest.setAttribute("error", "bad request");
        httpServletResponse.sendError(HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(PermissionDeniedDataAccessException.class)
    public void handlePermissionDeniedDataAccessException(HttpServletRequest httpServletRequest,
                                                          HttpServletResponse httpServletResponse) throws IOException {
        httpServletRequest.setAttribute("error", "permission denied");
        httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public void handleUserNotFoundException(HttpServletRequest httpServletRequest,
                                            HttpServletResponse httpServletResponse) throws IOException {
        httpServletRequest.setAttribute("error", "user not found");
        httpServletResponse.sendError(HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public void handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e,
                                                          HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        Throwable cause = getNestedCause(e);
        HttpStatus status = HttpStatus.NOT_FOUND;

        if (cause instanceof BaseThrowable) {
            httpServletRequest.setAttribute("cause", cause);
            status = Optional.ofNullable(((BaseThrowable) cause).getHttpStatus()).orElse(HttpStatus.NOT_FOUND);
        }

        httpServletRequest.setAttribute("error", cause.getMessage());

        httpServletResponse.sendError(status.value());
    }

    @ResponseBody
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public SystemResource handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        System.out.println("HttpRequestMethodNotSupportedException error");
        e.printStackTrace();
        Throwable cause = getNestedCause(e);
        HttpStatus status = HttpStatus.NOT_FOUND;
        if (cause instanceof BaseThrowable) {
            status = Optional.ofNullable(((BaseThrowable) cause).getHttpStatus()).orElse(HttpStatus.NOT_FOUND);
        }
        return systemResourcePacker.packErrors(status, cause.getMessage());
    }

    private Throwable getNestedCause(Throwable e) {
        if (e.getCause() == null) {
            return e;
        }

        return getNestedCause(e.getCause());
    }
}
