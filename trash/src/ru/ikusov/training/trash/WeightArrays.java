package ru.ikusov.training.trash;

import java.util.Arrays;

public class WeightArrays {

    private int[] nums;
    private int[] weights;
    private int[] rowweights;
    private int length;

    private int sumweights;

    public WeightArrays(int nums[], int weights[]) {
        length = Math.min(nums.length, weights.length);
        this.nums = Arrays.copyOf(nums, length);
        this.weights = Arrays.copyOf(weights, length);
        sumweights = 0;
        rowweights = new int[length];

        for (int i=0; i<length; i++) {
            sumweights += weights[i];
            rowweights[i] = sumweights;
        }
    }

    public int getElement() {
        int n = 0;

        double rand = sumweights*Math.random();
        while (rand>rowweights[n++]);
        return nums[n-1];
    }
}
