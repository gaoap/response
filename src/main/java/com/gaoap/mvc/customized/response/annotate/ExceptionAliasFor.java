package com.gaoap.mvc.customized.response.annotate;


import java.lang.annotation.*;


/**
 * 将外部异常映射为内部异常，方便定义错误码和提示消息
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ExceptionAliasFor {
    /**
     * 异常对应的错误码.
     *
     * @return 异常对应的错误码
     */
    String code() default "10404";

    /**
     * 异常信息.
     *
     * @return 异常对应的提示信息
     */
    String msg() default "未识别的外部异常!";

    /**
     * 作为某个异常的别名
     *
     * @return
     */
    Class<? extends Throwable> aliasFor();

}
