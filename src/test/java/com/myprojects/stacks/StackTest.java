package com.myprojects.stacks;

import org.junit.jupiter.api.Test;

import java.security.spec.NamedParameterSpec;

import static org.junit.jupiter.api.Assertions.*;

public class StackTest {

    private final Stack<Integer> stack = new Stack<>(6, 23, 52);

    @Test
    void push() {
        stack.push(8);
        stack.push(1);

        assertArrayEquals(
            new Integer[]{1, 8, 6, 23, 52},
            stack.printStack()
        );
    }

    @Test
    void pop() {
        int result = stack.pop();

        assertEquals(6, result);

        assertArrayEquals(
            new Integer[]{23, 52},
            stack.printStack()
        );
    }

    @Test
    void top() {
        int top = stack.top();

        assertEquals(6, top);

        assertArrayEquals(
            new Integer[]{6, 23, 52},
            stack.printStack()
        );
    }

    @Test
    void equals() {
        Stack another = new Stack(6, 23, 52);
        Stack notEqual = new Stack(6, 23);
        Stack notEqual2 = new Stack(6, 23, 51);

        assertEquals(stack, another);
        assertNotEquals(stack, notEqual);
        assertNotEquals(stack, notEqual2);
    }
}
