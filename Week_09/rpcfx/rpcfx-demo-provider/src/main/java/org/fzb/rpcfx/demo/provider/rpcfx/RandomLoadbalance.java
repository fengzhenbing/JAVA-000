package org.fzb.rpcfx.demo.provider.rpcfx;

import lombok.extern.slf4j.Slf4j;
import org.fzb.rpcfx.api.LoadBalancer;

import java.util.List;

/**
 * RandomLoadbalance
 *
 * @author fengzhenbing
 */
@Slf4j
public class RandomLoadbalance implements LoadBalancer {
    @Override
    public String select(List<String> urls) {
        int index = (int) (Math.random() * urls.size());
        String targetUrl = urls.get(index);
        log.info("select index -> {},url -> {}", index, targetUrl);
        return targetUrl;
    }
}
