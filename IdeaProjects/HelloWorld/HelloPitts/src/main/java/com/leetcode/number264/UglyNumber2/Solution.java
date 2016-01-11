package com.leetcode.number264.UglyNumber2;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 寻找丑数2:
 * Write a program to find the n-th ugly number.
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
 *    For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
 * Note that 1 is typically treated as an ugly number.
 *
 * Created by Peixin Lu on 16/1/3.
 */
public class Solution {

    /**
     * 由2/3/5分别生成后续的ugly数, 变为3个有序list,然后合并这三个list, 再得到第n个数即为结果.
     * 或者不合并的话, 就用heap, 维持小顶堆, 然后pop()N次即为结果.
     * 维护3个list
     * @param n
     * @return
     */
    public int nthUglyNumber(int n) {
        Queue<Long> q = new PriorityQueue<Long>();
        int[] nums = {2,3,5};
        Long result = Long.valueOf(1);
        q.offer(result);
        for(int i=0;i<n;i++){
            // Each time we poll the peak value of q, is the ith number
            result = q.poll();
            for(int num : nums){
                Long uglyNum = result * num;
                if(!q.contains(uglyNum)){
                    q.offer(uglyNum);
                }
            }
        }
        return result.intValue();
    }


    int getMin(int a,int b,int c){
        int min=Integer.min(a,b);
        min=Integer.min(min,c);
        return min;
    }


    /**
     * 这个版本也很巧妙!
     * p2, p3, p5三个指针分别指向了最小的2/3/5的因子
     * @param n
     * @return
     */
    public int nthUglyNumberV2(int n) {
        if(n == 1) return 1;
        int[] a = new int[n];
        int p2 = 0, p3 = 0, p5 = 0, p = 1, count = 1;
        a[0] = 1;
        while (count < n){
            a[p] = getMin(a[p2] * 2,a[p3] * 3,a[p5] * 5);
            while(a[p2] * 2 <= a[p])
                p2++;
            while(a[p3] * 3 <= a[p])
                p3++;
            while(a[p5] * 5 <= a[p])
                p5++;
            p++;
            count++;
        }
        return a[--p];
    }

    public static void main(String[] args) {
    }
}