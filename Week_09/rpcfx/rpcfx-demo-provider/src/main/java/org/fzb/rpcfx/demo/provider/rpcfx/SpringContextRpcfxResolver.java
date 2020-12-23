package org.fzb.rpcfx.demo.provider.rpcfx;

import org.fzb.rpcfx.api.RpcfxResolver;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * SpringContextRpcfxResolver
 *
 * @author fengzhenbing
 */
public class SpringContextRpcfxResolver implements RpcfxResolver , ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public Object resolve(String serviceClass) {
        return applicationContext.getBean(serviceClass);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
