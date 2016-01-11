package com.leetcode.number15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by PeixinLu on 15/9/21.
 */
public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(nums == null || nums.length < 3) return result;
        Arrays.sort(nums);
        for(int i = 0; i < nums.length; i++){
            int low = i+1;
            int high = nums.length -1;
            while(low < high){
                if(nums[i] + nums[low] + nums[high] == 0){
                    result.add(Arrays.asList(nums[i], nums[low], nums[high]));
                    while(i + 1 < nums.length && nums[i + 1] == nums[i]) i++;//去重考虑.
                    while(low + 1 < nums.length && nums[low] == nums[low + 1]) low++;//因为i移动的话,low肯定要移动.最终low肯定停留在i后一位,所以没问题
                    while(high - 1 >= 0 && nums[high] == nums[high - 1]) high--;//去重考虑
                    /**
                     * 去重结束后,low和high往中间汇合
                     * 并且,在当前low+high = 0 - nums[i]的情况下,low和high是唯一的组合.所以可以同时推移
                     */
                    low++;
                    high--;
                } else if(nums[i] + nums[low] + nums[high] > 0) {
                    /**
                     * 不等情况(1)
                     */
                    high--;
                } else low++;//不等情况(2)
            }
        }
        return result;
    }
}
