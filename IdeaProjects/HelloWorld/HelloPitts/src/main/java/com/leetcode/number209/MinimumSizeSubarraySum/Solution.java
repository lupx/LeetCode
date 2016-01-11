package com.leetcode.number209.MinimumSizeSubarraySum;

import java.util.*;

/**
 * Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the sum ≥ s. If there isn't one, return 0 instead.
 * For example, given the array [2,3,1,2,4,3] and s = 7,
 * the subarray [4,3] has the minimal length under the problem constraint.
 *
 * Created by Peixin Lu on 15/12/23.
 */
public class Solution {

    /**
     * 两个指针模拟滑动窗口.
     * 起初,两个指针(i,j)都在0位置, i指针往前滑动, 同时计算thisSum.
     *   当thisSum < s的时候, i继续移动
     *   当thisSum > s的时候, 记录当前subArray长度, 然后j指针往前滑动, 同时计算thisSum, 并且必要时更新长度
     * 当i指针走到末尾时候, 只移动j指针,同时在j指针移动使得thisSum < s的时候, 算法结束
     *
     * 速度较慢, beat 8%
     * @param s
     * @param nums
     * @return
     */
    public static int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1 && nums[0] < s) return 0;
        int i = 0, j = 0;
        int thisSum = 0;
        int thisLength = 0;
        int minLength = Integer.MAX_VALUE;
        for (; i < nums.length; i++) {//i moves forward
            thisSum += nums[i];
            for (; j <= i && thisSum >= s; j++) {
                    thisLength = i - j + 1;
                    minLength = Math.min(minLength, thisLength);
                    thisSum -= nums[j];//窗口大小减去j边
            }
        }
        for (; j < nums.length; j++) {//i is at the end, moves j only
            if (thisSum >= s) {
                thisLength = i - j + 1;
                minLength = Math.min(minLength, thisLength);
                thisSum -= nums[j];
            }
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }


    /**
     * 真正的O(n)时间!
     * 在上面算法的基础上进行了优化
     * 本质是, thisSum不够s的时候, 窗口扩大, thisSum 够s的时候, 窗口缩小
     * 直到最终, 窗口扩大的前指针达到了nums最大范围, 循环结束
     * beat 18.69%
     * @param s
     * @param nums
     * @return
     */
    public static int minSubArrayLenOn(int s, int[] nums) {
        int head = 0, tail = 0;
        int thisSum = 0;
        int minLength = Integer.MAX_VALUE;
        while (tail < nums.length) {//窗口尾达到nums最大位,算法彻底结束
            if (thisSum < s && head < nums.length) {
                thisSum += nums[head];
                head++;
            } else if (thisSum >= s) {
                minLength = Math.min(minLength, head - tail);
                thisSum -= nums[tail];
                tail++;
            } else {
                //head reaches the end of the nums
                //break
                break;
            }
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }


    public static void main(String[] args) {
        System.out.println(minSubArrayLenOn(7, new int[]{7}));
    }
}
