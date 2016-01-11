package com.leetcode.number149;

import java.util.*;

/**
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 * 找出平面中, 同处一条直线上最多的点的个数
 * Created by Peixin Lu on 15/11/6.
 */
public class Solution {

    /**
     * 两层循环:
     *  i: 0 -> n
     *    j: i+1 -> n
     *
     * 比较慢, 76ms...
     * @param points
     * @return
     */
    public static int maxPoints(Point[] points) {
        if (points == null) return 0;
        if (points.length < 3) return points.length;

        int max = 2;
        Map<Double, Integer> map;//保存斜率
        for (int i = 0; i < points.length; i++) {
            map = new HashMap<>();
            int tmp = 1;
            int count = 1;
            // 比较points[j]和points[i], 得出最大max
            for (int j = i + 1; j < points.length; j++) {
                Double x = 0.00000000;
                if (points[j].y == points[i].y
                        && points[j].x == points[i].x) {
                    count++;
                } else {
                    if (points[j].x == points[i].x
                            && points[j].y != points[i].y) {
                        //一条竖线上
                        x = Double.MAX_VALUE;
                    } else {
                        x = (double) (points[j].y - points[i].y) / (double) (points[j].x - points[i].x) + 0.00000001;
                    }
                    Integer val = map.getOrDefault(x, 1);
                    val++;
                    map.put(x, val);
                }
                if (map.size() != 0) { //当有有用斜率时, 每个斜率的点数 + 相同点个数 - 1, 减1是减去了最开始的原始点.
                    Iterator<Double> itr = map.keySet().iterator();
                    while (itr.hasNext()) {
                        Double a = itr.next();
                        if (max < map.get(a) + count - 1) max = map.get(a) + count - 1;
                    }
                } else {//无有用斜率, count就是当前轮最多的点数
                    if (max < count) max = count;
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
//        double x = (double)0  / (double)3;
//        System.out.println(x);
        Point p1 = new Point(1, 1);
        Point p2 = new Point(-1, -1);
        Point p3 = new Point(1, 0);
        Point p4 = new Point(1, 2);
        Point p5 = new Point(1, 1);
        Point[] p = new Point[]{p1,p2,p3,p4,p5};
        System.out.println(maxPoints(p));
    }


    static class Point {
        int x;
        int y;
        Point() { x = 0; y = 0; }
        Point(int a, int b) { x = a; y = b; }
    }
}

