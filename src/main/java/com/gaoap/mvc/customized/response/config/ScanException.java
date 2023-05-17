package com.gaoap.mvc.customized.response.config;

import com.gaoap.mvc.customized.response.annotate.ExceptionAliasFor;
import com.gaoap.mvc.customized.response.annotate.enable.ScanCustomizedException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ScanException implements ImportBeanDefinitionRegistrar, EnvironmentAware {
    private String basePackage_ = "com.gaoap.mvc.customized.response";
    private Environment environment;
    private ConcurrentHashMap<Class<? extends Throwable>, ExceptionAliasFor> aliasForMap = new ConcurrentHashMap<>();

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        //聚合扫描的路径
        Set<String> basePackages = getBasePackages(importingClassMetadata);
        basePackages.add(basePackage_);
        //获取扫描器
        ClassPathScanningCandidateComponentProvider scanner = getScanner();

        //扫描并注册
        for (String basePackage : basePackages) {
            Set<BeanDefinition> candidateComponents = scanner.findCandidateComponents(basePackage);
            for (BeanDefinition bean : candidateComponents) {
                Class<?> clazz = null;
                try {
                    clazz = ClassUtils.forName(bean.getBeanClassName(), this.getClass().getClassLoader());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                ExceptionAliasFor er = AnnotationUtils.findAnnotation(clazz, ExceptionAliasFor.class);
                aliasForMap.put(er.aliasFor(), er);
            }
        }
        registry.registerBeanDefinition("exceptionAliasRegister", BeanDefinitionBuilder.genericBeanDefinition(FactoryBeanExceptionAliasRegister.class)
                .addPropertyValue("aliasForMap", aliasForMap)
                .getBeanDefinition());

    }

    private ClassPathScanningCandidateComponentProvider getScanner() {
        //不使用默认的过滤器
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false, environment);
        scanner.addIncludeFilter(new AnnotationTypeFilter(ExceptionAliasFor.class));
        return scanner;
    }

    /**
     * 获取扫描组件的包路径
     */
    protected Set<String> getBasePackages(AnnotationMetadata importingClassMetadata) {
        Map<String, Object> attributes = importingClassMetadata
                .getAnnotationAttributes(ScanCustomizedException.class.getCanonicalName());

        Set<String> basePackages = new HashSet<>();

        for (String pkg : (String[]) attributes.get("basePackages")) {
            System.out.println("basePackages:" + pkg);
            if (StringUtils.hasText(pkg)) {
                basePackages.add(pkg);
            }
        }
        for (Class<?> clazz : (Class<?>[]) attributes.get("basePackageClasses")) {
            basePackages.add(ClassUtils.getPackageName(clazz));
        }

        if (basePackages.isEmpty()) {
            basePackages.add(
                    ClassUtils.getPackageName(importingClassMetadata.getClassName()));
        }
        return basePackages;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}