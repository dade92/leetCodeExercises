package com.myprojects.loadbalancer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class WeightedRoundRobinTest {

    List<String> servers = List.of("Server1", "Server2", "Server3");
    List<Integer> weights = List.of(5, 1, 1);

    private WeightedRoundRobin weightedRoundRobin = new WeightedRoundRobin(servers, weights);

    @Test
    void getNextServer() {
        List<String> actual = new ArrayList<>();
        List<String> expected = Arrays.asList(
            "Server1",
            "Server1",
            "Server1",
            "Server1",
            "Server1",
            "Server2",
            "Server3"
        );

        for (int i = 0; i < 7; i++) {
            actual.add(weightedRoundRobin.getNextServer());
        }

        Assertions.assertEquals(expected, actual);
    }
}