package com.leetcode.number174;

/**
 * The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon.
 * The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially positioned
 *    in the top-left room and must fight his way through the dungeon to rescue the princess.
 * The knight has an initial health point represented by a positive integer.
 * If at any point his health point drops to 0 or below, he dies immediately.
 * Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms;
 * other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).
 * In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.

 * Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.
 * For example, given the dungeon below, the initial health of the knight must be at least 7
    if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.
  -2 (K)	-3	   3
  -5	   -10	   1
  10	    30	  -5 (P)

 * 描述挺复杂, 其实就是个动态规划, 条件更复杂而已.
 * 转换题意, 其实就是找到:  从左上到右下权重最小且为正的一条路径即可
 *
 * Created by Peixin Lu on 15/11/27.
 */
public class Solution {

    /**
     * 注意, 此解法虽然intuitive, 但是是错误的!
     * 维护一个accuDmg, 作为累积伤害, 每走到一个点, 分别判断从上过来和从左过来的累计伤害
     * 取其中小值, 并且换算成走到该点所需的最小血量. 和全局最小血量做比较, 必要时更新全局
     *
     * @param dungeon
     * @return
     */
    public static int calculateMinimumHP_forward(int[][] dungeon) {
        if (dungeon == null || dungeon.length == 0) return 0;

        int n = dungeon.length;//rows
        int m = dungeon[0].length;//cols

        /**
         * dp[i][j] 表示走到dungeon[i][j]位置, 骑士必须有的最小初始血量
         */
        int[][] dp = new int[n][m];
        /**
         * dp[i][j] 表示骑士从左上角按最优路径走到dungeon[i][j]位置, 累积受到的伤害
         * 加血视为正伤害
         */
        int[][] accuDmg = new int[n][m];

        /**
         * 初始化
         * 如果左上角格是正数或0, 那么骑士只要有一点血就够了
         * 如果左上角是demon,  那么骑士的血量必须大于耗血量
         */
        dp[0][0] = dungeon[0][0] < 0 ? Math.abs(dungeon[0][0]) + 1 : 1;
        accuDmg[0][0] = dungeon[0][0]; //加血视为正伤害

        int min = dp[0][0];
        int sum = dungeon[0][0];
        /**
         * 初始化第一行, 相当于骑士先一直往右走
         */
        for (int j = 1; j < dungeon[0].length; j++) {
            sum += dungeon[0][j];
            int tmp = 0;
            if (sum <= 0) {
                tmp = sum * -1 + 1;
                if (tmp > min) min = tmp;
            }
            dp[0][j] = min;
            accuDmg[0][j] = sum;
        }
        /**
         * 初始化第一列, 相当于骑士先一直往下走
         */
        sum = dungeon[0][0];
        min = dp[0][0];
        for (int i = 1; i < dungeon.length; i++) {
            sum += dungeon[i][0];
            int tmp = 0;
            if (sum <= 0) {
                tmp = sum * -1 + 1;
                if (tmp > min) min = tmp;
            }
            dp[i][0] = min;
            accuDmg[i][0] = sum;
        }

        /**
         * 递推:
         * 先分别计算从上和从左走过来的accuDMG是多少,
         * 然后, 分别用这两个dmg, 计算出如果从上走下来和从左走过来分别的最小血量是多少.
         * 然后, 哪个小, 就用哪个!
         * 注意: 更新dp[i][j]和accuDmg[i][j]!
         */
        int accUp = 0;
        int accLeft = 0;
        int minUp = 0;
        int minLeft = 0;
        for (int i = 1; i < dungeon.length; i++) {
            for (int j = 1; j < dungeon[0].length; j++) {
                accUp = accuDmg[i - 1][j] + dungeon[i][j];
                accLeft = accuDmg[i][j - 1] + dungeon[i][j];
                if (accUp >= 0) {
                    minUp = dp[i - 1][j];
                } else {
                    minUp = accUp * -1 + 1 > dp[i - 1][j] ? accUp * -1 + 1 : dp[i - 1][j];
                }
                if (accLeft >= 0) {
                    minLeft = dp[i][j - 1];
                } else {
                    minLeft = accLeft * -1 + 1 > dp[i][j - 1] ? accLeft * -1 + 1 : dp[i][j - 1];
                }
                if (accUp == accLeft) {
                    //选初始血量小的
                    dp[i][j] = minUp <= minLeft ? minUp : minLeft;
                    accuDmg[i][j] = accUp;//随便选一个即可
                } else {
                    //选累积正伤害小的(accuDmg大的)
                    dp[i][j] = accLeft >= accUp ? minLeft : minUp;
                    accuDmg[i][j] = accLeft >= accUp ? accLeft : accUp;
                }
            }
        }
        return dp[n - 1][m - 1];
    }


