package com.leetcode.number41;

/**
 * Given an unsorted integer array, find the first missing positive integer.
 * For example,
 * Given [1,2,0] return 3,
 * and [3,4,-1,1] return 2.
 *
 * 要求O(n)时间内完成,且只能用常数空间
 *
 * Created by Peixin Lu on 15/10/5.
 */
public class Solution {

    /**
     * O(n)时间意味着不能预排序
     * 首先肯定是要遍历:
     * 此题tricky的点在于:
     * (1) 由于是乱序, 而要找第一个missing, 肯定要基于一个基本排序完成的数组
     * (2) 这个基本排序的数组可以是个抽象的概念(也只能是个抽象的概念)
     * 那么,我们能否在遍历的过程中, 尽量地把不属于当前位置的元素调整个位置呢?
     * 也即, 尽量让元素归位到应该在的位置上去.
     * 那么, 遍历一遍后, 我们得到的这个数组, 除了归位数字外,全部都是不归位的数字.
     * 顾名思义, 不归位, 所以肯定也不属于当前位置, 我们假设这些数字也被移到某个地方去了.
     * 那么这些位置不就空出来了么? 第一个位置,不就是missing数字了么!
     * 然后,我们在从头到尾扫一遍,找到第一个位置, 然后输出,即为所求.
     *
     * @param nums
     * @return
     */
    public static int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }

        int n = nums.length;
        int i = 0;
//        for (int i = 0; i < n; i++) {
        while (i < n) {
            if (nums[i] >= 0 && nums[i] < n && nums[nums[i]] != nums[i]) {
                swap(nums, i, nums[i]);
            } else {
                i++;
            }
        }//负数不需要移动, 他们本就不属于当前这个位置. 移不移动其实都一样


        int k = 1;
        while ( k < n && nums[k] == k) {
            k++;
        }
        //循环结束后, 要么就是k走到了结尾, 要么就是遇到了第一个不归位数字
        if (k < n) {
            //k中途跳出
            return k;
        } else {
            //k走到了结尾, 那么需要看看nums[0]是否是k
            return nums[0] == k ? k + 1 : k;
        }
    }


    public static void swap(int[] nums, int i, int j) {
        int tmp = nums[j];
        nums[j] = nums[i];
        nums[i] = tmp;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{6,1,2,3,7,5,4,0};
        System.out.println(firstMissingPositive(nums));
    }
}
