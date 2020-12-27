package org.fzb.rpcfx.annotation;

import lombok.extern.slf4j.Slf4j;
import org.fzb.rpcfx.config.AbstractConfig;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

import java.util.Map;

import static org.fzb.rpcfx.util.PropertySourcesUtils.getPrefixedProperties;

/**
 * RpxfxConfigBindingBeanPostProcessor
 *
 * @author fengzhenbing
 */
@Slf4j
public class RpxfxConfigBindingBeanPostProcessor implements BeanPostProcessor , EnvironmentAware, PriorityOrdered {

    private ConfigurableEnvironment environment;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof AbstractConfig) {
            AbstractConfig rpcfxConfig = (AbstractConfig) bean;
            Map<String, Object> properties = getPrefixedProperties(environment.getPropertySources(), rpcfxConfig.getFullConfigPrefix());
            rpcfxConfig.bindProperties(rpcfxConfig,properties);
            if(log.isDebugEnabled()){
                log.debug("config -> {}",rpcfxConfig);
            }
        }
        return bean;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = (ConfigurableEnvironment) environment;
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE - 99;
    }
}
