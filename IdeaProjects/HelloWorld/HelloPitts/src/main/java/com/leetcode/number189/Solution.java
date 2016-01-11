package com.leetcode.number189;

/**
 * 旋转数组:
 * Rotate an array of n elements to the right by k steps.
 * For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].
 * 要求: 想出尽量多的解决方案
 * Created by Peixin Lu on 15/12/2.
 */
public class Solution {

    /**
     * 方案一: 费空间的笨办法, 直接开辟一个同等空间的数组, 挨个拷贝过去
     * 1ms, beat 12.56%
     *
     * @param nums
     * @param k
     */
    public static void rotateV1(int[] nums, int k) {
        if (nums == null || nums.length == 0) return;
        int n = nums.length;
        if (k > n) k %= n;
        int[] newNums = new int[n];
        //后k个元素,放到newNums前k位里
        int ind = 0;
        for (int i = n - k; i < n; i++) {
            newNums[ind++] = nums[i];
        }
        //前n-k个元素, 放入newNums后n-k位里
        for (int i = 0; i < n - k; i++) {
            newNums[ind++] = nums[i];
        }
        System.arraycopy(newNums, 0, nums, 0, n);
    }

    /**
     * 方案二: 硬做, 硬交换:
     *
     * 一趟遍历, i元素和i+k元素交互
     * 当i+k>=n-1的时候, i和n-1交换
     *
     * 这个思路有问题, 暂时没实现...
     * @param nums
     * @param k
     */
    public static void rotateV2(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) return;
        int n = nums.length;
        k = n - k % n;
        for (int i = 0; i < n; i++) {
            if (i + k < n - 1) {
                swap(nums, i, i + k);
            } else if (i + k == n - 1) {
                k = n - 2 * k;
                swap(nums, i, i + k);
            } else {
                swap(nums, i, n - 1);
            }
        }
    }

    /**
     * 方案三: 逐元素后推, 循环k次:
     * 每次取最后一个元素给tmp, 然后从前往后逐元素推移
     * 速度肯定非常慢!!!
     * @param nums
     * @param k
     */
    public void rotateV3(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) return;
        int n = nums.length;
        k %= n;

    }

    /**
     * 方案四: 两次旋转
     * 先把整个数组翻转一次(逆序)
     * 然后把数组分为两半:
     *   前k个元素,
     *   后n-k个元素.
     * 最后, 分别内部翻转一次得到结果
     * 1ms. beat 12.56%
     * @param nums
     * @param k
     */
    public static void rotateV4(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) return;
        int n = nums.length;
        k %= n;
        int i = 0;
        int j = n - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
        i = 0;
        j = k - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
        i = k;
        j = n - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    public static void main(String[] args) {
        rotateV4(new int[]{1,2,3,4,5,6,7}, 6);
    }

}

