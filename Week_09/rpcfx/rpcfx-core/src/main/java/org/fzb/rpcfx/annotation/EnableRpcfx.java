package org.fzb.rpcfx.annotation;

import java.lang.annotation.*;

/**
 * EnableRpcfx
 *
 * @author fengzhenbing
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@EnableRpcfxConfig
@RpcfxComponentScan
public @interface EnableRpcfx {
}
