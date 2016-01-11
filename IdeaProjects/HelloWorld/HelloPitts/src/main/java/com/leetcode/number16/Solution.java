package com.leetcode.number16;

import java.util.Arrays;

/**
 * Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target.
 * Return the sum of the three integers. You may assume that each input would have exactly one solution.
 *
 * For example, given array S = {-1 2 1 -4}, and target = 1.
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 * Created by PeixinLu on 15/9/21.
 */
public class Solution {
    /**
     * TLE!!!
     * @param nums
     * @param target
     * @return
     */
    public static int threeSumClosest(int[] nums, int target) {
        int len = nums.length;
        Arrays.sort(nums);

        //算出最大可能和 + 最小可能和, 避免最差情况
        int max = nums[len - 1] + nums[len - 2] + nums[len - 3];
        int min = nums[0] + nums[1] + nums[2];
        if(target >= max) return max;
        if(target <= min) return min;

        int diff = Integer.MAX_VALUE;
        int result = 0;
        for (int i = 0; i < len; i++) {
            int low = i + 1;
            int high = len - 1;
            while (low < high) {
                int sum = nums[i] + nums[low] + nums[high];
                if(Math.abs(target-sum) < diff) { //只要比diff小,就更新diff,同时记录三元组和
                    diff = Math.abs(target-sum);
                    result = sum;
                } else {
                    if(sum > target) high--;
                    else low++;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(threeSumClosest(new int[]{-1,2,1,-4}, 1));
    }
}
