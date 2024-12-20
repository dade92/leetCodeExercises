package com.myprojects.loadbalancer;


import com.myprojects.lists.List;

public class RoundRobinLoadBalancer implements LoadBalancer {

    private final List<String> servers;
    private int currentIndex;

    public RoundRobinLoadBalancer(List<String> servers) {
        this.servers = servers;
        currentIndex = -1;
    }

    @Override
    public String getNextServer() {
        currentIndex = (currentIndex + 1) % servers.size();
        return servers.getAt(currentIndex);
    }
}
