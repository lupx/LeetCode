package com.leetcode.number51;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 * Each solution contains a distinct board configuration of the n-queens' placement,
 * where 'Q' and '.' both indicate a queen and an empty space respectively.
 * Created by PeixinLu on 15/10/11.
 */
public class Solution {

    /**
     * 返回所有的摆放可能.
     * 回溯递归, 不用多说了.
     * @param n
     * @return
     */
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<List<String>>();
        List<List<Integer>> rs = new ArrayList<List<Integer>>();
        List<Integer> tmp = new ArrayList<Integer>();
        nQueensHelper(n, rs, tmp, 0);

        for (List<Integer> l : rs) {
            List<String> strs = new ArrayList<String>();
            for (Integer col : l) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < n; i++) {
                    if (i == col) {
                        sb.append('Q');
                    } else {
                        sb.append('.');
                    }
                }
                strs.add(sb.toString());
            }
            result.add(strs);
        }
        return result;
    }

    /**
     *
     * 该helper负责在nextRow上从0到n-1位置分别放queen然后递归的处理
     * 在下一行结果递归返回后, tmp中要删除当前位填写的queen,然后进入下一列
     * @param n
     * @param result 保存最终结果对应的各个解的各个列号, 驱动程序还需要根据这个解拼成最终的结果
     * @param tmp 保存已经截止目前用掉的各个列号
     * @param nextRow 本次需要填入行的行号
     */
    public static void nQueensHelper(int n, List<List<Integer>> result, List<Integer> tmp, int nextRow) {
        if (nextRow == n) {
            //base condition
            result.add(new ArrayList<Integer>(tmp));//将一个解放入result中
        }
        for (int j = 0;j < n; j++) {
            //nextRow为当前行号, j为当前列号
             if (tmp.contains(j)) {
                 //该列已被选过
                 continue;
             } else {
                 //该列没被选过, 看对角线
                 boolean conflict = false;
                 for (int i = 0; i < tmp.size(); i++) {
                     //对tmp中每个列一一检查,只要有冲突就不能用
                     int usedRow = i;
                     int usedCol = tmp.get(i);
                     if ((j - usedCol) == (nextRow - usedRow)) {
                         conflict = true;
                         break;
                     }
                     if ((usedCol - j) == (nextRow - usedRow)) {
                         conflict = true;
                         break;
                     }
                 }
                 if (!conflict) {
                     //无冲突, 可以递归迭代
                     tmp.add(j);
                     nQueensHelper(n, result, tmp, nextRow + 1);
                     //迭代完,tmp清除本步所走
                     tmp.remove(tmp.size() - 1);
                 }
             }
        }
    }

    public static void main (String[] args) {

        List<List<String>> result = solveNQueens(8);
        System.out.println(result.size());
        for (List<String> l : result) {
            System.out.println(l.size());
            for (String s : l) {
                System.out.println(s + "\n");
            }
        }
    }
}
