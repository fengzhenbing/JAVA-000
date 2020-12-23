package org.fzb.rpcfx.config;

import lombok.Data;

/**
 * RegistryConfig
 *
 * @author fengzhenbing
 */
@Data
public class RegistryConfig {
    /**
     * Register center address
     */
    private String address;

    /**
     * Username to login register center
     */
    private String username;

    /**
     * Password to login register center
     */
    private String password;
}
