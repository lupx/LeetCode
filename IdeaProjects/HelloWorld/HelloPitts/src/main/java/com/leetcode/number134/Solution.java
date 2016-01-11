package com.leetcode.number134;

/**
 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel
 *     from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.
 * Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
 *
 * Created by Peixin Lu on 15/11/6.
 */
public class Solution {

    /**
     * gas[i]: amount of gas in station i
     * cost[i]:  gas cost from i to i+1,
     * 贪心算法, 从i=0开始作为第一个起点加油站, 到每个加油站做如下计算:
     *     剩余油量 + 当前加油站总油量gas[i] 是否 大于 cost[i]
     *       若大于, 则i++
     *       若小于, 则认为当前方案不可行, 起点递推
     * 只要能跑下来,就返回其起点. 若起点遍历了一遍都跑不下来, 返回-1
     *
     * 这个O(n)算法需要数学证明, 能理解证明, 就能想明白这个问题.
     *
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas == null || cost == null) {
            return -1;
        }
        int start = 0;
        int totalRemin = 0;
        int tank = 0;
        for (int i = 0; i < gas.length; i++) {
            int cur = gas[i] - cost[i];
            tank += cur;
            if (tank < 0) {//说明当前这个走法不对, 那么, 可以肯定的是, start必然从i + 1开始
                start = i + 1;
                tank = 0;
            }
            totalRemin += cur;
        }
        //totalRemin只要 >= 0, 说明从头到尾,总油量肯定是够的.
        return totalRemin < 0 ? -1 : start;
    }


    public static void main(String[] args) {

    }
}