    /**
     * 上面的正向解法实际是有问题的. 原因在于上面的dp[][]是有后效性的.
     * 每走一步的dp[i][j]对后面的结论是会产生影响的, 例子:
     *     1  -3  3
     *     0  -2  0
     *     -3 -3 -3
     * 算出来结果就是错的.
     *
     * 改成反向做:
     * 还是定义dp[i][j], 它表示骑士为了达到终点, 走入dungeon[i][j]的时候必须有多少血量
     * 显然, dp[0][0]即为所求.
     *
     * 解法正确!
     * 速度很快, 66.98% beat
     * @param dungeon
     * @return
     */
    public static int calculateMinimumHP_backward(int[][] dungeon) {
        if (dungeon == null || dungeon.length == 0) return 0;

        int n = dungeon.length;//rows
        int m = dungeon[0].length;//cols

        /**
         * dp[i][j]表示走到dungeon[i][j]格, 骑士必须有的最少血量
         * 显然, dp[0][0]即为我们所求结果
         * 所以是个逆向过程
         */
        int[][] dp = new int[n][m];

        /**
         * 初始化, 当然是从右下角开始初始化
         */
        dp[n - 1][m - 1] = dungeon[n - 1][m - 1] < 0 ?
                   dungeon[n - 1][m - 1] * -1 + 1 : 1;

        /**
         * 边界处理, 初始化底边
         * 底边所有点只能往右走
         * 简单叠加即可
         */
        for (int j = m - 2; j >= 0; j--) {
            if (dungeon[n - 1][j] >= dp[n - 1][j + 1]) {
                dp[n - 1][j] = 1;
            } else {
                dp[n - 1][j] = dp[n - 1][j + 1] - dungeon[n - 1][j];
            }
        }

        /**
         * 边界处理, 初始化右边
         * 右边所有点只能往下走
         * 简单叠加即可
         */
        for (int i = n - 2; i >= 0; i--) {
            if (dungeon[i][m - 1] >= dp[i + 1][m - 1]) {
                dp[i][m - 1] = 1;
            } else {
                dp[i][m - 1] = dp[i + 1][m - 1] - dungeon[i][m - 1];
            }
        }

        /**
         * 逆向求解
         * 对每个格, 看下格和右格, 哪个小, 我们就认为骑士走到当前位置的下一个决策应该是往哪里走
         *
         */
        for (int i = n - 2; i >= 0; i--) {
            for (int j = m - 2; j >= 0; j--) {
                int right = dp[i][j + 1];
                int down = dp[i + 1][j];
                int minHP = 0;
                if (right >= down) {
                    minHP = down - dungeon[i][j];
                } else {
                    minHP = right - dungeon[i][j];
                }
                dp[i][j] = minHP <= 0 ? 1 : minHP;
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        int[][] dungeon = new int[][]{
//                {1, -3,  3},
//                {0, -2,  0},
//                {-3, -3, -3}
//                {3, -20, 30},
//                {-3, 4, 0}
                {0,  5},
                {-2, -3}
//                {}
        };
        System.out.println(calculateMinimumHP_backward(dungeon));
    }

}

