package org.fzb.rpcfx.server;

import org.fzb.rpcfx.registry.RegistryClient;

/**
 * ServiceRegister
 *
 * @author fengzhenbing
 */
public interface ServiceRegister {
    void registerService(RegistryClient client, String service);
}
