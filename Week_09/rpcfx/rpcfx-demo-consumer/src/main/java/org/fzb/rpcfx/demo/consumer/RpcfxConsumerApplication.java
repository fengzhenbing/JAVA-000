package org.fzb.rpcfx.demo.consumer;

import org.fzb.rpcfx.annotation.EnableRpcfx;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * RpcfxConsumerApplication
 *
 * @author fengzhenbing
 */
@SpringBootApplication
@EnableRpcfx
public class RpcfxConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(RpcfxConsumerApplication.class);
    }
}
