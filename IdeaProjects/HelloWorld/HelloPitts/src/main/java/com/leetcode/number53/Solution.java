package com.leetcode.number53;


/**
 * leetcode 53: 给一个数组, 求最大子序列和.
 * Created by PeixinLu on 15/10/11.
 */
public class Solution {

    /**
     * O(n)时间DP算法
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {
        int thisSum = 0 ;
        int maxSum = Integer.MIN_VALUE;
//        int maxOne = Integer.MIN_VALUE;
        if (nums.length == 1) {
            return nums[0];
        }
        for (int i = 0; i < nums.length; i++) {
            thisSum += nums[i];
            if (thisSum > maxSum) {
                maxSum = thisSum;
            }
            if (thisSum < 0) {
                thisSum = 0;
            }
        }
        return maxSum;
    }

    public static void main (String[] args) {
        int[] a = new int[]{-2, -1};
        System.out.println(maxSubArray(a));
    }
}
