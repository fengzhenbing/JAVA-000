package org.fzb.rpcfx.demo.provider;


import org.fzb.rpcfx.annotation.EnableRpcfx;
import org.fzb.rpcfx.api.RpcfxRequest;
import org.fzb.rpcfx.api.RpcfxResolver;
import org.fzb.rpcfx.api.RpcfxResponse;
import org.fzb.rpcfx.demo.provider.rpcfx.SpringContextRpcfxResolver;
import org.fzb.rpcfx.server.RpcfxInvoker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * RpcfxProviderApplication
 *
 * @author fengzhenbing
 */
@SpringBootApplication
@EnableRpcfx
@RestController
public class RpcfxProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(RpcfxProviderApplication.class);
    }

    @Autowired
    RpcfxInvoker invoker;

    @PostMapping("/")
    public RpcfxResponse invoke(@RequestBody RpcfxRequest request) {
        return invoker.invoke(request);
    }

    @Bean
    public RpcfxInvoker createInvoker(@Autowired RpcfxResolver resolver) {
        return new RpcfxInvoker(resolver);
    }

    @Bean
    public RpcfxResolver createResolver() {
        return new SpringContextRpcfxResolver();
    }
}
