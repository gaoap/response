package com.gaoap.mvc.customized.response.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "customized.response")
@Data
public class CustomizedResponseProperties {
    /**
     * validation框架校验错误提示错误码
     */
    private String filed_validation_code = "-1111";
    /**
     * validationResponseStatusFactory的风格
     * validationResponseStatusFactoryStyle==0,validationResponseStatusFactory的风格 DefaultValidationResponseStatusFactory
     * 其他值，自定义实现ValidationResponseStatusFactoryStyle
     */
    private Integer validationResponseStatusFactoryStyle = 0;
    private boolean printException = true;

    /**
     * responseStyle的风格
     * responseStyle==0,Response风格为 DefaultResponseImplStyle0
     * responseStyle=1,Response风格为 DefaultResponseImplStyle1
     * 其他值，自定义实现Response
     */
    private Integer responseStyle = 0;
    /**
     * responseStatusStyle
     * responseStatusStyle==0,Response风格为 DefaultResponseStatus
     * 其他值，自定义实现DefaultResponseStatus
     */
    private Integer responseStatusStyle = 0;

    /**
     * 默认的成功返回码
     */
    private String defaultSuccessCode = "0";

    /**
     * 默认的成功提示
     */
    private String defaultSuccessMsg = "success";

    /**
     * 默认的失败码
     */
    private String defaultFailCode = "-1";

    /**
     * 默认的失败提示
     */
    private String defaultFailMsg = "error";

}
