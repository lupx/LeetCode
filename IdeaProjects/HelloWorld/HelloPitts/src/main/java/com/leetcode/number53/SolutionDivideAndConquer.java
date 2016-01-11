package com.leetcode.number53;

import java.util.Arrays;

/**
 *
 * Created by PeixinLu on 15/10/11.
 */
public class SolutionDivideAndConquer {

    /**
     * 分治解法.
     * 先分, 求左右, 左右求出最大, 最后和中间的比大小.
     *
     * @param nums
     */
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        if (n == 1)  return nums[0];
        int mid = n / 2;

        int RightMSS = maxSubArray(Arrays.copyOfRange(nums, mid, n));
        int LeftMSS = maxSubArray(Arrays.copyOfRange(nums, 0, mid));

        int LeftSum=Integer.MIN_VALUE;
        int RightSum=Integer.MIN_VALUE;

        int Rsum=0;
        for(int i = mid; i < n; i++){
            Rsum += nums[i];
            RightSum = Math.max(RightSum, Rsum);
        }
        int Lsum = 0;
        for(int i = mid - 1; i >= 0; i--){
            Lsum += nums[i];
            LeftSum = Math.max(LeftSum, Lsum);
        }
        int ans = Math.max(LeftMSS, RightMSS);
        return Math.max(ans, LeftSum + RightSum);
    }
}
