package com.leetcode.number27;

/**
 * Given an array and a value, remove all instances of that value in place and return the new length.
 * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
 *
 * 去掉数组中与val相同的数字, 并且该位置需要被其他与val不相同的数字补充,最后形成一个紧致数组.
 * 返回去掉val后剩余数字的个数
 * Created by PeixinLu on 15/9/21.
 */
public class Solution {

    /**
     *
     * @param nums
     * @param val
     * @return
     */
    public static int removeElement(int[] nums, int val) {
        if (nums.length < 1) {
            return 0;
        }
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            while (right > left && nums[right] == val)
            {
                right--; //先把结尾的相同数字全舍弃, 直到right指向一个不相同数字
            }
            if (nums[left] == val) {
                nums[left] = nums[right];
                right--;
            }
            left++;
        }
        return right + 1;
    }

    public static void main(String[] args) {
        int[] a = {0,4,4,0,4,4,4,0,2};
        System.out.println(removeElement(a, 4));
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }
}
