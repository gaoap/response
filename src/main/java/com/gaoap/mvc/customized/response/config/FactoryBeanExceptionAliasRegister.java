package com.gaoap.mvc.customized.response.config;

import com.gaoap.mvc.customized.response.annotate.ExceptionAliasFor;
import org.springframework.beans.factory.FactoryBean;

import java.util.concurrent.ConcurrentHashMap;

public class FactoryBeanExceptionAliasRegister implements FactoryBean {
    private ConcurrentHashMap<Class<? extends Throwable>, ExceptionAliasFor> aliasForMap = new ConcurrentHashMap<>();

    public void setAliasForMap(ConcurrentHashMap<Class<? extends Throwable>, ExceptionAliasFor> aliasForMap) {
        this.aliasForMap = aliasForMap;
    }

    @Override
    public Object getObject() throws Exception {
        ExceptionAliasRegister exceptionAliasRegister = new ExceptionAliasRegister(this.aliasForMap);
        System.out.println("init ....ExceptionAliasRegister.....");
        return exceptionAliasRegister;
    }

    @Override
    public Class<?> getObjectType() {
        return ExceptionAliasRegister.class;
    }
}
