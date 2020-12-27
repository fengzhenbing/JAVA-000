package org.fzb.rpcfx.annotation;

import com.google.common.collect.Streams;
import org.fzb.rpcfx.config.AbstractConfig;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.Assert;

import java.util.Map;

import static org.fzb.rpcfx.util.AnnotatedBeanDefinitionRegistryUtils.registerBeans;
import static org.fzb.rpcfx.util.PropertySourcesUtils.getPrefixedProperties;

/**
 * RpcfxConfigBindingsRegistrar
 *
 * @author fengzhenbing
 */
public class RpcfxConfigBindingsRegistrar  implements ImportBeanDefinitionRegistrar, EnvironmentAware {
    private ConfigurableEnvironment environment;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(
                importingClassMetadata.getAnnotationAttributes(EnableRpcfxConfigBindings.class.getName()));

        AnnotationAttributes[] annotationAttributes = attributes.getAnnotationArray("value");

        registerBeans(registry, RpxfxConfigBindingBeanPostProcessor.class);

        for (AnnotationAttributes element : annotationAttributes) {

            String prefix = environment.resolvePlaceholders(element.getString("prefix"));

            Class<? extends AbstractConfig> configClass = element.getClass("type");

            Map<String, Object> properties = getPrefixedProperties(environment.getPropertySources(), prefix);

            registerBeans(registry, configClass);
        }

    }

    @Override
    public void setEnvironment(Environment environment) {
        Assert.isInstanceOf(ConfigurableEnvironment.class, environment);
        this.environment = (ConfigurableEnvironment) environment;
    }
}
