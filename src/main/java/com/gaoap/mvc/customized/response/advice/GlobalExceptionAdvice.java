package com.gaoap.mvc.customized.response.advice;

import com.gaoap.mvc.customized.response.annotate.ExceptionAliasFor;
import com.gaoap.mvc.customized.response.annotate.ExceptionMapper;
import com.gaoap.mvc.customized.response.config.CustomizedResponseProperties;
import com.gaoap.mvc.customized.response.config.ExceptionAliasRegister;
import com.gaoap.mvc.customized.response.data.Response;
import com.gaoap.mvc.customized.response.data.ResponseStatus;
import com.gaoap.mvc.customized.response.factory.ResponseFactory;
import com.gaoap.mvc.customized.response.factory.ResponseStatusFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 全局 统一 异常格式处理.
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionAdvice {

    @Resource
    private ResponseStatusFactory responseStatusFactory;

    @Resource
    private ResponseFactory responseFactory;
    @Resource
    private ExceptionAliasRegister exceptionAliasRegister;

    @Resource
    private CustomizedResponseProperties customizedResponseProperties;

    /**
     * 异常处理逻辑.
     *
     * @param throwable 业务逻辑抛出的异常
     * @return 统一返回包装后的结果
     */
    @ExceptionHandler({Throwable.class})
    @ResponseBody
    public Response exceptionHandler(Throwable throwable) {
        if (customizedResponseProperties.isPrintException()) {
            log.error("全局捕获到异常", throwable);
        }
        ResponseStatus responseStatus;

            responseStatus = fromExceptionClass(throwable.getClass());


        return responseFactory.newInstance(responseStatus);
    }



    private ResponseStatus fromExceptionClass(Class<? extends Throwable> clazz) {

        ExceptionMapper exceptionMapper = clazz.getAnnotation(ExceptionMapper.class);

        if (exceptionMapper != null) {
            return responseStatusFactory.newInstance(exceptionMapper.code(),
                    exceptionMapper.msg());
        }

        //获取已注册的别名
        if (exceptionAliasRegister != null) {
            ExceptionAliasFor exceptionAliasFor = exceptionAliasRegister.getExceptionAliasFor(clazz);
            if (exceptionAliasFor != null) {
                return responseStatusFactory.newInstance(exceptionAliasFor.code(),
                        exceptionAliasFor.msg());
            }
        }

        return responseStatusFactory.defaultFail();
    }

}
