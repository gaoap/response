package com.gaoap.mvc.customized.response.config;

import com.gaoap.mvc.customized.response.advice.GlobalExceptionAdvice;
import com.gaoap.mvc.customized.response.advice.NotVoidResponseBodyAdvice;
import com.gaoap.mvc.customized.response.advice.VoidResponseBodyAdvice;
import com.gaoap.mvc.customized.response.data.Response;
import com.gaoap.mvc.customized.response.data.ResponseStatus;
import com.gaoap.mvc.customized.response.factory.ResponseFactory;
import com.gaoap.mvc.customized.response.factory.ResponseStatusFactory;
import com.gaoap.mvc.customized.response.factory.defaults.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@EnableConfigurationProperties(CustomizedResponseProperties.class)
public class AutoCustomizedResponseConfig {

    @Bean
    @Scope("prototype")
    @ConditionalOnProperty(prefix = "customized.response", name = "responseStatusStyle", havingValue = "0", matchIfMissing = true)
    public ResponseStatus responseStatus() {
        return new DefaultResponseStatus();
    }

    @Bean
    @ConditionalOnMissingBean(value = GlobalExceptionAdvice.class)
    public GlobalExceptionAdvice globalExceptionAdvice() {
        return new GlobalExceptionAdvice();
    }

    @Bean
    @ConditionalOnMissingBean(NotVoidResponseBodyAdvice.class)
    public NotVoidResponseBodyAdvice notVoidResponseBodyAdvice() {
        return new NotVoidResponseBodyAdvice();
    }

    @Bean
    @ConditionalOnMissingBean(VoidResponseBodyAdvice.class)

    public VoidResponseBodyAdvice voidResponseBodyAdvice() {
        return new VoidResponseBodyAdvice();
    }

    @Bean
    @ConditionalOnMissingBean(value = {ResponseFactory.class})
    public ResponseFactory responseBeanFactory() {
        return new DefaultResponseFactory();
    }

    @Bean
    @Scope("prototype")
    @ConditionalOnProperty(prefix = "customized.response", name = "responseStyle", havingValue = "0", matchIfMissing = true)
    public Response response0() {
        return new DefaultResponseImplStyle0();
    }

    @Bean
    @Scope("prototype")
    @ConditionalOnProperty(prefix = "customized.response", name = "responseStyle", havingValue = "1", matchIfMissing = false)
    public Response response1() {
        return new DefaultResponseImplStyle1();
    }

    @Bean
    @ConditionalOnMissingBean(value = {ResponseStatusFactory.class})
    public ResponseStatusFactory responseStatusFactory() {
        return new DefaultResponseStatusFactoryImpl();
    }


}
