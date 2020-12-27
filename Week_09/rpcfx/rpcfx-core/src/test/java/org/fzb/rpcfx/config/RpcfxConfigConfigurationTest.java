package org.fzb.rpcfx.config;

import org.fzb.rpcfx.AbstractConfigurationTest;
import org.fzb.rpcfx.annotation.RpcfxConfigConfiguration;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * ApplicationConfigTest
 *
 * @author fengzhenbing
 */
public class RpcfxConfigConfigurationTest extends AbstractConfigurationTest {

    @Test
    public void testRpcfxConfigConfiguration(){
        final String name = "rpcfx-provider";
        final String[] inlinedProperties = new String[]{
            "rpcfx.application.name=" + name,
        };
        load(RpcfxConfigConfiguration.class, inlinedProperties);
        ApplicationConfig properties = getContext().getBean(ApplicationConfig.class);
        assertThat(properties.getName(), is(name));
    }
}
