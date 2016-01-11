package com.leetcode.number240.Search2DMatrix2;

import java.util.ArrayDeque;
import java.util.PriorityQueue;

/**
 * Write an efficient algorithm that searches for a value in an m x n matrix.
 * This matrix has the following properties:
 * Integers in each row are sorted in ascending from left to right. //每一行, 从左到右升序排列
 * Integers in each column are sorted in ascending from top to bottom. //每一列, 从上到下升序排列
 * For example,
 * Consider the following matrix:
 *
 * [
 * [1,   4,  7, 11, 15],
 * [2,   5,  8, 12, 19],
 * [3,   6,  9, 16, 22],
 * [10, 13, 14, 17, 24],
 * [18, 21, 23, 26, 30]
 * ]
 * Given target = 5, return true.
 * Given target = 20, return false.
 *
 * Created by Peixin Lu on 16/1/1.
 */
public class Solution {

    /**
     * 行列一起二分确定行列范围,然后遍历行列
     * 用每行行首确定行范围最大值, 用每行行末确定行范围最小值
     * 用每列列首确定列范围最大值, 用每列列末确定列范围最小值
     * 写的长了点..
     * beat 8.62%...不明白为什么速度慢...
     * @param matrix
     * @param target
     * @return
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;
        int N = matrix.length, M = matrix[0].length;
        if (target < matrix[0][0] || target > matrix[N - 1][M - 1]) return false;
        //二分法确定行列范围
        int minRow = 0, maxRow = 0, minCol = 0, maxCol = 0;

        //先确定最大行, 和每行行首比
        int start = 0, end = N - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (matrix[mid][0] == target) return true;
            if (matrix[mid][0] < target) {
                //mid < target, 说明mid是可能的最大行
                start = mid;
            } else {
                end = mid;
            }
        }
        if (matrix[end][0] > target) maxRow = start;
        if (matrix[end][0] <= target) maxRow = end;

        //确定最小行, 和每行行尾比
        start = 0;
        end = N - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (matrix[mid][M - 1] == target) return true;
            if (matrix[mid][M - 1] < target) {
                //mid < target, 说明mid是可能的最小行
                start = mid;
            } else {
                end = mid;
            }
        }
        if (matrix[start][M - 1] >= target) minRow = start;
        if (matrix[start][M - 1] < target) minRow = end;

        //确定最大列, 和每列列首比
        start = 0;
        end = M - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (matrix[0][mid] == target) return true;
            if (matrix[0][mid] < target) {
                //mid < target, 说明mid是可能的最大行
                start = mid;
            } else {
                end = mid;
            }
        }
        if (matrix[0][end] > target) maxCol = start;
        if (matrix[0][end] <= target) maxCol = end;

        //确定最小列, 和每列列尾比
        start = 0;
        end = M - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (matrix[N - 1][mid] == target) return true;
            if (matrix[N - 1][mid] < target) {
                //mid < target, 说明mid是可能的最大行
                start = mid;
            } else {
                end = mid;
            }
        }
        if (matrix[N - 1][start] >= target) minCol = start;
        if (matrix[N - 1][start] < target) minCol = end;

        //缩小了搜索的范围
        for (int i = minRow; i <= maxRow; i++) {
            for (int j = minCol; j <= maxCol; j++) {
                if (matrix[i][j] == target) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] m = new int[][] {
                {1},
                {3}
//                {1,   4,  7, 11, 15},
//                {2,   5,  8, 12, 19},
//                {3,   6,  9, 16, 22},
//                {10, 13, 14, 17, 24},
//                {18, 21, 23, 26, 30}
        };
        System.out.println(searchMatrix(m, 3));
    }
}
