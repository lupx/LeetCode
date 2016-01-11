package com.leetcode.number48;

/**
 * You are given an n x n 2D matrix representing an image.
 * Rotate the image by 90 degrees (clockwise).
 * Follow up:
 * Could you do this in-place?
 * in-place意味着不能申请其他空间做转存, 引用是可以用的.
 * Created by Peixin Lu on 15/10/11.
 */
public class Solution {

    /**
     * 顺时针旋转矩阵
     * 找到规律就很简单
     * 首先,左右翻转
     * 规律是:
     * matrix[i][j]的对称点是: matrix[i][COL-1-j]
     * 然后,沿左下到右上的对角线对翻,即为所求
     * 沿此对角线对折的规律是:
     * matrix[i][j]的对角点为: matrix[ROW-1-j][COL-1-i]
     *
     * @param matrix
     */
    public static void rotate(int[][] matrix) {
        //题目说了n*n, 所以肯定不用怕越界
        int N = matrix[0].length;

        //左右翻转
        int mid = N / 2;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < mid; j++) {
                int newj = N - 1 - j;
                swap(matrix, i, j, i, newj);
            }
        }

        //沿对角线对翻
        //matrix[i][j] 与 matrix[N-1-j][N-1-i] swap
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < N - 1 - i; j++) {
                int newi = N - 1 - j;
                int newj = N - 1 - i;
                swap(matrix, i, j, newi, newj);
            }
        }
    }

    public static void swap(int[][] matrix, int i, int j, int newi, int newj) {
        int tmp = matrix[newi][newj];
        matrix[newi][newj] = matrix[i][j];
        matrix[i][j] = tmp;
    }


    public static void main(String[] args) {
//        int[][] a = new int[][]{
//                {1,2,3,4},
//                {3,4,7,2},
//                {0,4,5,9},
//                {6,8,3,2}
//        };
        int[][] a = new int[][] {
                {1}
        };
        rotate(a);
        System.out.println("done!");
    }
}
