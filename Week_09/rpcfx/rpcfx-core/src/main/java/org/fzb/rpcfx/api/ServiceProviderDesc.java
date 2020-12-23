package org.fzb.rpcfx.api;

import lombok.Builder;
import lombok.Data;

/**
 * ServiceProviderDesc
 *
 * @author fengzhenbing
 */
@Data
@Builder
public class ServiceProviderDesc {

    private String host;
    private Integer port;
    private String serviceClass;

    // group
    // version
}
