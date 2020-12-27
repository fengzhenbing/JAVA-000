package org.fzb.rpcfx.annotation;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import static org.fzb.rpcfx.util.AnnotatedBeanDefinitionRegistryUtils.registerBeans;

/**
 * RpcfxComponentScanRegistrar
 *
 * @author fengzhenbing
 */
public class RpcfxComponentScanRegistrar  implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        registerBeans(registry, ServiceRegisterPostProcessor.class);
        registerBeans(registry, ServiceDiscoverPostProcessor.class);
    }
}
