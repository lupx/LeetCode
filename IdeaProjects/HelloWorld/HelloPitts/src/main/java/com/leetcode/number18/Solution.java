package com.leetcode.number18;

import java.util.*;

/**
 * 4SUM!
 *
 * Created by PeixinLu on 15/9/21.
 */
public class Solution {

    /**
     * 4SUM
     * Using 3 SUM
     * @param nums
     * @param target
     * @return
     */
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        if(nums == null || nums.length < 4) return list;
        Arrays.sort(nums);
        for(int i = 0; i < nums.length; i++) {
            if(map.containsKey(nums[i])) continue;
            List<List<Integer>> l = threeSum(nums, i+1, target - nums[i]);
            for(List<Integer> ls: l) {
                List<Integer> ll = new ArrayList<Integer>();
                ll.add(nums[i]);
                ll.addAll(ls);
                list.add(ll);
            }
            map.put(nums[i],1);
        }
        return list;
    }

    /**
     * From start point, search nums, to find 3sum = target
     * @param nums
     * @param start from which point to start the searchs
     * @param target
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums, int start, int target) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        if(nums == null || nums.length < 3) return list;
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

    public static void main(String[] args) {
        System.out.println(fourSum(new int[]{-1,-5,-5,-3,2,5,0,4},-7));
    }
}
