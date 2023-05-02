package org.example;

public class Math {
    public int calcSum(int[] numbers) {
        int sum = 0;

        for(int number:numbers) {
            sum += number;
        }

        return sum;
    }
}
