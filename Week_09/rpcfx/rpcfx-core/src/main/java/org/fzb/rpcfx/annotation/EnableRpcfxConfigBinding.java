package org.fzb.rpcfx.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * EnableRpcfxConfigBinding
 *
 * @author fengzhenbing
 */
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Repeatable(EnableRpcfxConfigBindings.class)
//@Import(RpcfxConfigBindingRegistrar.class)
public @interface EnableRpcfxConfigBinding {
    String prefix();
    Class<?> type();
}
