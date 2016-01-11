package com.leetcode.number289.GameOfLife;


import java.util.Arrays;

/**
 * According to the Wikipedia's article: "The Game of Life, also known simply as Life,
 *   is a cellular automaton devised by the British mathematician John Horton Conway in 1970."
 *
 * Given a board with m by n cells, each cell has an initial state live (1) or dead (0).
 *   Each cell interacts with its eight neighbors (horizontal, vertical, diagonal)
 *        using the following four rules (taken from the above Wikipedia article):
 *
 * Any live cell with fewer than two live neighbors dies, as if caused by under-population.
 * Any live cell with two or three live neighbors lives on to the next generation.
 * Any live cell with more than three live neighbors dies, as if by over-population..
 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 *
 * Write a function to compute the next state (after one update) of the board given its current state.
 *
 * Follow up:
 * Could you solve it in-place? Remember that the board needs to be updated at the same time:
 *      You cannot update some cells first and then use their updated values to update other cells.
 *
 * In this question, we represent the board using a 2D array.
 * In principle, the board is infinite[无限的], which would cause problems when the active area encroaches[侵犯,到达] the border of the array.
 *   How would you address these problems?
 *
 * Created by Peixin Lu on 16/1/9.
 */
public class Solution {

    /**
     * 使用2D空间的算法,相对简单, 遍历board,然后把每个格子的下个状态写入就行了
     * follow up要求用原地做,也就是说常数空间来做.
     * 那么, 就得利用每个格子自己保存自己下个状态, 同时还要让其他格子知道目前状态好利于其他格子进行计算
     * 其实,只需要约定下来, 1如果要变, 变成了谁? 0如果要变, 变成了谁?
     * 比如, 我们约定1变成-1, 0变成-2.
     * 这样,其他格子看见-1, 就当其为1来计算. 看见-2, 就当其为0来计算.
     * 最后, 遍历board, 把-1变为0, -2变为1, 即可!
     * beat 15.16%
     * @param board
     */
    public static void gameOfLife(int[][] board) {
        if (board == null||board.length == 0) return;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int lives = computeLives(board, i, j);
                if (board[i][j] == 1) {
                    if (lives < 2) board[i][j] = -1;
                    if (lives > 3) board[i][j] = -1;
                } else {
                    if (lives == 3) board[i][j] = -2;
                }
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == -1) board[i][j] = 0;
                if (board[i][j] == -2) board[i][j] = 1;
            }
        }
    }

    private static int computeLives(int[][] board, int i, int j) {
        int lives = 0;
        if (i > 0
                && (board[i - 1][j] == -1||board[i - 1][j] == 1)) {
            lives++;
        }
        if (i < board.length - 1
                && (board[i + 1][j] == -1||board[i + 1][j] == 1)) {
            lives++;
        }
        if (j > 0
                && (board[i][j - 1] == -1||board[i][j - 1] == 1)) {
            lives++;
        }
        if (j < board[0].length - 1
                && (board[i][j + 1] == -1||board[i][j + 1] == 1)) {
            lives++;
        }
        if ((i > 0 && j > 0)
                && (board[i - 1][j - 1] == -1||board[i - 1][j - 1] == 1)) {
            lives++;
        }
        if ((i < board.length - 1 && j < board[0].length - 1)
                && (board[i + 1][j + 1] == -1||board[i + 1][j + 1] == 1)) {
            lives++;
        }
        if ((i > 0 && j < board[0].length - 1)
                && (board[i - 1][j + 1] == -1||board[i - 1][j + 1] == 1)) {
            lives++;
        }
        if ((i < board.length - 1 && j > 0)
                && (board[i + 1][j - 1] == -1||board[i + 1][j - 1] == 1)) {
            lives++;
        }
        return lives;
    }

    private static int computeNeighbors(int[][] board, int i, int j) {
        int neighbors = 0;
        if (i == 0 && (j == 0 || j == board[0].length - 1)) neighbors = 3;
        if (i == board.length - 1 && (j == 0 || j == board[0].length - 1)) neighbors = 3;
        if (i == 0 || i == board.length - 1 || j == 0 || j == board[0].length - 1) neighbors = 5;
        else neighbors = 8;
        return neighbors;
    }

    public static void main(String[] args) {
        int[][] board = new int[][]{
                {1,1},
                {0,1},
                {1,0}
        };
        gameOfLife(board);
        System.out.println("aa");
    }
}
