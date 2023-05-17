package com.gaoap.mvc.customized.response.data;


public interface Response {

    void setStatus(ResponseStatus responseStatus);

    ResponseStatus getStatus();

    /**
     * 设置响应数据.
     *
     * @param payload 设置的响应数据.
     */
    void setPayload(Object payload);

    /**
     * 获得响应数据.
     *
     * @return
     */
    Object getPayload();

//
//    /**
//     * 设置数据校验异常提示信息
//     *
//     * @param ves
//     */
//    void setVes(List<String> ves);
//
//    /**
//     * 获取数据校验异常提示
//     *
//     * @return
//     */
//    List<String> getVes();
}
