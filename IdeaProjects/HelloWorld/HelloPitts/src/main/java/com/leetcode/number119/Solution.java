package com.leetcode.number119;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an index k, return the kth row of the Pascal's triangle.
 * For example, given k = 3,
 * Return [1,3,3,1].
 *
 * Challenge: Could you optimize your algorithm to use only O(k) extra space???
 * Created by Peixin Lu on 15/10/29.
 */
public class Solution {

    /**
     * 要考虑用O(k)空间来实现
     * 总结规律发现, 其实就是求k个数的全排列, C(k~0), C(k~1), C(k~2),...C(k~k)
     * 1ms, beat 90.49%
     * @param rowIndex
     * @return
     */
    public static List<Integer> getRow(int rowIndex) {
        List<Integer> rst = new ArrayList<>();
        if (rowIndex < 0) return rst;
        if (rowIndex == 0) {
            rst.add(1);
            return rst;
        }
        int mid = rowIndex / 2;
        long divided = 1L;
        for (int i = 0; i <= mid; i++) {
            if (i == 0) {
                divided = 1L;
            } else {
                divided = divided * (rowIndex - i + 1) / i;
            }
            rst.add((int)divided);
        }
        //往回把相等值再加进来
        for (int i = rowIndex % 2 == 0 ? rst.size() - 2 : rst.size() - 1;
                i >= 0; i--) {
            rst.add(rst.get(i));
        }
        return rst;
    }

    public static void main(String[] args) {
        List<Integer> rst = getRow(30);
    }
}

