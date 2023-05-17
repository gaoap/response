package com.gaoap.mvc.customized.response.factory.defaults;


import com.gaoap.mvc.customized.response.data.Response;
import com.gaoap.mvc.customized.response.data.ResponseStatus;
import com.gaoap.mvc.customized.response.factory.ResponseFactory;
import com.gaoap.mvc.customized.response.factory.ResponseStatusFactory;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

/**
 * 提供的默认的ResponseBeanFactory实现.
 */
@Slf4j
public class DefaultResponseFactory implements ResponseFactory {

    @Resource
    private ResponseStatusFactory responseStatusFactory;

    @Resource
    private Response response;


    @Override
    public Response newInstance(ResponseStatus responseStatus) {
        response.setStatus(responseStatus);
        return response;
    }

    @Override
    public Response newSuccessInstance() {
        response.setStatus(responseStatusFactory.defaultSuccess());
        return response;
    }

    @Override
    public Response newSuccessInstance(Object payload) {
        response.setStatus(responseStatusFactory.defaultSuccess());
        response.setPayload(payload);
        return response;
    }

    @Override
    public Response newFailInstance() {
        response.setStatus(responseStatusFactory.defaultFail());
        return response;
    }

}
