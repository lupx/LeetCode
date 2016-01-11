package com.leetcode.number73;

import java.util.*;

/**
 * Created by PeixinLu on 15/10/16.
 */
public class Solution {

    /**
     * 遍历数组, 遇到0, 就把行列记录入map, 跳入下一行
     * 最终遍历map, 把matrix置位
     * @param matrix
     */
    public static void setZeroes(int[][] matrix) {
        if (matrix == null) return;
        Map<Integer, Integer> rowmap = new HashMap<Integer, Integer>();
        Map<Integer, Integer> colmap = new HashMap<Integer, Integer>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    rowmap.put(i, 1);
                    colmap.put(j, 1);
                }
            }
        }
        Iterator itrrow = rowmap.keySet().iterator();
        Iterator itrcol = colmap.keySet().iterator();
        while (itrrow.hasNext()) {
            //对每一个对应行,全部清零
            int row = (Integer) itrrow.next();
            for (int k = 0; k < matrix[0].length; k++) {
                matrix[row][k] = 0;
            }
        }
        while (itrcol.hasNext()) {
            //对每一个对应行,全部清零
            int col = (Integer) itrcol.next();
            for (int k = 0; k < matrix.length; k++) {
                matrix[k][col] = 0;
            }
        }
    }

    /**
     * 在遍历的时候直接实时置0,
     * O(1)空间, O(n^2)时间
     * 把第一行和第一列用来做记录
     * 首先, 两层遍历数组, 哪行遇到0, 就把哪行第一列为0, 哪列遇到0, 就把第一行当前列置为0
     *      但是, 这个时候, 第一行第一列就身兼两个职责了, 这显然不对.
     *      所以, 我们附加一个extra位,用来专门记录第一行置0的情况, 而matrix[0][0]用来记录第一列置0的情况, 这样先区分开职责.
     *
     * 然后, 遍历第一行从第2列到最后一列, 确定哪些列需要置0. 跳过第一列的原因是, 每一行第一列用来保存每一行置位信息了,
     *      所以这一列现在先不能动.
     * 接着, 遍历第一列从第1行到最后一行, 确定哪些行需要置0. 现在,虽然第一行各个列都已经完成了置位,但是第一列还是不能动, 因为第一列仍然
     *      决定了后续行第一列的情况, 所以, matrix[0][0]位置需要跳过.
     * 最后, 根据matrix[0][0]的情况, 对第一列做最终的调整.
     * 最最后, 一定切记, matrix[0][0]要根据extra做调整, 如果extra为0, matrix[0][0] = 0
     *
     * @param matrix
     */
    public static void setZeroesConstanceSpace(int[][] matrix) {
        if (matrix == null) return;
        int extra = Integer.MAX_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    //借助matrix自身记录i和j
                    if (i == 0) {
                        //第一行记录到一个附加位上
                        extra = 0;
                    } else {
                        matrix[i][0] = 0;
                    }
                    matrix[0][j] = 0;
                }
            }
        }
        //遍历第一行,从第2列到最后一列, 确定哪些列需要置0
        for (int i = 1; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) {
                for (int j = 0; j < matrix.length; j++) {
                    matrix[j][i] = 0;
                }
            }
        }

        //遍历第一列第1行到最后一行, 确定都有哪些行需要置0
        //第一行第一列先不做变动
        for (int i = 0; i < matrix.length; i++) {
            if (i == 0) {
                if (extra == 0) {
                    for (int j = 1; j < matrix[0].length; j++) {
                        matrix[0][j] = 0;
                    }
                }
            } else {
                if (matrix[i][0] == 0) {
                    for (int j = 0; j < matrix[0].length; j++) {
                        matrix[i][j] = 0;
                    }
                }
            }
        }

        //看看matrix[0][0]是否需要把第一列置0
        if (matrix[0][0] == 0) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
        if (extra == 0) {
            matrix[0][0] = 0;
        }
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
//                {8,3,6,9,7,8,0,6},
//                {0,3,7,0,0,4,3,8},
//                {5,3,6,7,1,6,2,6},
//                {8,7,2,5,0,6,4,0},
//                {0,2,9,9,3,9,7,3}
                {1,1,1},
                {0,1,2}
        };
        setZeroesConstanceSpace(matrix);
    }
}

