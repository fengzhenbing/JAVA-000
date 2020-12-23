package org.fzb.rpcfx.config;

import lombok.Data;

/**
 * ProviderConfig
 *
 * @author fengzhenbing
 */
@Data
public class ProviderConfig {
    /**
     * Service ip addresses (used when there are multiple network cards available)
     */
    private String host;

    /**
     * Service port
     */
    private Integer port;

    /**
     * Context path
     */
    private String contextPath;
}
