package org.fzb.rpcfx.config;

import lombok.Data;

/**
 * ConsumerConfig
 *
 * @author fengzhenbing
 */
@Data
public class ConsumerConfig  extends AbstractConfig{
    public static final String PREFIX = "consumer";
    /**
     * Networking framework client uses: netty, httpclient, etc.
     */
    private String client;


    @Override
    public String getConfigPrefix() {
        return PREFIX;
    }
}
