package com.gaoap.mvc.customized.response.factory;


import com.gaoap.mvc.customized.response.data.ResponseStatus;

public interface ResponseStatusFactory {
    /**
     * 获得响应成功的ResponseMeta.
     *
     * @return
     */
    ResponseStatus defaultSuccess();

    /**
     * 获得失败的ResponseMeta.
     *
     * @return
     */
    ResponseStatus defaultFail();


    /**
     * 从code和msg创建ResponseStatus
     *
     * @param code
     * @param msg
     * @return
     */
    ResponseStatus newInstance(String code, String msg);

}
