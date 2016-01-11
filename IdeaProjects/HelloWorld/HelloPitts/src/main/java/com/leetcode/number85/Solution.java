package com.leetcode.number85;

import java.util.Arrays;
import java.util.Stack;

/**
 *
 * Created by PeixinLu on 15/10/16.
 */
public class Solution {

    /**
     * 这其实也有动规的思想在里面
     * 用一个arr积累每一行的1, 然后对每一行计算当前最大bar
     * @param matrix
     * @return
     */
    public static int maximalRectangle(char[][] matrix) {
        if(matrix == null || matrix.length == 0)
            return 0;

        int len = matrix[0].length;
        // This array represent the histogram after going through each row.
        int[] arr = new int[len];
        Arrays.fill(arr, 0);
        int max = 0;

        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < len; j++) {
                if(matrix[i][j] == '1') {
                    arr[j]++;
                } else {
                    arr[j] = 0;
                }
            }
            max = Math.max(max, calculateArea(arr));//Calculate largest bar for each row
        }
        return max;
    }

    private static int calculateArea(int[] arr) {
        int top = 0;
        int area = 0;
        int maxarea = 0;
        Stack<Integer> stack = new Stack<Integer>();
        int i = 0;
        stack.push(i);
        for(i =1; i < arr.length; i++) {
            if(arr[i] < arr[stack.peek()]) {
                while(!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                    top = stack.pop();
                    if(stack.isEmpty()) {
                        area = arr[top] * i;
                    } else {
                        area = arr[top] * (i - stack.peek() - 1);
                    }
                    if(area > maxarea)
                        maxarea = area;
                }
            }
            stack.push(i);
        }
        while(!stack.isEmpty()) {
            top = stack.pop();
            if(stack.isEmpty()) {
                area = arr[top] * i;
            } else {
                area = arr[top] * (i - stack.peek() - 1);
            }
            if(area > maxarea)
                maxarea = area;
        }
        return maxarea;
    }

    /**
     * 纯DP解法!太屌了!
     * 需要三个一维数组:
     * left[j]: 过往所有行中, 0到j列最晚遇到1的地方, 就是该行0到j列的实际左边界
     * right[j]: 过往所有行中, COL-1到j列最早遇到0的地方, 就是该行COL-1到j列的实际右边界
     * height[j]: 每一行累积下来的高度. 纯++即可
     * 三个数组从第一行到最后一行累积计算, 每一行需要考虑当前情况和前一行情况, 所以是DP解法!
     * @param matrix
     * @return
     */
    public static int maximalRectangleDP(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int ROW = matrix.length;
        int COL = matrix[0].length;
        int[] left = new int[COL];
        int[] right = new int[COL];
        int[] height = new int[COL];
        /**
         * 初始化
         */
        Arrays.fill(left, 0);
        Arrays.fill(right, COL);
        Arrays.fill(height, 0);

        int max = 0;//最大面积

        /**
         * 对每一行进行计算, 递推公式如下:
         * 每一行开始时,左边界定为0, 右边界定为COL
         * height[j]好算:
         *    如果matrix[i][j] = 0, height[j]不变
         *    如果matrix[i][j] = 1, height[j]++;
         * left[j]从左往右算:
         *    如果matrix[i][j] = 0, left[j]=0, 同时左边界变为当前j+1(因为潜在的左边界可能就在j+1)
         *    如果matrix[i][j] = 1, left[j]= max(left[j], 左边界), 哪个大取哪个.
         *    (解释: 因为我们要的是过往所有行中0到该列位置最晚遇到1的位置)
         * right[j]从右往左算:
         *    如果matrix[i][j] = 0, right[j]=0, 同时右边界变为当前j(因为潜在的右边界就在当前j位置)
         *    如果matrix[i][j] = 1, right[j]= min(right[j], 右边界), 哪个小取哪个.
         *    (解释: 因为我们要的是过往所有行中COL-1到该列位置最早遇到0的位置)
         */
        for (int i = 0; i < ROW; i++) {
            int leftBound = 0;
            int rightBound = COL;//如果本行全为1, 那么从右往左第一个0应该在COL处, 这是个想象的位置, 只是为方便计算.
            /**
             * 算高度
             */
            for (int j = 0; j < COL; j++) {
                if (matrix[i][j] == '1') {
                    height[j]++;
                } else {
                    height[j] = 0;
                }
            }

            /**
             * 算左边界
             */
            for (int j = 0; j < COL; j++) {
                if (matrix[i][j] == '1') {
                    left[j] = Math.max(left[j], leftBound);
                } else {
                    left[j] = 0;
                    leftBound = j + 1;
                }
            }

            /**
             * 算右边界
             */
            for (int j = COL - 1; j >=0; j--) {
                if (matrix[i][j] == '1') {
                    right[j] = Math.min(right[j], rightBound);
                } else {
                    rightBound = j;//当前行j到COL-1位置, 最早遇到0的位置可能就是当前
                }
            }

            /**
             * 实时计算走到当前行的最大矩形面积
             *
             */
            for (int j = 0; j < COL; j++) {
                max = Math.max((right[j] - left[j]) * height[j], max);
            }
        }
        return max;
    }


    public static void main(String[] args) {
        char[][] a = new char[][] {
//                {'0','1','1','0','0','0','1','1','1','1'},
                {'0','0','0','1','1','0','1','1','1','1'}
        };

        System.out.println(maximalRectangleDP(a));
    }
}

