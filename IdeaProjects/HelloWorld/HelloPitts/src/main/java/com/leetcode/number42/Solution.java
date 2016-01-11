package com.leetcode.number42;

import java.util.ArrayList;
import java.util.List;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it is able to trap after raining.
 * For example,
 * Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 *
 * Created by Peixin Lu on 15/10/5.
 */
public class Solution {

    /**
     * 排除特殊情况
     * 用DP思想来做, 在遍历的过程中实时计算结果累加.
     * i从0开始遍历
     * 如果h[i] >= h[i-1], continue
     * 否则j = i + 1, 遍历,
     *    如果h[j] >= h[i], continue
     *    否则, 记录几个变量:
     *    1 maxPoten, 说明有潜在留水, 在遇到下一个比h[i]高的墙的时候, 这个maxPoten就是潜在最大留水量(以h[i]为高);
     *    2 deepest, 从j开始, deepest=h[j], 之后没遇到比deepest低的,就更新deepest,否则不动
     *    3 lower, 在遍历过程中,记录下最后一个遇到的高度仅次于h[i]的墙,记录下来. 如果j遍历全程没有遇到高墙,那么lower就是高墙;
     *    4 lowerPoten, lower决定的水量, 如果j遍历全程未遇到高墙, 这个值就是这个过程中的实际存水量, 然后i = lower. continue;
     *    5 lowerBehind, lower墙后的水量,在必要时候要减去,才能算出正确的lowerPoten
     *
     * 同时注意以下细节:
     *  (1)每次continue后(i移动到j的位置的时候), 临时变量加入总和中, 几个临时变量全部归0;
     *  (2)同时有公式: lowerPoten = maxPoten - lowerBehind - stepWater - (h[i] - h[lower]) * (lower - 最右紧邻台阶).
     *  (3)只有当h[j] > h[j-1]的时候, 说明出现了升序, 才开始更新lower. 之后遇到更大的就更新lower
     *  (4)每当更新lower的时候, lowerBehind一并更新
     *
     *
     * @param height
     * @return
     */
    public static int trap(int[] height) {
        if (height == null || height.length == 0) return 0;

        int result = 0;
        int i = 0;
        while (i < height.length) {
            //每次i移动后, 几个变量要归0
            int maxPoten = 0;
            int lowerPoten = 0;
            int lower = -1;
            int lowerBehind = 0;//lower后的总水量,必要时候要减掉,每次lower更新后,此值更新到0,并重新累积
            if (i < height.length  - 1 && height[i] <= height[i + 1]) {
                i++;
                continue;//当前i比后一个矮, 直接continue, 细节1
            }
            List<Integer> union = new ArrayList<Integer>();//保存和i墙紧邻的所有墙
            boolean isUnion = true;
            int j  = i + 1;
            while (j < height.length) {
                if (height[j] >= height[i]) {
                    //找到了比height[i]还高或者一样高的, 直接break;
                    //当前的maxPoten就是临时结果,直接加入result即可
                    break;
                }
                if (lower == -1 && height[j] > height[j - 1]) {
                    //第一次出现了升序, lower更新到第一个位置, 细节4
                    lower = j;
                }
                if (height[j] == 0) {
                    isUnion = false;
                }
                maxPoten += height[i] - height[j];//每一步都要计算maxPoten
                lowerBehind += height[i] - height[j];//细节2, lower后的面积需要计算出来,必要时候要减去
                if (isUnion) {
                    union.add(j);//最终计算的时候, union部分也需要考虑
                }
                if (lower != -1 && height[j] >= height[lower]) {
                    lower = j;//更新lower到最远,且高度仅次于height[i]的位置
                    //每次更新lower后,lowerBehind从头算
                    lowerBehind = 0;
                }
                j++;
            }
            // 有2个可能性:
            // (1) break出来的, 那么maxPoten直接加入result, continue
            // (2) j遍历完了整个数组, 说明maxPoten不可用, 那么计算lowerPoten
            if (j == height.length) {
                //说明是正常遍历完的, lower起作用了
                //计算lowerPoten
                //先算起初台阶的存水,这部分要区分对待
                if (lower == -1) {
                    //说明也没找到lower, 说明就是一直降的台阶, 循环直接结束
                    break;
                }
                int stepWater = 0;
                int k = 0;
                if (union.size() > 0) {//计算台阶
                    while (k < union.size() && height[union.get(k)] > height[lower]) {
                        stepWater += height[i] - height[i + k + 1];//细节3, 计算step上的水量
                        k++;
                    }
                }
                //此外, maxPoten应截止到lower, lower后的水不能再算进去.
                lowerPoten = maxPoten - stepWater - lowerBehind - (height[i] - height[lower]) * (lower - (i + k));
                result += lowerPoten;
                i = lower; // i更新到lower位置
            } else {
                //说明是break出来的
                result += maxPoten;
                i = j; //i移动到j的位置
            }
        }

        return result;
    }


    public static void main(String[] args) {
        int[] height = new int[]{9,6,8,8,5,6,3};
        System.out.println(trap(height));
    }
}
