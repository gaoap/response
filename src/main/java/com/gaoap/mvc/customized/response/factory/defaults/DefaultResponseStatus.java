package com.gaoap.mvc.customized.response.factory.defaults;


import com.gaoap.mvc.customized.response.data.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 默认的ResponseStatus实现
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DefaultResponseStatus implements ResponseStatus {
    /**
     * 响应码.
     */
    private String code;
    /**
     * 响应信息.
     */
    private String msg;


}
