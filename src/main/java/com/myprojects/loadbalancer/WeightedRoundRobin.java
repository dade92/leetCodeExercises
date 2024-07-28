package com.myprojects.loadbalancer;

import java.util.List;

public class WeightedRoundRobin {
    private final List<String> servers;
    private final List<Integer> weights;
    private int currentIndex;
    private int currentWeight;

    public WeightedRoundRobin(List<String> servers, List<Integer> weights) {
        this.servers = servers;
        this.weights = weights;
        this.currentIndex = -1;
        this.currentWeight = 0;
    }

    public String getNextServer() {
        while (true) {
            currentIndex = (currentIndex + 1) % servers.size();
            if (currentIndex == 0) {
                currentWeight--;
                if (currentWeight <= 0) {
                    currentWeight = getMaxWeight();
                }
            }
            if (weights.get(currentIndex) >= currentWeight) {
                return servers.get(currentIndex);
            }
        }
    }

    private int getMaxWeight() {
        return weights.stream().max(Integer::compare).orElse(0);
    }
}
