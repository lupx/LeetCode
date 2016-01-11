package com.leetcode.number15;


import java.util.*;

/**
 * 3-SUM.
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0?
 * Find all unique triplets in the array which gives the sum of zero.
 * Notes:
 * Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
 * The solution set must not contain duplicate triplets.
 *
 * 若没有,比如nums根本就不够3个数字,返回一个空list,但不能返回null
 * Created by Peixin Lu on 15/9/14.
 */
public class SolutionTwoSum {

    /**
     * Using TwoSum
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> rt = new ArrayList<List<Integer>>();
        List<Integer> l= new ArrayList<Integer>();
        if(nums.length < 3) return rt;
        Arrays.sort(nums);
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < nums.length; i++) {
            if(map.containsKey(nums[i])) continue; //跳过之前已经处理过的数字,防止重复
            else map.put(nums[i], 1); //只要处理就记录
            int twoSum = 0 - nums[i];
            rt.addAll(twoSum(nums, nums[i], twoSum, i+1));
        }
        return rt;
    }

    public static List<List<Integer>> twoSum(int[] nums, int first, int sum, int start) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        List<List<Integer>> rt = new ArrayList<List<Integer>>();
        for(int i = start; i < nums.length; i++) {
            int x = sum - nums[i];
            if(x > nums[nums.length - 1]) continue; // 两者差在数组中不可能出现,continue
            List<Integer> l = new ArrayList<Integer>();

            /**
             * 这个题求twoSum的难点在于,可能有重复数字,这是和leetCode第一题的最大区别(第一题的follow-up可能就考这个点)
             * 那么一旦遇到重复数字,如果不做任何处理,就会有重复的结果. 比如 -3, -1, 4, 4, 4. 就会出现3个相同的结果.
             * 所以,我们在没遇到重复数字的时候,都先考虑跳过,跳到最后一个出现的该数字. 这个时候,可能出现一种情况:
             * -2,....,1,1
             * 2个1跳过第一个1, 显然就会漏掉-2,1,1这组解.
             * 所以,我们引入这种极特殊情况的检查, count值主要做这种情况的检查:
             * 首先count值为1, 只要遇到重复情况,我们count要加.
             * 最终,count值就是重复数字出现的次数
             * 然后, 我们考虑上面说的情况. 上面的情况其实就是 差值 = 自身的情况, 所以如果自身出现不止一次,那就应该算进去,然后再跳过
             * 所以,我们后面做一个判断, if(x == nums[i] && count >=2) 然后把这个特殊情况考虑进去,然后continue;
             *
             */
            int count = 1;
            while(i < nums.length - 1 && nums[i] == nums[i + 1]) {
                i++;
                count++;
            }
            if(x == nums[i] && count >=2) {
                l.add(first);
                l.add(x);
                l.add(nums[i]);
                rt.add(l);
                continue;
            }

            /**
             * 剩下的就没有特殊的了, 由于重复数字会跳过, 所以也不会有重复结果
             * 如果还没有出现差值, 就put进map
             */
            if(map.containsKey(x)) {
                l.add(first);
                l.add(x);
                l.add(nums[i]);
                rt.add(l);
            } else {
                map.put(nums[i], 1);
            }
        }
        return rt;
    }


    public static void main(String[] args) {
        List<List<Integer>> list = threeSum(new int[]{-5,-3,-3,-2,-2,-2,-1,-1,0,1,1,1,2,3,3,4,4});
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
