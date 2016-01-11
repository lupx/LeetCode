package com.leetcode.number36;

import java.util.*;

/**
 * Determine if a Sudoku is valid
 * The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
 *
 *
 * Created by Peixin Lu on 15/10/5.
 */
public class Solution {

    /**
     * 数独,肯定有9行9列. 所以不会有特殊情况考虑
     * board[0][0]开始, 沿对角线往board[8][8]找. 维护8个hashMap即可.
     *
     * @param board
     * @return
     */
    public static boolean isValidSudoku(char[][] board) {
        List<HashMap<Character, Integer>> maparray = new LinkedList<HashMap<Character, Integer>>();
        List<HashMap<Character, Integer>> square = new LinkedList<HashMap<Character, Integer>>();

        for (int i = 0; i < 9; i++) {
            HashMap<Character, Integer> fill = new HashMap<Character, Integer>();
            maparray.add(i, fill); //直接加空的进去,后面的for循环就好做一点
        }
        for (int i = 0; i < 9; i++) {
            HashMap<Character, Integer> fill = new HashMap<Character, Integer>();
            square.add(i, fill); //直接加空的进去,后面的for循环就好做一点
        }

        for (int i = 0; i < 9; i++) {
            HashMap<Character, Integer> map = new HashMap<Character, Integer>();//当前行map
            for (int j = 0; j < 9; j++) {
                HashMap<Character, Integer> m = maparray.get(j);//尝试取当前列对应map
                if (board[i][j] == '.') {
                    //此种情况下,无需判断,直接continue
                    continue;
                }
                int squareRow = Integer.MAX_VALUE;
                int squareCol = Integer.MAX_VALUE;
                if (i <= 2) {
                    //squareRow = 0
                    squareRow = 0;
                }
                if (i >= 3 && i <= 5) {
                    squareRow = 3;
                }
                if (i > 5) {
                    squareRow = 6;
                }
                if (j <= 2) {
                    squareCol = 0;
                }
                if (j >= 3 && j <= 5) {
                    squareCol = 1;
                }
                if (j > 5) {
                    squareCol = 2;
                }
                int squareNo = squareRow + squareCol;
                HashMap<Character, Integer> thisSquare = square.get(squareNo);
                if (thisSquare.containsKey(board[i][j])) {
                    return false;
                } else {
                    thisSquare.put(board[i][j], 1);
                    square.remove(squareNo);
                    square.add(squareNo, thisSquare);
                }
                if ((!map.containsKey(board[i][j]) && m.isEmpty())) {
                    map.put(board[i][j], 1);
                    m.put(board[i][j], 1);
                    maparray.remove(j);//删除旧的map
                    maparray.add(j, m);//更新j列map
                    continue;
                } else if (!map.containsKey(board[i][j]) && !m.containsKey(board[i][j])) {
                    map.put(board[i][j], 1);
                    m.put(board[i][j], 1);
                    maparray.remove(j);
                    maparray.add(j, m);
                    continue;
                } else {
                    return false;
                }
            }
        }// End of for, means all are valid.
        return true;
    }


    public static void main(String[] args) {
        char[][] board = new char[][]{
                new char[]{'.','.','4','.','.','.','6','3','.'},
                new char[]{'.','.','.','.','.','.','.','.','.'},
                new char[]{'5','.','.','.','.','.','.','9','.'},
                new char[]{'.','.','.','5','6','.','.','.','.'},
                new char[]{'4','.','3','.','.','.','.','.','1'},
                new char[]{'.','.','.','7','.','.','.','.','.'},
                new char[]{'.','.','.','5','.','.','.','.','.'},
                new char[]{'.','.','.','.','.','.','.','.','.'},
                new char[]{'.','.','.','.','.','.','.','.','.'},
                };
        System.out.println(isValidSudoku(board));
    }
}
