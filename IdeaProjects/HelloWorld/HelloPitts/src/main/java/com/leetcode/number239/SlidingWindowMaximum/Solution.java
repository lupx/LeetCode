package com.leetcode.number239.SlidingWindowMaximum;

import java.util.ArrayDeque;
import java.util.PriorityQueue;

/**
 * 最大滑动窗口:
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right.
 *
 * You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 *  For example,
 *  Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
 *
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1  [3  -1  -3] 5  3  6  7       3
 * 1   3 [-1  -3  5] 3  6  7       5
 * 1   3  -1 [-3  5  3] 6  7       5
 * 1   3  -1  -3 [5  3  6] 7       6
 * 1   3  -1  -3  5 [3  6  7]      7
 * Therefore, return the max sliding window as [3,3,5,5,6,7].
 *
 * FollowUp问: 能不能在线性时间内做出来?
 * Created by Peixin Lu on 16/1/1.
 */
public class Solution {

    /**
     * 常数时间的话, 用堆来做
     * 每次入堆一个数字, 出堆一个数字, 然后取最大元素出来存入rst[i].
     * 如此往复, 直到窗口走到头.
     * beat 14.18%
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSlidingWindowUsingHeap(int[] nums, int k) {
        if (nums == null||nums.length == 0) return new int[0];
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {return b - a;});//大顶堆
        int[] rst = new int[nums.length - k + 1];
        int i = 0;
        for (; i < k; i++) {
            pq.offer(nums[i]);
        }
        rst[i - k] = pq.peek();
        for (; i < nums.length; i++) {
            pq.offer(nums[i]);
            pq.remove(nums[i - k]);
            rst[i - k + 1] = pq.peek();
        }
        return rst;
    }

    /**
     * 还可以用双端队列来做
     * 核心思想是: 用双端队列一头保存当前队列中最大的数的下标!
     *
     * 双端队列比堆要快, 因为堆每次入元素后要做内部的整理!
     * beat 62.55%
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSlidingWindowUsingDeque(int[] nums, int k) {
        if (nums == null || k <= 0) return new int[0];
        int[] res = new int[nums.length - k + 1];
        ArrayDeque<Integer> deque = new ArrayDeque<Integer>();

        int index  = 0;
        for (int i = 0; i < nums.length; i++) {
            // Ensure deque's size doesn't exceed k
            while (!deque.isEmpty() && deque.peek() < i - k + 1)
                deque.poll();

            // Remove numbers smaller than a[i] from right(a[i-1]) to left,
            // to make the first number in the deque the largest one in the window
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i])
                deque.pollLast();

            // Offer the current index to the deque's tail
            deque.offer(i);
            if (i >= k - 1)// Starts recording when i is big enough to make the window has k elements
                res[index++] = nums[deque.peek()];
        }
        return res;
    }


    public static void main(String[] args) {
        int [] rst = maxSlidingWindowUsingHeap(new int[]{1,3}, 2);
        for (Integer i : rst) {
            System.out.println(i);
        }
    }
}
