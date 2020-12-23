package org.fzb.rpcfx.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * EnableRpcfxConfig
 *
 * @author fengzhenbing
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Import(RpcfxConfigConfigurationRegistrar.class)
public @interface EnableRpcfxConfig {
}
