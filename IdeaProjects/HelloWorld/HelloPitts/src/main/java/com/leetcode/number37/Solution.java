package com.leetcode.number37;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 解数独, 假设给定的数独只有唯一解.
 *
 * Created by Peixin Lu on 15/10/5.
 */
public class Solution {

//    private static char[][] board = new char[][]{
//            new char[]{'5','3','.','.','7','.','.','.','.'},
//            new char[]{'6','.','.','1','9','5','.','.','.'},
//            new char[]{'.','9','8','.','.','.','.','6','.'},
//            new char[]{'8','.','.','.','6','.','.','.','3'},
//            new char[]{'4','.','.','8','.','1','.','.','1'},
//            new char[]{'7','.','.','.','2','.','.','.','6'},
//            new char[]{'.','6','.','.','.','.','2','8','.'},
//            new char[]{'.','.','.','4','1','9','.','.','5'},
//            new char[]{'.','.','.','.','8','.','.','7','9'},
//    };


    /**
     * 解数独
     * 递归求解,构造递归函数,其返回值是boolean.
     * 本方法是个driver, 先根据已知数独表,把3个maplist构造好;
     * 然后找出第一个需要填写的位置,然后让helper递归等结果即可;
     * 最终helper返回true的时候,流程结束(根据题目来看,肯定会有一个true的情况.)
//     * @param board
     */
    public static void solveSudoku(char[][] board) {
        List<HashMap<Character, Integer>> rowMaps = new LinkedList<HashMap<Character, Integer>>();
        List<HashMap<Character, Integer>> colMaps = new LinkedList<HashMap<Character, Integer>>();
        List<HashMap<Character, Integer>> squareMaps = new LinkedList<HashMap<Character, Integer>>();

        for (int i = 0; i < 9; i++) {
            HashMap<Character, Integer> fill = new HashMap<Character, Integer>();
            colMaps.add(i, fill); //直接加空的进去,后面的for循环就好做一点
        }
        for (int i = 0; i < 9; i++) {
            HashMap<Character, Integer> fill = new HashMap<Character, Integer>();
            squareMaps.add(i, fill); //直接加空的进去,后面的for循环就好做一点
        }


        int x = Integer.MAX_VALUE;
        int y = Integer.MAX_VALUE;
        for (int i = 0; i < 9; i++) {
            HashMap<Character, Integer> rowMap = new HashMap<Character, Integer>();
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    if(x == Integer.MAX_VALUE && y == Integer.MAX_VALUE) {
                        x = i;
                        y = j;
                    }
                    continue; //'.'情况不用考虑
                }
                rowMap.put(board[i][j], 1);//加行
                HashMap<Character, Integer> colMap = colMaps.get(j);
                colMap.put(board[i][j], 1);
                colMaps.remove(j);
                colMaps.add(j, colMap);//加列或更新列

                int squareNo = getSquareNo(i , j);
                HashMap<Character, Integer> thisSquare = squareMaps.get(squareNo);
                thisSquare.put(board[i][j], 1);
                squareMaps.remove(squareNo);
                squareMaps.add(squareNo, thisSquare);//添加或更新squareMaps
            }
            rowMaps.add(rowMap);
        }

        if(sudokuHelper(board, x, y, rowMaps, colMaps, squareMaps)) {
//        if(sudokuHelper(x, y, rowMaps, colMaps, squareMaps)) {
            System.out.println("done!");
            return;
        }
    }

    /**
     * i和j代表了当前需要填写的位置.
     * (1)本方法在当前位置填写一个值, 看看是否合法. 若不合法,选下一个值试试.
     *   (1.1)若合法,则算出下一个位置递归.
     * (2)若下一位置返回了false, 则重新填写一个合法值, 回到(1)继续. 若无法找到合法值,则返回false;
     * (3)若下一位置返回了true, 则直接返回true
     * 根据题目,势必会有一个情况会返回true!
//     * @param board
     * @param x 行下标
     * @param y 列下表
     * @param rowMaps 每一行对应的map的列表
     * @param colMaps 每一列对应的map的列表
     * @param squareMaps 每一个square对应的map的列表
     * @return
     */
    public static boolean sudokuHelper( char[][] board, int x, int y,
                                       List<HashMap<Character, Integer>> rowMaps,
                                       List<HashMap<Character, Integer>> colMaps,
                                       List<HashMap<Character, Integer>> squareMaps) {
        //当前x/y位置尝试填值
        for (int i = 0; i < 9; i++) {
            int squareNo = getSquareNo(x, y); //得到当前的squre
            HashMap<Character, Integer> rowMap = rowMaps.get(x);
            HashMap<Character, Integer> colMap = colMaps.get(y);
            HashMap<Character, Integer> squareMap = squareMaps.get(squareNo);

            Character candi = String.valueOf(i + 1).charAt(0);//候选项

            if (!rowMap.containsKey(candi)
                    && !colMap.containsKey(candi)
                    && !squareMap.containsKey(candi)) {
                board[x][y] = candi;
                //找下一个位置, 准备递归
                int nextX = Integer.MAX_VALUE;
                int nextY = Integer.MAX_VALUE;
                boolean flag = false;
                for (int ii = x; ii < 9 && !flag; ii++) {
                    for (int jj = 0; jj < 9; jj++) {
                        if (ii == x && jj  <= y) {
                            continue;//不用看,已经填了
                        } else {
                            if(board[ii][jj] == '.') {
                                nextX = ii;
                                nextY = jj;
                                flag = true;//找到第一个就退出
                                break;
                            }
                        }
                    }
                }
                if (nextX == 2 && nextY == 0) {
                    System.out.println("gotcha!");
                }
                if (nextX != Integer.MAX_VALUE && nextY != Integer.MAX_VALUE) {
                    //找到了
                    //更新3个map, 进入递归流程
                    rowMap.put(candi, 1);
                    rowMaps.remove(x);
                    rowMaps.add(x, rowMap);

                    colMap.put(candi, 1);
                    colMaps.remove(y);
                    colMaps.add(y, colMap);

                    squareMap.put(candi, 1);
                    squareMaps.remove(squareNo);
                    squareMaps.add(squareNo, squareMap);

                    //递归!
                    if (sudokuHelper(board, nextX, nextY, rowMaps, colMaps, squareMaps)) {
//                    if (sudokuHelper(nextX, nextY, rowMaps, colMaps, squareMaps)) {
                        return true;
                    } else {
                        //失败,那么需要抹去已经写入的值
                        board[x][y] = '.';
                        //并且3个maps要随之更新, 抹去所有值
                        rowMap = rowMaps.get(x);
                        rowMap.remove(candi);
                        rowMaps.remove(x);
                        rowMaps.add(x, rowMap);

                        colMap = colMaps.get(y);
                        colMap.remove(candi);
                        colMaps.remove(y);
                        colMaps.add(y, colMap);

                        squareMap = squareMaps.get(squareNo);
                        squareMap.remove(candi);
                        squareMaps.remove(squareNo);
                        squareMaps.add(squareNo, squareMap);
                        continue; //找下一个可行值
                    }
                } else {
                    //没找到, 说明成功了!
                    System.out.println("aa");
                    return true;
                }
            }//END of if, 找下一个候选项直到结束
        } //END of for

        //走到这里,说明所有值找遍了,但是都失败了, 说明之前步走的就有问题, 直接return false回溯!
        return false;
    }


    public static int getSquareNo (int x, int y) {
        int squareRow = Integer.MAX_VALUE;
        int squareCol = Integer.MAX_VALUE;
        if (x <= 2) {
            //squareRow = 0
            squareRow = 0;
        }
        if (x >= 3 && x <= 5) {
            squareRow = 3;
        }
        if (x > 5) {
            squareRow = 6;
        }
        if (y <= 2) {
            squareCol = 0;
        }
        if (y >= 3 && y <= 5) {
            squareCol = 1;
        }
        if (y > 5) {
            squareCol = 2;
        }
        return squareRow + squareCol;
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{
                new char[]{'5','3','.','.','7','.','.','.','.'},
                new char[]{'6','.','.','1','9','5','.','.','.'},
                new char[]{'.','9','8','.','.','.','.','6','.'},
                new char[]{'8','.','.','.','6','.','.','.','3'},
                new char[]{'4','.','.','8','.','3','.','.','1'},
                new char[]{'7','.','.','.','2','.','.','.','6'},
                new char[]{'.','6','.','.','.','.','2','8','.'},
                new char[]{'.','.','.','4','1','9','.','.','5'},
                new char[]{'.','.','.','.','8','.','.','7','9'},
        };
        solveSudoku(board);
    }
}
