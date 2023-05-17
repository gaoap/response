package com.gaoap.mvc.customized.response.advice;

import com.gaoap.mvc.customized.response.data.Response;
import com.gaoap.mvc.customized.response.factory.ResponseFactory;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.annotation.Resource;
import java.util.Objects;

@ControllerAdvice
@Order(value = Integer.MAX_VALUE)
public class NotVoidResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Resource
    private ResponseFactory responseFactory;

    /**
     * 只处理不返回void的，并且MappingJackson2HttpMessageConverter支持的类型.
     *
     * @param methodParameter 返回类型
     * @param clazz           消息转换器
     * @return 是否对这种返回值进行处理
     */
    @Override
    public boolean supports(MethodParameter methodParameter,
                            Class<? extends HttpMessageConverter<?>> clazz) {

        return !Objects.requireNonNull(methodParameter.getMethod()).getReturnType().equals(Void.TYPE)
                && MappingJackson2HttpMessageConverter.class.isAssignableFrom(clazz);
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> clazz,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        if (body == null) {
            return responseFactory.newSuccessInstance();
        } else if (body instanceof Response) {
            return body;
        } else {
            return responseFactory.newSuccessInstance(body);
        }
    }
}
