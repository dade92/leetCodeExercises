package com.myprojects.loadbalancer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RoundRobinLoadBalancerTest {

    private final List<String> servers = Arrays.asList("Server1", "Server2", "Server3");

    private final RoundRobinLoadBalancer roundRobinLoadBalancer = new RoundRobinLoadBalancer(servers);

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
            actual.add(roundRobinLoadBalancer.getNextServer());
        }

        assertEquals(expected, actual);
    }
}
