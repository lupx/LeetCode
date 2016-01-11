package com.leetcode.number128;

import java.util.*;

/**
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 * For example,
 * Given [100, 4, 200, 1, 3, 2],
 * The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
 * Your algorithm should run in O(n) complexity.
 *
 * Created by Peixin Lu on 15/10/24.
 */
public class Solution {

    /**
     * 要求O(n)时间, 那么显然无法预排序解决.
     * 所有数字入map, 再声明两个变量, left和right, 用来指代当前区间的左右边界, 然后遍历此map, 每遇到一个数字,
     *   left=当前数字-1, right=当前数字+1, 然后在map中找left和right, 如果找到, 更新这两个数,同时map.remove这个数
     *   同时, 把长度更新到max里
     * 遍历完map后, return max即可
     *
     * Don't know whether this is really O(n) solution.
     *
     * @param nums
     * @return
     */
    public static int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int max = 0;
        int left = 0, right = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        Iterator itr = set.iterator();
        while(itr.hasNext()) {
            int thisNum = (Integer)itr.next();
            left = thisNum - 1;
            right = thisNum + 1;
            while (set.contains(left)) {
                set.remove(left);
                left--;
            }
            while (set.contains(right)) {
                set.remove(right);
                right++;
            }
            set.remove(thisNum);
            itr = set.iterator();
            int length = right - left - 1;
            max = max < length ? length : max;
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(longestConsecutive(new int[]{4,9,15,2,7,5}));
    }
}

