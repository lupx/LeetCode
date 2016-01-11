package com.leetcode.number229.MajorityElements2;

import java.util.*;

/**
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
 * The algorithm should run in linear time and in O(1) space. 要求O(n)时间和O(1)空间.
 * 这个要求基本已经指明需要使用moore voting来实现了!
 *
 * Created by Peixin Lu on 15/12/31.
 */
public class Solution {

    /**
     * 使用moore voting来实现
     * 速度比较慢, 因为是用map来实现的, 所以beat 4.11%
     * 要快的话, 可以用数组实现, 或者list实现, 都行
     *
     * 注意: 虽然用了map, 但并不代表空间是O(n), 因为map被严格控制在2个元素, 所以还是常数空间!
     * @param nums
     * @return
     */
    public static List<Integer> majorityElement(int[] nums) {
        List<Integer> rst = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        if (nums == null || nums.length == 0) return rst;
        int n = nums.length;
        for (Integer i : nums) {
            if (map.size() < 2) {
                if (map.containsKey(i)) {
                    int count = map.get(i);
                    count++;
                    map.put(i, count);
                } else map.put(i, 1);
            } else {
                if (map.containsKey(i)) {
                    int count = map.get(i);
                    map.put(i, ++count);
                    continue;
                }
                Iterator<Integer> itr = map.keySet().iterator();
                while(itr.hasNext()) {
                    int key = itr.next();
                    int count = map.get(key);
                    count--;
                    if (count == 0) itr.remove();
                    else map.put(key, count);
                }
            }
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            entry.setValue(0);
        }
        //此时map中所有的元素只能算是候选元素, 还需要遍历一遍nums, 判断其总个数
        for (Integer i : nums) {
            if (map.containsKey(i)) {
                int count = map.get(i);
                count++;
                map.put(i, count);
            }
        }

        //遍历map,找出所有count>n/3的元素
        Iterator<Integer> itr = map.keySet().iterator();
        while (itr.hasNext()) {
            int key = itr.next();
            int count = map.get(key);
            if (count > n / 3) {
                rst.add(key);
            }
        }
        return rst;
    }

    public static void main(String[] args) {
        List<Integer> rst = majorityElement(new int[]{1,2});
        for (Integer i : rst) {
            System.out.println(i);
        }
    }
}
