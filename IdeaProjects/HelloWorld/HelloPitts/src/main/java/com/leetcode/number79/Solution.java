package com.leetcode.number79;
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
public class Solution {

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

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (existHelper(board, word, i, j)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 首先检查i,j是否合法
     * 其次基准条件:
     * @param board
     * @param word
     * @param i
     * @param j
     * @return
     */
    public static boolean existHelper(char[][] board, String word, int i, int j) {
        if (word.length() == 0) return true;//base condition
        if (i < 0 || i >= board.length
                || j < 0 || j >= board[0].length) {
            return false;
        }

        if (board[i][j] != word.charAt(0)) return false;

        return existHelper(board, word.substring(1), i + 1, j)
                || existHelper(board, word.substring(1), i, j + 1)
                || existHelper(board, word.substring(1), i - 1, j)
                || existHelper(board, word.substring(1), i, j - 1);
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'a','b','c'},
                {'d','e','d'},
                {'f','s','p'}
        };
        System.out.println(exist(board, "abfd"));
    }
}

