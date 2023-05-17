package com.gaoap.mvc.customized.response.factory.defaults;


import com.gaoap.mvc.customized.response.data.Response;
import com.gaoap.mvc.customized.response.data.ResponseStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;

/**
 * 默认的Response实现
 * 包装成统一响应的JavaBean.
 */
@NoArgsConstructor
@Data
public class DefaultResponseImplStyle0 implements Response {
    private ResponseStatus status;
    private Object payload = Collections.emptyMap();
}
