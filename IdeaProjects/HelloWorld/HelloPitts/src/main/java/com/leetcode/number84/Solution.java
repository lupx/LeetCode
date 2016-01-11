package com.leetcode.number84;

import java.util.Stack;

/**
 * LeetCode84: 找出最大长方形
 *
 * Created by PeixinLu on 15/10/16.
 */
public class Solution {

    /**
     * 2个解法:
     * (1)首先立刻想到O(n^2)解法, 先i遍历,对每个i给一个j往后遍历, 算出从每个位置往后各种区间的大小, 记录下中途最大的那个即可
     *    肯定TLE!
     * (2)分治法, 不断分为一半, 算出各自半的最大面积, 然后两半合在一起算从mid往两边延展最大的面积, 三个面积取最大值.
     *
     * @param height
     * @return
     */
    public static int largestRectangleAreaTLE(int[] height) {
        if (height == null || height.length == 0) return 0;
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            if (height[i] == 0) continue;
            int thisSum = height[i];
            int minHeight = height[i];
            for (int j = i; j < height.length; j++) {
                if (height[j] < minHeight) {
                    minHeight = height[j];
                }
                thisSum = minHeight * (j - i + 1);
                if (thisSum > max) max = thisSum;
            }
        }
        return max;
    }

    /**
     * 第二个解法: 分治, 时间复杂度: O(nlogn)
     * 对全一样数字的条件,就会TLE!!!
     * @param height
     * @return
     */
    public static int largestRectangleAreaDC(int[] height) {
        if (height == null || height.length == 0) return 0;
        return divdeConquer(height, 0, height.length - 1);
    }

    /**
     * 分一半, 递归求两边最大, 然后求中间延展到两边的最大, 求三者之间最大返回
     * 注意基准条件
     * @param height
     * @param start
     * @param end
     * @return
     */
    public static int divdeConquer(int[] height, int start, int end) {
        if (start == end) {
            return height[start];
        }
        int mid = 0;
        mid = start + (end - start) / 2;

        int leftMax = divdeConquer(height, start, mid);
        int rightMax = divdeConquer(height, mid + 1, end);
        int max = Math.max(leftMax, rightMax);
        /**
         * 求从mid延展到两边的最大值
         * mid--, mid+1 ++
         */
        int i = mid, j = mid + 1;
        int thisSum = 0, thisMax = 0;
        int minHeight = Integer.MAX_VALUE;
        while (i >= start && j <= end) {
            int iArea = height[i];
            int jArea = height[j];
            minHeight = Math.min(minHeight, height[i]);
            minHeight = Math.min(minHeight, height[j]);
            thisSum = minHeight * (j - i + 1);
            thisSum = Math.max(iArea, thisSum);
            thisSum = Math.max(jArea, thisSum);
            if (thisSum > thisMax) {
                thisMax = thisSum;
            }
            //一左一右
            if ((mid - i) <= j - (mid + 1) && i >= start) {
                //说明此步需要走i
                i--;
            }
            if ((mid - i) > j - (mid + 1) && j <= end) {
                //说明此步需要走j
                j++;
            }
        }
        return Math.max(thisMax, max);//中间延展最大,和两边的大者, 比较得到start到end之间的最大值
    }

    /**
     * 解法三,把人类智慧提升到极限的方法: stack!
     * 方法:
     * 遍历一遍数组
     *
     *
     * @param height
     * @return
     */
    public static int largestRectangleArea(int[] height) {
        if (height == null || height.length == 0 )
            return 0;
        Stack<Integer> stack = new Stack<Integer>();
        int res = 0;
        int i = 0;
        while(i < height.length) {
            while(!stack.isEmpty() && height[stack.peek()] >= height[i]) {
                int index = stack.pop();
                int temp = stack.isEmpty() ? i * height[index]
                        : ( (i - 1) - (stack.peek() + 1) + 1 ) * height[index];
                res = Math.max(res,temp);
            }
            stack.push(i);
            i++;
        }
        int count = 1;
        while (!stack.isEmpty()) {
            int index = stack.pop();
            if (!stack.isEmpty() && height[index] == height[stack.peek()]) {
                count++;
                continue;
            }
            int temp = count * height[index];
            res = Math.max(res,temp);
            temp = stack.isEmpty() ? i * height[index]
                    : ( i - stack.peek() - 1 ) * height[index];
            res = Math.max(res,temp);
        }
        return res;
    }


    public static void main(String[] args) {
        int[] a = new int[]{1,1,2,1,1,1};
        System.out.println(largestRectangleArea(a));
    }
}

