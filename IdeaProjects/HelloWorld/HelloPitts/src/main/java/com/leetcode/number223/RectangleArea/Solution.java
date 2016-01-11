package com.leetcode.number223.RectangleArea;

import java.util.Arrays;

/**
 * Find the total area covered by two rectilinear rectangles in a 2D plane.
 * Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.
 *
 * Created by Peixin Lu on 15/12/28.
 */
public class Solution {

    /**
     * 计算两个矩阵叠合矩阵的面积
     * 每个矩阵是由2个对角点定义的:
     *    第一个是AB, 和CD
     *    第二个是EF, 和GH
     * 两个矩阵a, b在平面上的分布有四种可能性:
     *  (1) 两个矩阵完全分离 或者 只边重合, return 0;
     *  (2) 两个矩阵有叠合, 根据不同的叠合方式求面积即可
     *
     * 第一次submit失败,因为没有考虑到其中一个矩形如果是一个点或者一条线,重合面积应该为另外一个矩形的面积!
     *
     * @param A a的左下角x坐标
     * @param B a的左下角y坐标
     * @param C a的右上角x坐标
     * @param D a的右上角y坐标
     * @param E b的左下角x坐标
     * @param F b的左下角y坐标
     * @param G b的右上角x坐标
     * @param H b的右上角y坐标
     * @return
     */
    public static int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        //先计算两个矩形分别的面积
        int areaA = (C - A) * (D - B);
        int areaB = (G - E) * (H - F);
        if (!isOverlapped(A,B,C,D,E,F,G,H)) {
            return areaA + areaB;//各自分开算相加即可
        }

        //叠合情况下, 有多种叠合方式
        //但是我们可以总结规律: AECG四个比较, BDGH四个比较. 找出4个中排序后的中间两个数, 相减即为重叠部分面积
        if (areaA == 0) return areaB;
        if (areaB == 0) return areaA;
        int[] edge1 = new int[4];
        int[] edge2 = new int[4];
        edge1[0] = A;
        edge1[1] = E;
        edge1[2] = C;
        edge1[3] = G;
        Arrays.sort(edge1);
        //求得一个边
        int a = edge1[1] - edge1[2];
        edge2[0] = B;
        edge2[1] = D;
        edge2[2] = F;
        edge2[3] = H;
        Arrays.sort(edge2);
        //求得另一个边
        int b = edge2[1] - edge2[2];
        int overlapped = a * b < 0 ? -(a * b) : a * b;
        return areaA + areaB - overlapped;
    }

    private static boolean isOverlapped(int A, int B, int C, int D, int E, int F, int G, int H) {
        if (E >= C || G <= A) return false;
        if (B >= H || D <= F) return false;
        return true;
    }

    public static void main(String[] args) {
        System.out.println(computeArea(0,0,0,0,3,3,4,4));
    }
}
