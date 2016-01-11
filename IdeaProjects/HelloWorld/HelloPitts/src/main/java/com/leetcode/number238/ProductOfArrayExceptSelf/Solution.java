package com.leetcode.number238.ProductOfArrayExceptSelf;

/**
 *
 * Given an array of n integers where n > 1, nums,
 *   return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
 * Solve it without division and in O(n). 不能做除法, 在O(n)时间内搞定.
 * For example, given [1,2,3,4], return [24,12,8,6].
 *
 * Follow up:
 * Could you solve it with constant space complexity?
 * (Note: The output array does not count as extra space for the purpose of space complexity analysis.)
 * FollowUp, 能不能在常数空间内搞定?
 * Created by Peixin Lu on 16/1/1.
 */
public class Solution {

    /**
     * 定义两个数组, leftPro[i]代表从0到i-1的乘积, rightPro[i]代表从i+1到n-1的乘积
     * 这是O(1)空间的做法, 非常巧妙!
     * beat 48.83%
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        if (nums == null) return null;
        int res[] = new int[nums.length];
        int leftProduct = 1;
        for (int i = 0; i < nums.length; i++) {//Calculate leftProduct
            res[i] = leftProduct;
            leftProduct *= nums[i];// products[0][i-1]
        }

        int rightProduct = 1;
        for (int i = nums.length - 1; i >= 0; i--) {// Calculate rightProduct
            res[i] *= rightProduct;
            rightProduct *= nums[i];// products[i+1][n-1]
        }
        return res;
    }

    public static void main(String[] args) {
    }
}
