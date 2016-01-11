package com.leetcode.number11;


/**
 *Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
 * Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 *
 * Thinking:
 * (1) the SMALLEST one will harm the total area!
 * (2) Find the smallest one and calculate the maxArea when it is involved!
 * (3) Then, recursively, calculate its left part and right part, in both the smallest one will not be involved!
 * (4) the maxLeft and maxRight and the maxArea will be the final competition. return the max one, this is what we want!
 *
 * Created by Peixin Lu on 15/9/14.
 */
public class Solution {
    /**
     * Divide and Conquer
     * @param height
     * @return
     */
    public static int maxArea(int[] height) {
        return maxArea(height, 0, height.length - 1);
    }

    /**
     * the Recursive method
     * @param height the given array
     * @param start the start point of the sequence which will be checked
     * @param end the end point of the sequence which will be checked
     * @return
     */
    public static int maxArea(int[] height, int start, int end) {

        if(start == end) return 0;//base condition.

        /**
         * find the SMALLEST one among start and end
         */
        int smallest = Integer.MAX_VALUE;
        int smallpos = 0; // origin pos is out the array
        for(int i = start; i <= end; i++) {
            if(height[i] < smallest) {
                smallest = height[i];
                smallpos = i;
            }
        }
        int tmpMaxArea = smallest * (end - start);
        int leftArea = 0;
        int rightArea = 0;
        /**
         * divide by the smallest
         */
        if(smallpos != start && smallpos != end) {
            // smallpos is inside of current sequence
            leftArea = maxArea(height, start, smallpos - 1);
            rightArea = maxArea(height, smallpos + 1, end);
        } else if(smallpos == start) {
            rightArea = maxArea(height, smallpos + 1, end);
        } else {
            leftArea = maxArea(height, start, smallpos - 1);
        }

        /**
         * return the max one
         */
        return findMax(tmpMaxArea, leftArea, rightArea);
    }

    public static int findMax(int a, int b, int c) {
        int tmp = Math.max(a, b);
        return tmp >= c ? tmp : c;
    }


    /**
     * 这个思路简直碉堡了!
     * @param height
     * @return
     */
    public static int maxAreaLinear(int[] height) {
        if(height.length==0 || height ==null) return 0;
        int maxArea = 0;
        int left = 0;
        int right = height.length - 1;

        while (left < right) {
            int minHeight = Math.min(height[left], height[right]);
            maxArea = Math.max(maxArea, minHeight * (right - left));
            if(height[left] <= height[right]) left++;
            else right--;
        }
        return maxArea;
    }

    public static void main(String[] args) {

        System.out.println(maxAreaLinear(new int[]{1,2,4,3}));
    }
}
