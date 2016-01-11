package com.leetcode.number80;

import java.util.*;

/**
 * Follow up for "Remove Duplicates":
 * What if duplicates are allowed at most twice?
 * For example,
 * Given sorted array nums = [1,1,1,2,2,3],
 * Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3.
 * It doesn't matter what you leave beyond the new length.
 *
 * Created by PeixinLu on 15/10/16.
 */
public class Solution {

    /**
     * 去除重复的,并且最多只能留2个. 返回结果的同时, 还要保证数组真实地修改掉了!
     * 解法有2个:
     * 解法(1):
     * 计数并存list, 遍历list数个数, >2的减到2个, 最终将list输出到nums中,并返回总个数
     *
     * @param nums
     * @return
     */
    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int total = 0;
        List<Integer> list = new LinkedList<Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (list.size() == 0) {
                list.add(nums[i]);
                list.add(1);
                total++;
            }
            else {
                if (nums[i] == list.get(list.size() - 2)) {
                    if (list.get(list.size() - 1) == 2) {
                        continue;
                    }
                    int n = list.remove(list.size() - 1) + 1;
                    list.add(n);
                } else {
                    list.add(nums[i]);
                    list.add(1);
                }
                total++;
            }
        }

        //输出到数组
        int j = 0;
        for (int i = 0; i < list.size(); i += 2) {
            int thisNum = list.get(i);
            int count = list.get(i + 1);
            while(count > 0) {
                nums[j] = thisNum;
                count--;
                j++;
            }
        }
        return total;
    }



    /**
     * 解法(2), i, j 快慢指针:
     *    i指针遍历nums,
     *       nums[i]!=nums[i+1]的时候,continue,总计数++
     *       nums[i]==nums[i+1]的时候,
     *          j = i,遍历剩余部分,找重复了多少个:
     *             若重复次数<=2, 那么i移动到j-1, continue
     *             重复次数>2次, i移动到第3个的位置, 然后j往后移动直到下一个不同的数字处
     *             然后j开始计数移动, 每移动一位计数+1, 在遇到下一个不同数字之前, 给出计数值.
     *             计数值>2时, i往右移动2位,每一位填nums[j]
     *             计数值<=2时, i往右移动计数值位,每一位填nums[j]
     * @param nums
     * @return
     */
    public static int removeDuplicatesv2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int total = 0;
        int i = 0, j = 0, dupCount = 1;
        while (i < nums.length) {
            if (i < nums.length - 1
                    && nums[i] != nums[i + 1]) {
                total++;
                i++;
                dupCount = 1;
            } else {
                dupCount++;//开始出现重复了
                if (dupCount > 2) {
                    total++;
                    i++;
                    break;
                } else {
                    total++;
                    i++;
                }
            }
        }//此循环结束, i在第一个需要修改的位置
        if (i == nums.length - 1) {
            return total;
        }
        j = i;
        while (j < nums.length) {
            //先用j找到i数字之后, 下一个不同的数字,
            //然后按照其个数推移i并填数
            while (j < nums.length - 1
                    && nums[j] == nums[j + 1])
                j++;
            j++;//下一个不同的数字
            if (j < nums.length) {
                //然后数有几个这样的数字
                int jcount = 1;
                while (j < nums.length - 1
                        && nums[j] == nums[j + 1]) {
                    jcount++;
                    j++;
                }
                int fill = 2;
                if (jcount > 2) {
                    fill = 2;//最多填2个过来
                } else {
                    fill = jcount;
                }
                //填i,并推移i
                while (fill > 0) {
                    nums[i] = nums[j];
                    total++;
                    fill--;
                    i++;
                }
            }
        }
        return total;
    }
    public static void main(String[] args) {
        int[] nums = new int[]{1,1,2,2,3};
        System.out.println(removeDuplicatesv2(nums));
    }
}

