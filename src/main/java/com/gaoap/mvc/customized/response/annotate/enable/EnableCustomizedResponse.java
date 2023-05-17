package com.gaoap.mvc.customized.response.annotate.enable;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@ScanCustomizedException
@CustomizedResponse
public @interface EnableCustomizedResponse {
    @AliasFor(
            annotation = ScanCustomizedException.class,
            attribute = "basePackages"
    )
    String[] basePackages() default {};

    @AliasFor(
            annotation = ScanCustomizedException.class,
            attribute = "basePackageClasses"
    )
    Class<?>[] basePackageClasses() default {};
}
