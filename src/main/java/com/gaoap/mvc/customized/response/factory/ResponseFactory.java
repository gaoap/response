package com.gaoap.mvc.customized.response.factory;


import com.gaoap.mvc.customized.response.data.Response;
import com.gaoap.mvc.customized.response.data.ResponseStatus;

/**
 * ResponseBean的工厂类，用于生成ResponseBean.
 */
public interface ResponseFactory {


    /**
     * 创建新的空响应.
     *
     * @param statusLine 响应行信息.
     * @return
     */
    Response newInstance(ResponseStatus statusLine);

    /**
     * 创建新的响应.
     *
     * @return
     */
    Response newSuccessInstance();

    /**
     * 从数据中创建成功响应.
     *
     * @param data 响应数据.
     * @return
     */
    Response newSuccessInstance(Object data);

    /**
     * 创建新的失败响应.
     *
     * @return
     */
    Response newFailInstance();

}
