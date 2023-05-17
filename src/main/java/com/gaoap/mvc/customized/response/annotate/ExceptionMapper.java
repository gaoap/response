package com.gaoap.mvc.customized.response.annotate;


import java.lang.annotation.*;

/**
 * 内部自定义异常映射注解.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ExceptionMapper {

    /**
     * 异常对应的错误码.
     *
     * @return 异常对应的错误码
     */
    String code() default "20404";

    /**
     * 异常信息.
     *
     * @return 异常对应的提示信息
     */
    String msg() default "未被识别的内部错误!";
}
