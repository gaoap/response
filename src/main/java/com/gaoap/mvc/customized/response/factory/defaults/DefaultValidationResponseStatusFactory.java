package com.gaoap.mvc.customized.response.factory.defaults;

import com.gaoap.mvc.customized.response.data.ResponseStatus;
import com.gaoap.mvc.customized.response.factory.ResponseStatusFactory;
import com.gaoap.mvc.customized.response.factory.ValidationResponseStatusFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DefaultValidationResponseStatusFactory implements ValidationResponseStatusFactory {
    @Resource
    private ResponseStatusFactory responseStatusFactory;
    @Value("${customized.response.filed_validation_code}")
    String filed_validation_code = "-2";

    @Override
    public ResponseStatus makeResponseStatus(Throwable throwable) {
        if (throwable instanceof BindException e) {
            return this.make(e);
        }
        if (throwable instanceof MethodArgumentNotValidException e) {
            return this.make(e);
        }
        if (throwable instanceof ConstraintViolationException e) {
            return this.make(e);
        }
        return responseStatusFactory.defaultFail();
    }

    //    <1> 处理 form data方式调用接口校验失败抛出的异常
    public ResponseStatus make(BindException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<String> collect = fieldErrors.stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        e.printStackTrace();
        String joiningString = collect.stream().collect(Collectors.joining("|", "{", "}"));

        return responseStatusFactory.newInstance(filed_validation_code,
                joiningString);
    }

    //     <2> 处理 json 请求体调用接口校验失败抛出的异常
    public ResponseStatus make(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<String> collect = fieldErrors.stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        String joiningString = collect.stream().collect(Collectors.joining("|", "{", "}"));

        return responseStatusFactory.newInstance(filed_validation_code,
                joiningString);
    }

    //    <3> 处理单个参数校验失败抛出的异常
    public ResponseStatus make(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        List<String> collect = constraintViolations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
        String joiningString = collect.stream().collect(Collectors.joining("|", "{", "}"));

        return responseStatusFactory.newInstance(filed_validation_code,
                joiningString);
    }
}

