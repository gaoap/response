package com.gaoap.mvc.customized.response.annotate.enable;

import com.gaoap.mvc.customized.response.config.ScanException;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(ScanException.class)
public @interface ScanCustomizedException {
    /**
     * 扫描的基础包名
     */
    String[] basePackages() default {};

    /**
     * 扫描包名的提供类
     */
    Class<?>[] basePackageClasses() default {};
}
