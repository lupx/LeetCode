package com.leetcode.number15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Two pointers to solve the 3SUM problem.
 * Created by PeixinLu on 15/9/21.
 */
public class MySolution {
    public static List<List<Integer>> threeSum(int[] nums, int start, int target) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        if(nums == null || nums.length < 3) return list;

        Arrays.sort(nums);

        for(int i = start; i < nums.length; i++){
            int low = i + 1;
            int high = nums.length - 1;
            while (low < high) {
                if(nums[i] + nums[low] + nums[high] == target) {
                    list.add(Arrays.asList(nums[i], nums[low], nums[high]));
                    while(i<nums.length-1&&nums[i] == nums[i+1]) i++;
                    while(low<nums.length-1 && nums[low] == nums[low+1]) low++;
                    while(high>1 && nums[high] == nums[high - 1]) high--;
                    low++;
                    high--;
                } else if (nums[i] + nums[low] + nums[high] > target) {
                    high--;
                } else low ++;
            }
        }
        return list;
    }

    public static void main(String[] args){
        List<List<Integer>> list = threeSum(new int[]{-1,2,1,4},1, 7);
        System.out.println(list.size());
        for(int i = 0; i < list.size(); i++) {
            List<Integer> l = list.get(i);
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j<l.size(); j++) {
                sb.append(l.get(j));
            }
            System.out.println(sb.toString());
        }
    }
}
