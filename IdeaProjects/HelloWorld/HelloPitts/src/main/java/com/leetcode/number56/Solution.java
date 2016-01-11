package com.leetcode.number56;

import java.util.*;

/**
 * Given a collection of intervals, merge all overlapping intervals.
 * For example,
 * Given [1,3],[2,6],[8,10],[15,18],
 * return [1,6],[8,10],[15,18].
 *
 * Created by PeixinLu on 15/10/11.
 */
public class Solution {

    /**
     * 合并区间
     * 每个区间的起点排序放入一个数组,并且把每个区间放入一个map
     *
     * 然后遍历这个数组, 每遇到一个数字:
     * 从map中取出这个interval,
     *    如果重叠, 就拓展当前区间的结尾, map中删去这个interval
     *    如果不重叠, 旧区间存入list, 新建一个区间, 标记首尾点.
     *
     * 最终, 返回这个list
     * @param intervals
     * @return
     */
    public static List<Interval> merge(List<Interval> intervals) {
        List<Interval> result = new ArrayList<Interval>();
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        for (int i = 0; i < intervals.size(); i++) {
            Integer ends = map.getOrDefault(intervals.get(i).start, Integer.MIN_VALUE);
            if (ends == Integer.MIN_VALUE) {
                ends = intervals.get(i).end;
                map.put(intervals.get(i).start, ends);
            } else if (ends < intervals.get(i).end) {
                map.put(intervals.get(i).start, intervals.get(i).end);
            }
        }

        int[] starts = new int[map.size()];
        int i = 0;
        Iterator itr = map.keySet().iterator();
        while (itr.hasNext()) {
            starts[i] = (Integer)itr.next();
            i++;
        }

        //到这里就得到了唯一起点的区间, 和唯一的starts数组
        Arrays.sort(starts);//起点排序
        int currentEnd = Integer.MIN_VALUE;
        int currentStart = Integer.MIN_VALUE;
        for (int j = 0; j < starts.length; j++) {
            //取每个数出来作为区间的起点
            if (starts[j] > currentEnd) {
                //找到新起点, 旧区间存入List
                //但是要避免把min_value放入list中
                if (currentEnd != Integer.MIN_VALUE) {
                    Interval inter = new Interval(currentStart, currentEnd);
                    result.add(inter);
                }
                currentStart = starts[j];
                currentEnd = map.get(starts[j]);
            } else {
                //当前起点在前一个区间之内
                if (map.get(starts[j]) > currentEnd) {
                    currentEnd = map.get(starts[j]);
                }
            }
        }
        if (currentEnd != Integer.MIN_VALUE) {
            Interval inter = new Interval(currentStart, currentEnd);
            result.add(inter);
        }
        return result;
    }


    public static void main (String[] args) {
        Interval in1 = new Interval(1,3);
//        Interval in2 = new Interval(1,2);
//        Interval in3 = new Interval(1,5);
        Interval in4 = new Interval(2,6);
        Interval in5 = new Interval(7,9);
        Interval in6 = new Interval(15,18);
        Interval in7 = new Interval(8,10);
        List<Interval> inters = new ArrayList<Interval>();
        inters.add(in1);
//        inters.add(in2);
//        inters.add(in3);
        inters.add(in4);
        inters.add(in5);
        inters.add(in6);
        inters.add(in7);
        List<Interval> result = merge(inters);
        for (Interval in : result) {
            System.out.println(in.start + "**" + in.end + "\n");
        }

    }
}
