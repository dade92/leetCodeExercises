package com.myprojects.stacks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StackTest {

    private final Stack stack = new Stack(6, 23, 52);

    @Test
    void push() {
        stack.push(8);
        stack.push(1);

        assertArrayEquals(
            new int[]{1, 8, 6, 23, 52},
            stack.printStack()
        );
    }

    @Test
    void pop() {
        int result = stack.pop();

        assertEquals(6, result);

        assertArrayEquals(
            new int[]{23, 52},
            stack.printStack()
        );
    }

    @Test
    void top() {
        int top = stack.top();

        assertEquals(6, top);

        assertArrayEquals(
            new int[]{6, 23, 52},
            stack.printStack()
        );
    }
}
