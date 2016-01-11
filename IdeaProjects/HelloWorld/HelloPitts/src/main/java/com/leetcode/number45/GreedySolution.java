package com.leetcode.number45;

/**
 * 贪心算法求leetCode45题:
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Your goal is to reach the last index in the minimum number of jumps.
 * For example:
 * Given array A = [2,3,1,1,4]
 * The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
 * Created by PeixinLu on 15/10/10.
 */
public class GreedySolution {

    /**
     * 贪心算法
     * start指针从0往后遍历, 然后再从start位置到能跳到位置范围内遍历,找到能跳到最远的数,然后步数+1
     * 遍历完后, 把start更新到最远那个数后面的位置(相当于跳了一步),再重复上面过程
     *
     * 最后返回步数即可
     *
     * @param nums
     * @return
     */
    public static int jump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int start = 0, end = 0, jumps = 0;
        while (end < nums.length - 1) {
            int farthest = end;
            for (int i = start; i <= end; i++) {
                if (nums[i] + i > farthest) {
                    farthest = nums[i] + i;
                }
            }
            start = end + 1;
            end = farthest;
            jumps++;
        }
        return jumps;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,3,1,4,5,3,1,7};
        System.out.println(jump(nums));

    }
}
