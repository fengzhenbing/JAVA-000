package org.fzb.gateway.loadbalancer;

import java.util.List;

public interface ILoadBalancer {

    public void addServers(List<Server> newServers);

    public Server chooseServer(Object key);

    public List<Server> getAllServers();
}
