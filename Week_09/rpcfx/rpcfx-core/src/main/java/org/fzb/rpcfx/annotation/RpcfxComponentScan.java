package org.fzb.rpcfx.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(RpcfxComponentScanRegistrar.class)
public @interface RpcfxComponentScan {
}
