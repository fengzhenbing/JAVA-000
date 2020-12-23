package org.fzb.rpcfx.config;

import lombok.Data;

/**
 * RegistryConfig
 *
 * @author fengzhenbing
 */
@Data
public class RegistryConfig extends AbstractConfig{

    String PASSWORD_KEY = "password";

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

    public void setPassword(String password) {
        checkLength(PASSWORD_KEY, password);
        this.password = password;
    }
}
