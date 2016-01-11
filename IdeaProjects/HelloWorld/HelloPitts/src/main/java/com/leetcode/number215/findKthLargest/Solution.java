package com.leetcode.number215.findKthLargest;

import java.util.PriorityQueue;

/**
 * 一个数组, 要求找出其中第k大的数.
 * 注意, 数组是未排序的.
 * Created by Peixin Lu on 15/12/26.
 */
public class Solution {

    /**
     * 先排序, 再输出.
     * beat 11.66%
     *
     * 这个题其实最适合用堆来解决.
     * @param nums
     * @param k
     * @return
     */
    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> q = new PriorityQueue<>(); // java自己的优先队列(堆), 是个小顶堆
        for (int i = 0; i < nums.length; i++) {
            q.offer(nums[i]);
        }
        //因为是小顶堆, 所以需要自己做处理
        //要求第k大, 其实就是求第n + 1小
        int n = nums.length - k;
        while (n-- > 0) {
            q.poll();
        }
        return q.poll();
    }

    public static void main(String[] args) {
        System.out.println(findKthLargest(new int[]{2,3,6,1,4,7,9}, 4));
    }
}
