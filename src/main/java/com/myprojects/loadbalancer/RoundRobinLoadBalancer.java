package com.myprojects.loadbalancer;

import java.util.List;

public class RoundRobinLoadBalancer implements LoadBalancer {

    private final List<String> servers;
    private int currentIndex;

    public RoundRobinLoadBalancer(List<String> servers) {
        this.servers = servers;
        currentIndex = -1;
    }

    public String getNextServer() {
        currentIndex = (currentIndex + 1) % servers.size();
        return servers.get(currentIndex);
    }
}
