package com.myprojects.loadbalancer;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeightedRoundRobinLoadBalancerTest {

    private final List<String> servers = List.of("Server1", "Server2", "Server3");

    @ParameterizedTest
    @MethodSource("weights")
    void getNextServer(
        List<Integer> weights,
        List<String> expected
    ) {
        WeightedRoundRobinLoadBalancer weightedRoundRobinLoadBalancer = new WeightedRoundRobinLoadBalancer(servers, weights);

        List<String> actual = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            actual.add(weightedRoundRobinLoadBalancer.getNextServer());
        }

        assertEquals(expected, actual);
    }

    static Stream<Arguments> weights() {
        return Stream.of(
            Arguments.of(
                asList(5, 1, 1),
                asList(
                    "Server1",
                    "Server1",
                    "Server1",
                    "Server1",
                    "Server1",
                    "Server2",
                    "Server3"
                )
            ),
            Arguments.of(
                asList(1, 1, 1),
                asList(
                    "Server1",
                    "Server2",
                    "Server3",
                    "Server1",
                    "Server2",
                    "Server3",
                    "Server1"
                )
            ),
            Arguments.of(
                asList(3, 2, 1),
                asList(
                    "Server1",
                    "Server1",
                    "Server2",
                    "Server1",
                    "Server2",
                    "Server3",
                    "Server1"
                )
            )
        );
    }
}