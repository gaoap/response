package com.gaoap.mvc.customized.response.annotate.enable;

import com.gaoap.mvc.customized.response.config.AutoCustomizedResponseConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(AutoCustomizedResponseConfig.class)
public @interface CustomizedResponse {
}
