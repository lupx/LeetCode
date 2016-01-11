package com.leetcode.number31;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 * The replacement must be in-place, do not allocate extra memory.
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 *
 * Created by PeixinLu on 15/10/1.
 */
public class Solution {
//    private static int[] nums = {1,2,1};
    /**
     * 从倒数第二位开始往前推.
     * 假设当前位为i, 比较i和从i+1开始的所有后续项,
     * i和其中大于i位的最小项交换. 交换后,把i后所有项从小到大排序
     * 如果i+1位<=i为, i--
     * 如果i到达0,仍然找不到任何交换. 直接把nums反转即可
     * @param nums
     */
    public static void nextPermutation(int[] nums) {
        int len = nums.length;
        int tmp;
        for(int i = nums.length - 2; i >= 0; i--) {
            //因为后续数字必然全是从大到小排列的
            //所以从后往前找第一个大于nums[i]的项
            int l = len - 1;
            while(l >= i && nums[l] <= nums[i]) l--;
            if(l < i) continue;//走到i-1,都没有找到合适的, 直接continue

            //交换
            tmp = nums[l];
            nums[l] = nums[i];
            nums[i] = tmp;

            //排序后续数字
            //因为后续数字必然全是从大到小排列的,所以直接翻转
            int left = i + 1;
            int right = len - 1;
            while (left < right) {
                tmp = nums[right];
                nums[right] = nums[left];
                nums[left] = tmp;
                left++;
                right--;
            }
            return;
        }
        //走到这里说明当前数字就是所有排列里最大的,直接翻转数组
        int i = 0;
        int j = len - 1;
        while(i < j) {
            tmp = nums[j];
            nums[j] = nums[i];
            nums[i] = tmp;
            i++;
            j--;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1};
        nextPermutation(nums);
        for(int i =0;i<nums.length;i++){
            System.out.println(nums[i]);
        }
    }
}
