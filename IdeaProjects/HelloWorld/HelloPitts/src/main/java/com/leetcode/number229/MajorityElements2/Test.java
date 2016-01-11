package com.leetcode.number229.MajorityElements2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by PeixinLu on 15/12/31.
 */
public class Test {
    public static List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<Integer>();
        if (nums.length == 0) return res;
        else return majorityElement(nums, 3);
    }

    private static List<Integer> majorityElement(int[] nums, int k) {
        int cnt = 2;
        ArrayList<Integer> candidates = new ArrayList<Integer>();
        ArrayList<Integer> count = new ArrayList<Integer>();

        for (int i = 0; i < cnt; i++) {
            candidates.add(0);
            count.add(0);
        }

        for (int num : nums) {
            boolean found = false;
            for (int i = 0; i < cnt; i++) {
                if (count.get(i) == 0 || candidates.get(i) == num)
                {
                    int c = count.get(i);
                    count.set(i, c + 1);
                    candidates.set(i, num);
                    found = true;
                    break;
                }
            }

            if (!found) {
                for (int i = 0; i < cnt; i++) {
                    int c = count.get(i);
                    count.set(i, c - 1);
                }
            }
        }

        Collections.fill(count, 0);
        for (int num : nums) {
            for (int i = 0; i < cnt; i++) {
                if (candidates.get(i) == num) {
                    int c = count.get(i);
                    count.set(i, c + 1);
                    break;
                }
            }
        }

        List<Integer> ans = new ArrayList<Integer>();

        for (int i = 0; i < cnt; i++) {
            if (count.get(i) > nums.length / k) {
                ans.add(candidates.get(i));
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        List<Integer> rst = majorityElement(new int[]{1,2,3,4,1,2,3,2,1,2,4,2});
        for (Integer i : rst) {
            System.out.println(i);
        }
    }

}
