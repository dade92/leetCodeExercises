package com.myprojects.loadbalancer;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LeastConnectionsLoadBalancerTest {

    private final List<String> servers = Arrays.asList("Server1", "Server2", "Server3");

    private LeastConnectionsLoadBalancer leastConnectionsLoadBalancer = new LeastConnectionsLoadBalancer(servers);

    @Test
    void getNextServer() {
        List<String> actual = new ArrayList<>();
        List<String> expected = Arrays.asList(
            "Server1",
            "Server2",
            "Server3",
            "Server1",
            "Server2",
            "Server3",
            "Server1"
        );

        for (int i = 0; i < 7; i++) {
            actual.add(leastConnectionsLoadBalancer.getNextServer());
        }

        assertEquals(expected, actual);
    }
}