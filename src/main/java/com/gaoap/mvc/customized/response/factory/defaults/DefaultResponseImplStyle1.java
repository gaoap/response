package com.gaoap.mvc.customized.response.factory.defaults;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gaoap.mvc.customized.response.data.Response;
import com.gaoap.mvc.customized.response.data.ResponseStatus;
import lombok.Data;

import java.util.Collections;

@Data
public class DefaultResponseImplStyle1 implements Response {

    private String code;

    private String msg;

    private Object data = Collections.emptyMap();

    @Override
    public void setStatus(ResponseStatus statusLine) {
        this.code = statusLine.getCode();
        this.msg = statusLine.getMsg();
    }

    @Override
    @JsonIgnore
    public ResponseStatus getStatus() {
        return null;
    }

    @Override
    public void setPayload(Object payload) {
        this.data = payload;
    }

    @Override
    @JsonIgnore
    public Object getPayload() {
        return null;
    }


}
