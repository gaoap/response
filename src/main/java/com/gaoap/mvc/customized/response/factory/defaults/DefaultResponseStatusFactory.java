package com.gaoap.mvc.customized.response.factory.defaults;


import com.gaoap.mvc.customized.response.config.CustomizedResponseProperties;
import com.gaoap.mvc.customized.response.data.ResponseStatus;
import com.gaoap.mvc.customized.response.factory.ResponseStatusFactory;

import javax.annotation.Resource;

public class DefaultResponseStatusFactory implements ResponseStatusFactory {

    @Resource
    private CustomizedResponseProperties properties;
    @Resource
    private ResponseStatus responseStatus;

    @Override
    public ResponseStatus defaultSuccess() {
        responseStatus.setCode(properties.getDefaultSuccessCode());
        responseStatus.setMsg(properties.getDefaultSuccessMsg());
        return responseStatus;
    }

    @Override
    public ResponseStatus defaultFail() {
        responseStatus.setCode(properties.getDefaultFailCode());
        responseStatus.setMsg(properties.getDefaultFailMsg());
        return responseStatus;
    }

    @Override
    public ResponseStatus newInstance(String code, String msg) {
        responseStatus.setCode(code);
        responseStatus.setMsg(msg);
        return responseStatus;
    }
}
