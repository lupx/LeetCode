package com.leetcode.number274.Hindex;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of citations (each citation is a non-negative integer) of a researcher,
 *     write a function to compute the researcher's h-index.
 *
 * According to the definition of h-index on Wikipedia:
 *   "A scientist has index h if h of his/her N papers have at least h citations each,
 *     and the other N − h papers have no more than h citations each."
 * For example,
 *
 *  given citations = [3, 0, 6, 1, 5],
 *  which means the researcher has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations respectively.
 *  Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each,
 *    his h-index is 3.
 * Note: If there are several possible values for h, the maximum one is taken as the h-index.
 * Created by Peixin Lu on 16/1/7.
 */
public class Solution {

    /**
     * 最简单的办法是给数组排序, 但是hint里说不够快. 更快的办法是用空间换时间!
     * 最小可能就是0, 最大可能h-index就是数组长度(每个的值都大于n).
     * beat 63.03%
     * @param c
     * @return
     */
    public int hIndex(int[] c) {
        int[] sum = new int[c.length + 2];
        for(int i : c){
            int index = i >= c.length ? c.length : i;
            sum[index]++;
        }
        for(int i = sum.length - 2; i >= 0; i--){
            sum[i] += sum[i + 1];
            if(sum[i] >= i)
                return i;
        }
        return 0;
    }

    public static void main(String[] args) {
    }
}