package com.leetcode.number52;

import java.util.ArrayList;
import java.util.List;

/**
 * leetCode 52: 八皇后II, 返回所有解的个数
 * Created by PeixinLu on 15/10/11.
 */
public class Solution {

    /**
     * 返回所有解的个数
     * 回溯递归, 不用多说了.
     * @param n
     * @return
     */
    public static int totalNQueens(int n) {
        List<Integer> rs = new ArrayList<Integer>();
        List<Integer> tmp = new ArrayList<Integer>();
        nQueensHelper(n, rs, tmp, 0);
        return rs.size();
    }

    /**
     *
     * 该helper负责在nextRow上从0到n-1位置分别放queen然后递归的处理
     * 在下一行结果递归返回后, tmp中要删除当前位填写的queen,然后进入下一列
     * @param n
     * @param result 每个解会存入一个1, 最后数1的个数即可
     * @param tmp 保存已经截止目前用掉的各个列号
     * @param nextRow 本次需要填入行的行号
     */
    public static void nQueensHelper(int n, List<Integer> result, List<Integer> tmp, int nextRow) {
        if (nextRow == n) {
            //base condition
            result.add(1);//放入一个解
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
        System.out.println(totalNQueens(8));
    }
}
