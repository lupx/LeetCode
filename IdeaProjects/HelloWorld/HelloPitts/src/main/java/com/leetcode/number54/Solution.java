package com.leetcode.number54;


import java.util.ArrayList;
import java.util.List;

/**
 * leetcode 54:
 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
 * For example,
 * Given the following matrix:
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * You should return [1,2,3,6,9,8,7,4,5].
 *
 * 旋转矩阵输出
 * 递归求解
 * Created by PeixinLu on 15/10/11.
 */
public class Solution {

    /**
     * 旋转矩阵输出
     * 卷心菜递归:
     * 每次求当前一层皮, 然后append(递归内层返回)
     *
     * @param matrix
     * @return
     */
    public static List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new ArrayList<Integer>();
        }
        return spiralHelper(matrix,0);
    }

    /**
     * 逐层剥开输出
     * 同时需要考虑矩阵可能不是正方形: m * n
     * 另外, 特殊情况必须考虑:
     * 单行单列情况下, 下横和左竖的条件必须考虑到!
     * @param matrix
     * @param layer 当前层
     * @return
     */
    public static List<Integer> spiralHelper(int[][] matrix, int layer) {
        int COL = matrix[0].length;//算出总列数
        int ROW = matrix.length;//算出总行数
        List<Integer> ints = new ArrayList<Integer>();
        //填本层内容, 然后递归
        //上横/右竖/下横/左竖
        for (int i = layer; i < COL - layer; i++) {
            ints.add(matrix[layer][i]);//上横
        }
        for (int i = layer + 1; i < ROW - layer; i++) {
            ints.add(matrix[i][COL - 1 - layer]);//右竖, 少最上面一个元素
        }
        if (ROW - 1 - layer > layer) {//需判断是否能走下横
            for (int i = COL - layer - 2; i >= layer; i--) {
                ints.add(matrix[ROW - 1 - layer][i]);//下横, 少右下元素
            }
        }
        if (COL - 1 - layer > layer) {//判断是否能走左竖
            for (int i = ROW - layer - 2; i > layer; i--) {
                ints.add(matrix[i][layer]);//左竖
            }
        }
        if (((ROW - layer - 1) - layer > 1)
                    && ((COL - layer - 1) - layer > 1)) {
                ints.addAll(spiralHelper(matrix, layer + 1));
        }
        return ints;
    }

    public static void main (String[] args) {
        int[][] a = new int[][]{
                {1, 4, 5, 6},
                {2, 3, 7, 3},
                {7, 4, 1, 0},
                {3, 2, 1, 5}
        };
//        int[][] a = new int[][]{
////                {1, 4},
////                {3, 2},
////                {5, 3}
//                {8},
//                {5},
//                {2},
//                {2}
//        };
        System.out.println(spiralOrder(a));
    }
}
