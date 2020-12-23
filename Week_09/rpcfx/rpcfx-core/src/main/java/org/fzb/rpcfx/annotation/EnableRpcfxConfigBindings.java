package org.fzb.rpcfx.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * EnableRpxfxConfigBindings
 *
 * @author fengzhenbing
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(RpcfxConfigBindingsRegistrar.class)
public @interface EnableRpcfxConfigBindings {
    /**
     * The value of {@link EnableRpcfxConfigBindings}
     *
     * @return non-null
     */
    EnableRpcfxConfigBinding[] value();
}
