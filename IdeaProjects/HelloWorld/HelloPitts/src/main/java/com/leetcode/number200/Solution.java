package com.leetcode.number200;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 *
 * Example 1:
 * 11110
 * 11010
 * 11000
 * 00000
 * Answer: 1
 *
 * Example 2:
 * 11000
 * 11000
 * 00100
 * 00011
 * Answer: 3
 * Created by Peixin Lu on 15/12/2.
 */
public class Solution {

    /**
     * ufs, 并查集
     * 两边循环, 从左上角开始, 只要遇到1, 就开始递归广搜找集合(所有1标为'A'), 找到集合后,  rst++;
     * 继续上述步骤, 直到走到右下角格
     * beat 66.87%, 速度还行!
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int n = grid.length;//rows
        int m = grid[0].length;//cols
        int rst = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    unionAll(grid, i, j);//from grid[i][j] find this island, using bst!
                    rst++;
                }
            }
        }
        return rst;
    }

    /**
     * BST to union this island, set all grid to 'A'
     * @param grid
     * @param i
     * @param j
     */
    private void unionAll(char[][] grid, int i, int j) {
        grid[i][j] = 'A';
        //look around 4 ways, beware of the borders.
        if (i > 0 && grid[i - 1][j] == '1') unionAll(grid, i - 1, j);
        if (i < grid.length - 1 && grid[i + 1][j] == '1') unionAll(grid, i + 1, j);
        if (j > 0 && grid[i][j - 1] == '1') unionAll(grid, i, j - 1);
        if (j < grid[0].length - 1 && grid[i][j + 1] == '1') unionAll(grid, i, j + 1);
    }

    public static void main(String[] args) {

    }
}

