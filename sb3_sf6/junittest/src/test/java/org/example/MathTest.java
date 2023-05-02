package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MathTest {

    private Math math = new Math();

    @Test
    void calculateSum123() {
        int result = math.calcSum(new int[] {1,2,3});

        int expected = 6;
        assertEquals(expected, result);
    }

    @Test
    void calculateSumEmpty() {
        int result = math.calcSum(new int[] {});

        int expected = 0;
        assertEquals(expected, result);
    }
}