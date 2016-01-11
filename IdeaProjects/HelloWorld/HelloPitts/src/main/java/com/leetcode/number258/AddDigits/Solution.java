package com.leetcode.number258.AddDigits;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.
 * For example:
 * Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.
 *
 * Follow up:
 * Could you do it without any loop/recursion in O(1) runtime?
 * Follow Up 要求不用循环或者递归在常数时间做出来!
 *
 * Created by Peixin Lu on 16/1/3.
 */
public class Solution {

    /**
     * 根据维基百科的词条, 这种问题叫做求"数位根"(Digital root), 最后的结论是,一个数n的数位根 = n mod 9.
     * @param num
     * @return
     */
    public int addDigits(int num) {
        if (num == 0) return 0;
        return num % 9 == 0 ? 9 : num % 9;
    }

    public static void main(String[] args) {
    }
}