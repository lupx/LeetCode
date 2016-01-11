package com.leetcode.number88;


/**
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 * Note:
 * You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
 * The number of elements initialized in nums1 and nums2 are m and n respectively.
 * Created by PeixinLu on 15/10/16.
 */
public class Solution {

    /**
     * nums1有足够的后续空间可以容纳nums2
     * 技巧, 从nums1后面的元素开始和nums2后面的做比较, 大的从后往前放!
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        if (nums2 == null || n == 0) return;
        if (m == 0 && n == 0) return;
        if (m == 0 && n != 0) {
            //把nums2拷贝进nums1即可
            System.arraycopy(nums2, 0, nums1, 0, n);
            return;
        }
        if (n == 0 && m != 0) return;

        int j = n - 1;//j从nums2最后一位开始往前遍历
        int i = m - 1;//i从nums1最后一位开始往前检查
        int k = m + n - 1;//k位就是两边比较结果应该填写的位置
        while (j >= 0 && i >= 0) {
            if (nums2[j] >= nums1[i]) {
                nums1[k] = nums2[j];
                j--;
            } else {
                nums1[k] = nums1[i];
                i--;
            }
            k--;
        }
        //但凡是合并的题,一定要在主循环结束后, 考察是否还有剩余!
        while (j >= 0) {
            nums1[k] = nums2[j];
            j--;
            k--;
        }
        return;
    }


    public static void main(String[] args) {
        int[] n1 = new int[]{0,0};
        int[] n2 = new int[]{1};
        merge(n1, 0, n2, 1);
    }
}

