package com.leetcode.number295.FindMedianfromDataStream;

import java.util.PriorityQueue;

/**
 * 用堆实现
 * 结合小顶堆+大顶堆实现
 * Created by Peixin Lu on 16/1/9.
 */
public class MedianFinder {
    /**
     * 大顶堆保存从0到median的数
     * 小顶堆保存从median到末尾的数
     */
    private PriorityQueue<Integer> minheap;//小顶堆
    private PriorityQueue<Integer> maxheap;//大顶堆

    private double median;

    public MedianFinder() {
        //initialize ...
        minheap = new PriorityQueue<>();
        maxheap = new PriorityQueue<>((a, b) -> b - a);
        median = Integer.MIN_VALUE;
    }

    // Adds a number into the data structure.
    public void addNum(int num) {
        if (num >= median) {
            //放入小顶堆
            minheap.offer(num);
            if (minheap.size() > maxheap.size() + 1) {
                //调整大小
                int tmp = minheap.poll();
                maxheap.offer(tmp);
            }
            if (minheap.size() > maxheap.size()) {
                median = minheap.peek();
            } else {
                median = ((double)minheap.peek() + (double)maxheap.peek())/ 2;
            }
        } else {
            maxheap.offer(num);
            if (maxheap.size() > minheap.size() + 1) {
                //调整大小
                int tmp = maxheap.poll();
                minheap.offer(tmp);
            }
            if (maxheap.size() > minheap.size()) {
                median = maxheap.peek();
            } else {
                median = ((double)minheap.peek() + (double)maxheap.peek())/ 2;
            }
        }
    }

    // Returns the median of current data stream
    public double findMedian() {
        return median;
    }

    public static void main(String[] args) {
        MedianFinder mf = new MedianFinder();
        mf.addNum(-1);
        System.out.println(mf.findMedian());
        mf.addNum(-2);
        System.out.println(mf.findMedian());
        mf.addNum(-3);
        System.out.println(mf.findMedian());
        mf.addNum(-4);
        System.out.println(mf.findMedian());
        mf.addNum(-5);
        System.out.println(mf.findMedian());
//        mf.addNum(7);
//        System.out.println(mf.findMedian());
//        mf.addNum(3);
//        System.out.println(mf.findMedian());
//        mf.addNum(4);
//        System.out.println(mf.findMedian());
    }
}
