package com.leetcode.number228.SummaryRanges;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a sorted integer array without duplicates, return the summary of its ranges.
 * For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
 * 在排好序的数组里, 找出所有的连续数字的范围
 * Created by Peixin Lu on 15/12/30.
 */
public class Solution {

    /**
     * 这个题没有技巧, 遍历数组硬做即可
     * @param nums
     * @return
     */
    public static List<String> summaryRanges(int[] nums) {
        List<String> rst = new ArrayList<>();
        if (nums == null || nums.length == 0) return rst;
        int n = nums.length;
        int start = -1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            if (start == -1) {
                start = i;
                sb.append(nums[start]);
                if ((i < n - 1 && nums[i] + 1 != nums[i + 1])
                        || i == n - 1) {
                    rst.add(sb.toString());
                    sb = new StringBuilder();
                    start = -1;
                }
            } else {
                //此时前面有start点, 看看什么时候end
                if ((i < n - 1 && nums[i] + 1 != nums[i + 1])
                        || i == n - 1) {
                    sb.append("->").append(nums[i]);
                    rst.add(sb.toString());
                    sb = new StringBuilder();
                    start = -1;
                }
            }
        }
        return rst;
    }

    public static void main(String[] args) {
        List<String> rst = summaryRanges(new int[]{0,1,2,8,9,11});
        for (String str: rst) {
            System.out.println(str);
        }
    }
}
