package com.leetcode.number275.Hindex2;

/**
 * Hindex的follow up: 如果已经由小到大排序了, 能不能优化算法?
 *
 * Created by Peixin Lu on 16/1/7.
 */
public class Solution {

    /**
     * 其实反而更简单了!
     * 一趟遍历解决
     * beat 20.38%
     * @param citations
     * @return
     */
    public static int hIndex(int[] citations) {
        int count = 0;
        for (int i = citations.length - 1; i >=0; i--) {
            count++;
            if (count == citations[i]) return citations[i];
            if (count > citations[i]) return count - 1;

        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(hIndex(new int[]{2,3,10,10}));
    }
}