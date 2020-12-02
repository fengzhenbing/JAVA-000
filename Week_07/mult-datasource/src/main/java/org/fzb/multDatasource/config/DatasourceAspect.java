package org.fzb.multDatasource.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Description
 * @Author fzb
 * @date 2020.09.23 18:11
 */
@Component
@Aspect
public class DatasourceAspect {

    @Before(value = "execution(* org.fzb.multDatasource.mapper..*(..)) ")
    //@Before(value = "execution(* org.feng.dynamicdatasource.service..*(..)) || execution(* org.feng.dynamicdatasource.mapper..*(..)) ")
    public void setDatasouceBeforeExecuteService(JoinPoint joinPoint) throws NoSuchMethodException {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
      //  Method method = joinPoint.getTarget().getClass().getMethod(interfaceMethod.getName(),interfaceMethod.getParameterTypes());
        if(method.isAnnotationPresent(Datasourcekey.class)){
            Datasourcekey datasourcekey = method.getAnnotation(Datasourcekey.class);
            DynamicDataSourceConfig.datasourceKeyHolder.set(datasourcekey.value());
        }else {
            DynamicDataSourceConfig.datasourceKeyHolder.set(Const.DATASOURCE_MASTER);
        }

    }
}
