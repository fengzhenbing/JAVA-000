package org.fzb.rpcfx.config;

import lombok.Data;

/**
 * ConsumerConfig
 *
 * @author fengzhenbing
 */
@Data
public class ConsumerConfig {
    /**
     * Networking framework client uses: netty, httpclient, etc.
     */
    private String client;
}
