package com.leetcode.number130;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collector;

/**
 * Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 * For example,
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 *
 * After running your function, the board should be:
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 *
 * Created by Peixin Lu on 15/11/6.
 */
public class Solution {

    /**
     * 二层遍历, 遇到X就continue
     * 遇到O的时候, 则BFS, 看看是否最终能走通:
     *       若能则全部保留在集合中, continue
     *       若不能, 则全部从集合中移除, 并且全部置为X;
     *
     * BFS算法:
     *  (1)上下左右看有没有O, 有O且其位置不在集合中, 则把当前位置加入集合, 然后往O走一步, 递归.
     *  (2)若最终返回true,则认为能走通.若最终返回false, 认为走不通.
     *
     * 借助board自身保存集合信息:
     *   如果bfs走完返回true, 那么所有走过的节点全部更新为'o', 集合清空
     *   如果bfs走完返回false, 那么所有走过的节点更新为'X', 集合清空
     * 所以,在一个bfs的过程中, 只有一个集合存在.
     *
     * 全部遍历完后, 再遍历一遍, 把o变为O即可.
     *
     * @param board
     */
    public static void solve(char[][] board) {
        if (board == null || board.length == 0) return;
        int n = board.length, m = board[0].length;

        /**
         * 查左右两个边界
         */
        for (int i = 0; i < n; i++) {
            bfs(board, i, 0);
            bfs(board, i, m - 1);
        }

        /**
         * 上下两个边界
         */
        for (int j = 0; j < m; j++) {
            bfs(board, 0, j);
            bfs(board, n - 1, j);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'V' || board[i][j] == 'O') // V means visited
                    board[i][j] = 'X';
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == '1') // 1 means O at the border
                    board[i][j] = 'O';
            }
        }
    }

    /**
     * bfs, 用队列来实现, 可以避免递归, 也就不会耗费太多memory
     * 此外, 用board自己来做并查集, 同一个集合置为一个字符即可.
     *
     * @param board
     * @param i
     * @param j
     * @return
     */
    private static void bfs(char[][] board, int i, int j) {
        Queue<Point> q = new LinkedList<>();
        Point point = new Point(i, j);
        q.offer(point);
        while (!q.isEmpty()) {
            Point p = q.poll();
            int x = p.x;
            int y = p.y;
            if (board[x][y] == '1'
                    || board[x][y] == 'V') {
                continue; //visited or belongs to border family
            }
            if (board[x][y] == 'O') {
                board[x][y] = '1';//border family
                if (x - 1 >= 0) {
                    q.offer(new Point(x - 1, y));
                }
                if (y - 1 >= 0) {
                    q.offer(new Point(x, y - 1));
                }
                if (x + 1 < board.length) {
                    q.offer(new Point(x + 1, y));
                }
                if (y + 1 < board[0].length) {
                    q.offer(new Point(x, y + 1));
                }
            } else {
                board[x][y] = 'V';//visited
            }
        }
    }

    public static void main(String[] args) {
        char[][] b = new char[][]{
                {'X','X','X','X'},
                {'X','O','O','X'},
                {'X','O','X','X'},
                {'X','O','X','X'}
        };
        solve(b);
    }
}

