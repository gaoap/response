package com.gaoap.mvc.customized.response.factory;

import com.gaoap.mvc.customized.response.data.ResponseStatus;

public interface ValidationResponseStatusFactory {

    public ResponseStatus makeResponseStatus(Throwable throwable);
}
