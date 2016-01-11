package com.leetcode.number218.Skyline;

import java.util.*;

/**
 * 题目太长, 就不粘贴了, 这里用中文描述一下
 * 给一个二维数组, 代表了不同高度大小的建筑物, 每个数组元素是个三元素的数组: Li, Ri, Hi. Li, Ri分别代表这个建筑左边右边的x轴坐标, Hi代表其高度
 * 所以, 比如, [3,7,5] 表示这个建筑左边位于3, 右边位于7, 高度是5.
 *
 * 本题要求给出一个二维数组,  要求返回这些建筑组成侧面整体轮廓的所有高度组合
 *
 *
 * Created by Peixin Lu on 15/12/28.
 */
public class Solution {


    /**
     * 大神们告诉你, 本题用大顶堆解决
     * 下面解法简单直观, 速度不是很快.
     * @param buildings
     * @return
     */
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> result = new ArrayList<>();
        List<int[]> height = new ArrayList<>();
        for(int[] b:buildings) {
            height.add(new int[]{b[0], -b[2]}); // start point has negative height value
            height.add(new int[]{b[1], b[2]}); // end point has normal height value
        }

        // sort $height, based on the first value, if necessary, use the second to break ties
        Collections.sort(height, (a, b) -> {
            if(a[0] != b[0])
                return a[0] - b[0];
            return a[1] - b[1];
        });

        // Use a maxHeap to store possible heights
        Queue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));

        // Provide a initial value to make it more consistent
        pq.offer(0);

        // Before starting, the previous max height is 0;
        int prev = 0;

        // visit all points in order
        for(int[] h:height) {
            if(h[1] < 0) { // a start point, add height
                pq.offer(-h[1]);
            } else {  // a end point, remove height
                pq.remove(h[1]);
            }
            int cur = pq.peek(); // current max height;

            // compare current max height with previous max height, update result and previous max height if necessary
            if(prev != cur) {
                result.add(new int[]{h[0], cur});
                prev = cur;
            }
        }
        return result;
    }

    public static void main(String[] args) {
    }
}
