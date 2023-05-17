package com.gaoap.mvc.customized.response.config;

import com.gaoap.mvc.customized.response.annotate.ExceptionAliasFor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@AllArgsConstructor
public class ExceptionAliasRegister {


    private ConcurrentHashMap<Class<? extends Throwable>, ExceptionAliasFor> aliasForMap = new ConcurrentHashMap<>();


    /**
     * 获取
     *
     * @param tClazz
     * @return
     */
    public ExceptionAliasFor getExceptionAliasFor(Class<? extends Throwable> tClazz) {
        return aliasForMap.get(tClazz);
    }
}
