package org.fzb.rpcfx.demo.provider;


import org.fzb.rpcfx.annotation.EnableRpcfx;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * RpcfxProviderApplication
 *
 * @author fengzhenbing
 */
@SpringBootApplication
@EnableRpcfx
public class RpcfxProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(RpcfxProviderApplication.class);
    }
}
