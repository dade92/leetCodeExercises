package com.myprojects.bloomfilter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BloomFilterTest {
    private BloomFilter<String> bloomFilter;

    @BeforeEach
    void setUp() {
        Function<String, Integer> hash1 = String::hashCode;
        Function<String, Integer> hash2 = s -> s.getBytes(StandardCharsets.UTF_8).length;

        bloomFilter = new BloomFilter<>(1000, 3, hash1, hash2);
    }

    @Test
    void testAddAndMightContain() {
        bloomFilter.add("Hello");
        bloomFilter.add("World");

        assertTrue(bloomFilter.mightContain("Hello"));
        assertTrue(bloomFilter.mightContain("World"));
        assertFalse(bloomFilter.mightContain("Bloom"));
        assertFalse(bloomFilter.mightContain("Java"));
    }

    @Test
    void testFalsePositives() {
        bloomFilter.add("Test");

        assertFalse(bloomFilter.mightContain("NotInFilter"));
    }

    @Test
    void testCollisionHandling() {
        bloomFilter.add("CollisionTest1");
        bloomFilter.add("CollisionTest2");

        assertTrue(bloomFilter.mightContain("CollisionTest1"));
        assertTrue(bloomFilter.mightContain("CollisionTest2"));
    }

    @Test
    void testMultipleHashFunctions() {
        bloomFilter.add("MultiHash");

        assertTrue(bloomFilter.mightContain("MultiHash"));
    }
}
