package com.leetcode.number153;

import java.util.ArrayList;
import java.util.List;

/**
 * Find the contiguous subarray within an array (containing at least one number) which has the largest product.
 * For example, given the array [2,3,-2,4],
 * the contiguous subarray [2,3] has the largest product = 6.
 * Created by Peixin Lu on 15/11/23.
 */
public class Solution {

    /**
     * DP解法:
     * 巧妙至极!
     * Math.max()的比较,可以直接把0的情况考虑进去
     * 遇到负数, 结果反转
     *
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        int min;
        int res = nums[0], max = min = nums[0]; // max, min means max and min product among the subarrays whose last element is A[i].
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > 0) {
                max = Math.max(max * nums[i], nums[i]);
                min = Math.min(min * nums[i], nums[i]);
            }
            else {
                int lastMax = max;
                max = Math.max(min * nums[i], nums[i]);
                min = Math.min(lastMax * nums[i], nums[i]);
            }
            res = Math.max(res, max);
        }
        return res;
    }


    /**
     * 换个思路解, 分治:
     * 乘积最大, 只要包含的负数为偶数个,且没有0, 子数组越长, 乘积越大
     * 首先找0, 然后根据0的位置把数组分割成若干区间, 然后对各个子区间求解:
     *  对每个子区间找负数个数和首尾负数, 计算该区间最大积, 更新到max
     *  最后所有区间遍历完, 得到max, return
     *
     *  beats 93.76%
     * @param nums
     * @return
     */
    public static int maxProduct_v2(int[] nums) {
        if (nums == null && nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        List<Integer> zeroIndex = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeroIndex.add(i);
            }
        }

        int max = 0;
        int start = 0;
        for (int i = 0; i <= zeroIndex.size(); i++) {
            if (i < zeroIndex.size()) {
                max = Math.max(max, helper(nums, start, zeroIndex.get(i) - 1));
                start = zeroIndex.get(i) + 1;
            } else {
                max = Math.max(max, helper(nums, start, nums.length - 1));
            }
        }
        return max;
    }

    private static int helper(int[] nums, int start, int end) {
        if (start > end) return 0;
        if (start == end) return nums[start];
        int negcount = 0;
        int leftNeg = Integer.MAX_VALUE;
        int rightNeg = Integer.MAX_VALUE;
        int product = 1;
        for (int i = start; i <= end; i++) {
            product *= nums[i];
            if (nums[i] < 0) {
                negcount++;
                if (leftNeg == Integer.MAX_VALUE) {
                    leftNeg = i;
                }
            }
        }
        for (int i = end; i >= start; i--) {
            if (nums[i] < 0) {
                rightNeg = i;
                break;
            }
        }
        //偶数个负数, 直接乘
        if (negcount % 2 == 0) return product;

        //奇数个负数, 分别算左右两边, 取大值
        int left = 1, right = 1;
        for (int i = start; i <= leftNeg; i++) {
            left *= nums[i];
        }
        left = product / left;

        for (int i = end; i >= rightNeg; i--) {
            right *= nums[i];
        }
        right = product / right;

        return Math.max(left, right);
    }

    public static void main(String[] args) {

        int[] nums = new int[]{0};
        System.out.println(maxProduct_v2(nums));
    }

}

