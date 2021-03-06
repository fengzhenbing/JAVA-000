package org.fzb.gateway.loadbalancer;

public interface IRule {
    public Server choose(Object key);

    public void setLoadBalancer(ILoadBalancer lb);

    public ILoadBalancer getLoadBalancer();
}
