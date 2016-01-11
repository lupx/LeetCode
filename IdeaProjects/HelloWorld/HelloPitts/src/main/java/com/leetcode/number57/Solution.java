package com.leetcode.number57;

import java.util.*;

/**
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 * You may assume that the intervals were initially sorted according to their start times.
 *
 * Example 1:
 * Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
 * Example 2:
 * Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
 * This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
 *
 * Created by Peixin Lu on 15/10/12.
 */
public class Solution {

    /**
     * 同样的思路.
     * 既然给定的intervals里不重叠, 那么表示肯定没有起点相同的区间
     * 放入数组中, 起点终点放入map中
     * 然后遍历数组, 如果插入区间的start不位于当前区间内部, 当前区间放入结果list中, continue;
     * 如果位于当前区间内部, 考虑是否合并:
     * 如果需要合并, 在合并完后, 还要考虑合并完的end点是否有可能连接下一个区间的起点, 如果可能继续合并 (此处是个loop)
     *     在做完所有可能的合并后,  把当前新的区间放入list中
     * 如果不需要合并, 把当前新区间放入list中.
     * 最后把剩余区间放入list中.
     * 返回结果list
     *
     * @param intervals
     * @param newInterval
     * @return
     */
    public static List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new ArrayList<Interval>();
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        if (intervals == null || intervals.size() == 0) {
            result.add(newInterval);
            return result;
        }
        int[] starts = new int[intervals.size()];
        for (int i = 0; i < intervals.size(); i++) {
            starts[i] = intervals.get(i).start;
            map.put(intervals.get(i).start, intervals.get(i).end);
        }

        Arrays.sort(starts);//起点排序

        int inStart = newInterval.start;
        int inEnd = newInterval.end;
        boolean inserted = false;
        /**
         * 对starts中每个起点, 取其区间, 和newInterval起点终点做综合判断
         * 如果不需要合并, 直接把当前区间入list,continue
         * 如果能合并, 合并后更新inStart和inEnd, continue;
         */
        for (int i = 0; i < starts.length; i++) {
            int curStart = starts[i];
            int curEnd = map.get(starts[i]);
            if (!inserted) {
                if (inEnd < curStart) {
                    result.add(new Interval(inStart, inEnd));
                    result.add(new Interval(curStart, curEnd));
                    inserted = true;
                } else if (inEnd >= curStart
                        && inEnd <= curEnd) {
                    if (inStart <= curStart) {
                        result.add(new Interval(inStart, curEnd));
                    } else {
                        result.add(new Interval(curStart, curEnd));
                    }
                    inserted = true;
                } else if (inStart > curEnd){//当前插入list
                    result.add(new Interval(curStart, curEnd));
                } else {
                    /**
                     *  inEnd >= curEnd的情况:
                     *  此时无法直接加入list,得保证和后面的interval不会合并
                     *  和当前区间合并后, 更新inStart, 然后continue
                     */
                    if (inStart >= curStart) {
                        inStart = curStart;
                    }
                }
            } else {
                result.add(new Interval(starts[i], map.get(starts[i])));
            }
        }
        if (!inserted) {
            result.add(new Interval(inStart, inEnd));
        }
        return result;
    }


    public static void main (String[] args) {
//        Interval in1 = new Interval(1,2);
        Interval in2 = new Interval(1, 10);
        Interval in3 = new Interval(13, 14);
////        Interval in4 = new Interval(4,6);
//        Interval in5 = new Interval(6,8);
//        Interval in6 = new Interval(8,10);
//        Interval in7 = new Interval(12,16);
        List<Interval> inters = new ArrayList<Interval>();
        inters.add(in2);
        inters.add(in3);
//        inters.add(in5);
//        inters.add(in7);
        List<Interval> result = insert(inters, new Interval(9, 11));
        for (Interval in : result) {
            System.out.println(in.start + "**" + in.end + "\n");
        }
    }
}
