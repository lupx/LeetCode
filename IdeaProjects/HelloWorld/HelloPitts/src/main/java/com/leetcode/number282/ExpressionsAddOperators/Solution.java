package com.leetcode.number282.ExpressionsAddOperators;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string that contains only digits 0-9 and a target value,
 *  return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.
 * Examples:
 * "123", 6 -> ["1+2+3", "1*2*3"]
 * "232", 8 -> ["2*3+2", "2+3*2"]
 * "105", 5 -> ["1*0+5","10-5"]
 * "00", 0 -> ["0+0", "0-0", "0*0"]
 * "3456237490", 9191 -> []
 * Created by Peixin Lu on 16/1/7.
 */
public class Solution {

    /**
     * 递归+分治
     * 思想是, 遍历num, 把num分成左右两边, 然后两边分别用+,-,*连接看结果, 而右边的结果则递归.
     * 此解法只能过13个case
     * @param num
     * @param target
     * @return
     */
    public static List<String> addOperators(String num, int target) {
        List<String> rst = new ArrayList<>();
        if (num == null || num.length() == 0) return rst;
        helper(num, target, 0, rst, "");
        return rst;
    }

    private static void helper(String num, int target, int start, List<String> rst, String tmp) {
        StringBuilder sb = new StringBuilder(tmp);
        if (num.equals("")) return;
        if (num.equals(String.valueOf(target))) {
            sb.append(num);
            rst.add(sb.toString());
        } else {
            for (int i = 0; i < num.length(); i++) {
                if (i < start - 1) continue;
                long left = Long.parseLong(num.substring(0, i + 1));
                //先考虑乘, 然后考虑+, -
                // * 的情况比较复杂
                String right = num.substring(i + 1);
                if (right.equals("")) return;
                for (int j = 0; j < right.length(); j++) {
                    String rightConsider = right.substring(0, j + 1);
                    if (rightConsider.length() == 1 || rightConsider.charAt(0) != '0') {
                        long rightNum = Long.parseLong(rightConsider);
                        long result = left * rightNum;
                        if (sb.length() != 0
                                && sb.charAt(sb.length() - 1) >= '0' && sb.charAt(sb.length() - 1) <= '9')
                            sb.append("*").append(rightNum);
                        else sb.append(left).append("*").append(rightNum);
                        StringBuilder ss = new StringBuilder();
                        ss.append(result);
                        start = ss.toString().length();
                        String newStr = ss.append(right.substring(j + 1)).toString();
                        if (newStr.equals(String.valueOf(target))) {
                            rst.add(sb.toString());
                            return;
                        }
                        helper(newStr, target, start, rst, sb.toString());
                    }
                }

                //+
                sb = new StringBuilder(tmp);
                if (sb.length() != 0
                        && sb.charAt(sb.length() - 1) >= '0' && sb.charAt(sb.length() - 1) <= '9')
                    sb.append("+");
                else sb.append(left).append("+");
                helper(right, target - (int)left, 0, rst, sb.toString());

                //-
                sb = new StringBuilder(tmp);
                if (sb.length() != 0
                        && sb.charAt(sb.length() - 1) >= '0' && sb.charAt(sb.length() - 1) <= '9')
                    sb.append("-");
                else sb.append(left).append("-");
                helper(right, (int)left - target, 0, rst, sb.toString());
            }
        }
    }


    public static void main(String[] args) {
//        System.out.println("123".substring(2));
        List<String> rst = addOperators("12345", 25);
        for (String str: rst) {
            System.out.println(str);
        }
    }
}
