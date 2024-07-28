package com.myprojects.loadbalancer;

import java.util.ArrayList;
import java.util.List;

public class LeastConnectionsLoadBalancer implements LoadBalancer {

    private final List<String> servers;
    private ArrayList<Integer> connections;

    public LeastConnectionsLoadBalancer(List<String> servers) {
        this.servers = servers;
        connections = new ArrayList<>(servers.stream().map(s -> 0).toList());
    }

    @Override
    public String getNextServer() {
        int minConnectionIndex = 0;
        int minConnection = connections.get(0);

        for(int i = 0; i < connections.size(); i++) {
            Integer connection = connections.get(i);
            if(connection < minConnection) {
                minConnectionIndex = i;
                minConnection = connection;
            }
        }

        connections.set(minConnectionIndex, minConnection + 1);
        return servers.get(minConnectionIndex);
    }

    public void updateConnection(int serverIndex, int connectionNumber) {
        connections.set(serverIndex, connectionNumber);
    }
}
