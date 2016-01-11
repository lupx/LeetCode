package com.leetcode.number16;

import java.util.*;

/**
 * Created by PeixinLu on 15/9/21.
 */
public class SolutionTwoSum {
    public int threeSumClosest(int[] nums, int target) {
        int len = nums.length;
        if(len == 3) return nums[0] + nums[1] + nums[2];
        Arrays.sort(nums);

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        int tmpSum = 0;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; i++) {
            int devi = 0;
            if(map.containsKey(nums[i])) continue; //跳过之前已经处理过的数字,防止重复
            else map.put(nums[i], 1); //只要处理就记录
            int twoSum = (target + devi) - nums[i];
            tmpSum = twoSum(nums, twoSum, i + 1);
            if(tmpSum < min) min = tmpSum;
        }


        return min;
    }

    public static int twoSum(int[] nums, int target, int start) {
        return 0;
    }
}
