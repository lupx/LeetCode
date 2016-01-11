package com.leetcode.number241.DifferentWaystoAddParentheses;

import java.util.*;

/**
 * Given a string of numbers and operators, return all possible results from computing all the different possible ways
 *   to group numbers and operators. The valid operators are +, - and *.
 *
 * Example 1
 * Input: "2-1-1".
 *
 * ((2-1)-1) = 0
 * (2-(1-1)) = 2
 * Output: [0, 2]
 *
 * Example 2
 * Input: "2*3-4*5"
 *
 * (2*(3-(4*5))) = -34
 * ((2*3)-(4*5)) = -14
 * ((2*(3-4))*5) = -10
 * (2*((3-4)*5)) = -10
 * (((2*3)-4)*5) = 10
 * Output: [-34, -14, -10, -10, 10]
 *
 * 给表达式加括号, 使表达式算出所有可能的结果, 并把所有结果输出
 *
 *
 *
 *
 * Created by Peixin Lu on 16/1/1.
 */
public class Solution {

    //把操作符缓存一下
    Set operators = new HashSet(Arrays.asList('+','-','*'));

    /**
     * 这是copy别人的代码
     * 核心思想是用分治:
     * helper函数里, 每次找到第一个算符, 然后把input分成两部分, 两部分分别递归进去!
     * beat 83.92%
     * @param input
     * @return
     */
    public List<Integer> diffWaysToCompute(String input) {
        Map<String, List<Integer>> cache = new HashMap<>();
        return helper(input, cache);
    }

    private List<Integer> helper(String input, Map<String, List<Integer>> cache) {
        if(cache.containsKey(input)) return cache.get(input);

        List<Integer> vals = new ArrayList<Integer>();

        for(int i=0; i<input.length(); i++) {
            if(!operators.contains(input.charAt(i))) continue; //找第一个算符
            char operator = input.charAt(i);
            List<Integer> leftVals = helper(input.substring(0,i), cache);
            List<Integer> rightVals = helper(input.substring(i+1), cache);

            for(Integer left : leftVals) {
                for(Integer right : rightVals) {
                    if(operator == '+') {
                        vals.add(left + right);
                    } else if(operator == '-') {
                        vals.add(left - right);
                    } else if(operator == '*') {
                        vals.add(left*right);
                    }
                }
            }
        }

        // number only, this is base condition
        if(vals.size() == 0) {
            vals.add(Integer.parseInt(input));
        }

        cache.put(input, vals); //把某段string的所有计算结果加入cache.
        return vals;
    }

    public static void main(String[] args) {
    }
}
