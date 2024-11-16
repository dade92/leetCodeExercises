package com.myprojects.loadbalancer;

import com.myprojects.lists.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoundRobinLoadBalancerTest {

    private final List<String> servers = new List<>("Server1", "Server2", "Server3");

    private final RoundRobinLoadBalancer roundRobinLoadBalancer = new RoundRobinLoadBalancer(servers);

    @Test
    void getNextServer() {
        List<String> actual = new List<>();
        List<String> expected = new List<>(
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
