package com.leetcode.number79;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a 2D board and a word, find if the word exists in the grid.
 * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring.
 * The same letter cell may not be used more than once.
 * For example,
 * Given board =
 * [
 * ["ABCE"],
 * ["SFCS"],
 * ["ADEE"]
 * ]
 * word = "ABCCED", -> returns true,
 * word = "SEE", -> returns true,
 * word = "ABCB", -> returns false.
 *
 * Created by PeixinLu on 15/10/16.
 */
public class BetterSolution {

    /**
     * 清晰明白的回溯递归, 但是该解法TLE!!!
     * 每个方法处理当前格: i, j
     * 如果和当前word第一个字符相同, 则分别往四个方向求word.substring(1)的匹配情况
     * 四个方向不管哪个返回true,则直接返回true
     * 否则, 循环继续, 直到完全结束
     * @param board
     * @param word
     * @return
     */
    public static boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0) {
            return false;
        }
        if (word.length() > board.length * board[0].length) return false;
        List<Integer> starts = findAllStarts(board, word.charAt(0));
        boolean[][] visited;

        int i = 0;
        while (i < starts.size()) {
            visited = new boolean[board.length][board[0].length];
            if (existHelper(board, word, starts.get(i),starts.get(i + 1), 0, 0, visited)) {
                return true;
            }
            i += 2;
        }
        return false;
    }

    public static List<Integer> findAllStarts(char[][] board, char start) {
        List<Integer> starts = new ArrayList<Integer>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == start) {
                    starts.add(i);
                    starts.add(j);
                }
            }
        }
        return starts;
    }

    /**
     * 首先检查i,j是否合法;
     * 其次考虑基准条件;
     * 最后还要看从哪个方向来的,就不能再问那个方向情况
     * @param board
     * @param word
     * @param i
     * @param j
     * @param entry 代表从哪个方向进入的这个点.
     *              0,表示一开始第一点
     *              1,上
     *              2,下
     *              3,左
     *              4,右
     * @param count word已匹配的字符个数
     * @param visited 记录各个点是否被访问过
     * @return
     */
    public static boolean existHelper(char[][] board, String word, int i, int j,
                                      int entry, int count, boolean[][] visited) {
        if (i < 0 || i >= board.length
                || j < 0 || j >= board[0].length) {
            return false;
        }
        if (visited[i][j]) {
            return false; //cannot visit twice!
        }
        if (board[i][j] != word.charAt(count)) return false;
        count += 1;
        if (count == word.length()) {
            return true;
        }
        visited[i][j] = true;//尝试走当前步
        boolean up = entry != 1 && existHelper(board, word, i - 1, j, 2, count, visited);
        boolean down = entry != 2 && existHelper(board, word, i + 1, j, 1, count, visited);
        boolean left = entry != 3 && existHelper(board, word, i, j - 1, 4, count, visited);
        boolean right = entry != 4 && existHelper(board, word, i, j + 1, 3, count, visited);
        boolean result = up || down || left || right;
        if (!result) visited[i][j] = false;//一旦当前步没法走, 当前步一定要注意撤销!
        return result;
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'A','B','C','E'},
                {'S','F','E','S'},
//                {'a','b','b'},
//                {'b','b','b'},
//                {'b','b','b'},
//                {'a','a','a'},
//                {'b','b','b'},
//                {'a','b','b'},
//                {'a','a','b'},
                {'A','D','E','E'}
        };
        System.out.println(exist(board, "ABCESEEEFS"));
    }
}

