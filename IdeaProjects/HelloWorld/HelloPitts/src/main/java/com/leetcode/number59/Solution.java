package com.leetcode.number59;


/**
 * Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
 * For example,
 * Given n = 3,
 * You should return the following matrix:
 * [
 * [ 1, 2, 3 ],
 * [ 8, 9, 4 ],
 * [ 7, 6, 5 ]
 * ]
 *
 * Created by Peixin Lu on 15/10/12.
 */
public class Solution {

    /**
     * 同spiral matrix I
     * 一圈一圈填, 填完一圈, 递归进入内圈
     *
     * @param n
     * @return
     */
    public static int[][] generateMatrix(int n) {
        int[][] a = new int[n][n];
        spiralHelper(a, 0, 1);//从第一圈开始,从1开始填
        return a;
    }

    /**
     *
     * @param nums 结果集
     * @param layer 层数
     * @param start 当前圈第一个位置填写数字
     */
    public static void spiralHelper(int[][] nums, int layer, int start) {
        int N = nums.length;
        //能走进来,说明横是肯定要画的, 先画横
        for (int i = layer; i < N - layer; i++) {//i代表列
            nums[layer][i] = start;
            start++;
        }
        //画右竖
        for (int i = layer + 1; i < N - layer; i++) {
            nums[i][N - layer - 1] = start;
            start++;
        }
        //下横,从列减一开始, 到layer列截止
        for (int i = N - layer- 2; i >= layer; i--) {
            nums[N - layer - 1][i] = start;
            start++;
        }
        //画左竖, 从行减一开始
        for (int i = N - layer - 2; i > layer; i-- ) {
            nums[i][layer] = start;
            start++;
        }
        if (N - layer * 2 >= 1) {
            //还有的画,继续深入递归
            spiralHelper(nums, layer + 1, start);
        }
    }

    public static void main (String[] args) {
        int[][] a = generateMatrix(4);
//        int[][] b = new int[3][3];
        for (int[] b : a) {
            StringBuilder sb = new StringBuilder();
            for (int c : b) {
                sb.append(c);
            }
            System.out.println(sb.toString() + "\n");
        }
    }

}
