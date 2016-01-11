package com.leetcode.number118;

import com.leetcode.number117.TreeLinkNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given numRows, generate the first numRows of Pascal's triangle.
 * For example, given numRows = 5,
 * Return
 * [
 * [1],
 * [1,1],
 * [1,2,1],
 * [1,3,3,1],
 * [1,4,6,4,1]
 * ]
 * Created by Peixin Lu on 15/10/29.
 */
public class Solution {

    /**
     * 循环迭代做.
     * 每次读取tmplist中数字,前后相加,得到当前数字. 首尾填1.
     * 1ms, beat 60%
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> rst = new ArrayList<>();
        if (numRows == 0) return rst;
        List<Integer> tmp = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        tmp.add(1);
        rst.add(new ArrayList<>(tmp));
        numRows--;
        while(numRows > 0) {
            list.add(1);
            for (int i = 0; i < tmp.size() - 1; i++) {
                list.add(tmp.get(i) + tmp.get(i + 1));
            }
            list.add(1);
            rst.add(new ArrayList<>(list));
            tmp = new ArrayList<>(list);
            list.clear();
            numRows--;
        }
        return rst;
    }
}

