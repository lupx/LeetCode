package com.leetcode.number74;


/**
 * LeetCode 74:搜索矩阵:
 * 在一个矩阵中搜索某数,要求算法高效
 * 矩阵有特点:
 * 每一行从小到大排序;
 * 每一行第一个元素一定大于上一行最后一个元素.
 *
 * 明显用二分法来做!
 *
 * Created by PeixinLu on 15/10/16.
 */
public class Solution {

    /**
     * 搜索矩阵
     * 明显的binary search
     * 先按每行第一个元素来search,确定行
     * 在行内在用二分找元素
     *
     * @param matrix
     * @param target
     * @return
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        //第一次二分,在第一列中找合适的行
        int start = 0;
        int end = matrix.length - 1;
        int row = Integer.MIN_VALUE;
        if (matrix.length == 1) row = 0;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (matrix[mid][0] == target) return true;
            if (matrix[mid][0] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (matrix[start][0] == target) {
            return true;
        }
        if (matrix[end][0] == target) {
            return true;
        }
        if (matrix[end][0] > target) {
            row = start;
        }
        if (matrix[end][0] < target) {
            row = end;
        }

        //在行内二分搜索找target
        start = 0;
        end = matrix[0].length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (matrix[row][mid] == target) return true;
            if (matrix[row][mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (matrix[row][start] == target) {
            return true;
        }
        if (matrix[row][end] == target) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
//                {1,3,6,7,9,12,16,20},
//                {21,23,27,30,31,34,38,40},
//                {41,43,46,47,51,56,62,66},
//                {68,69,72,75,80,86,94,100},
//                {101,102,109,119,123,129,137,143}
//                {1},
                {2}
        };
        System.out.println(searchMatrix(matrix, 2));
    }
}

