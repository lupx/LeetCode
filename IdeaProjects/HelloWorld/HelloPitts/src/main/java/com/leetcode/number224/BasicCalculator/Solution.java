package com.leetcode.number224.BasicCalculator;

import java.util.Arrays;
import java.util.Stack;

/**
 * Implement a basic calculator to evaluate a simple expression string.
 * The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .
 * You may assume that the given expression is always valid.
 * Some examples:
 * "1 + 1" = 2
 * "2- 1 + 2 " = 3
 * "(1+(4+5+2)-3)+(6+8)" = 23
 *
 * Created by Peixin Lu on 15/12/30.
 */
public class Solution {

    /**
     * 先去掉空格, 剩下的应该只有括号, +, - 以及 数字.
     * 下面算法有问题, 错误地处理了(), 放在这里引以为戒!
     * @param s
     * @return
     */
    public static int calculate(String s) {
        if (s == null || s.length() == 0) return 0;
        s = s.replaceAll(" ", "");
        //因为只有+,-, 所以括号的存在没有意义
        s = s.replaceAll("\\(", "");
        s = s.replaceAll("\\)", "");
        String[] exps = s.split("\\+");
        int rst = 0;
        // exps中各个元素相加.
        // 但是其中有的可能含有-, 需要分别做相减处理
        for (String str : exps) {
            if (str.contains("-")) {
                rst += substract(str);
            } else {
                rst += Integer.parseInt(str);
            }
        }

        return rst;
    }

    /**
     * 计算一个只有数字和减号的表达式的值
     * @param s
     * @return
     */
    private static int substract(String s) {
        String[] exps = s.split("\\-");
        int rst = Integer.parseInt(exps[0]);
        for (int i = 1; i <exps.length; i++) {
            rst -= Integer.parseInt(exps[i]);
        }
        return rst;
    }

    /**
     * 正确版本
     * 先去空格
     * 再遍历s
     * @param s
     * @return
     */
    public static int calculate_2 (String s) {
        if (s == null || s.length() == 0) return 0;
        StringBuilder sb = new StringBuilder();
        int rst = 0;
        boolean isAdd = true;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); ) {
            Character c = s.charAt(i);
            if (c == ' ') continue;
            if (c <= '9' && c >= '0') {
                sb.append(c);
                i++;
            }
            if (c == '+' || c == '-') {
                if (isAdd) {
                    rst += Integer.parseInt(sb.toString());
                } else {
                    rst -= Integer.parseInt(sb.toString());
                }
                sb = new StringBuilder();
                isAdd = c == '+';
                i++;
            }
            //下面开始处理遇到括号的情况
            if (c == '(') {
                stack.push(c);
                int k = i + 1;
                while (k < s.length()) {
                    Character kc = s.charAt(k);
                    if (kc == '(') {
                        stack.push(kc);
                    }
                    if (kc == ')') {
                        stack.pop();
                        if (stack.isEmpty()) {
                            break;
                        }
                    }
                    k++;
                }
                sb.append(calculate_2(s.substring(i + 1, k)));
                i = k + 1;
            }
        }
        if (isAdd) {
            rst += Integer.parseInt(sb.toString());
        } else {
            rst -= Integer.parseInt(sb.toString());
        }
        return rst;
    }

    /**
     * 速度更快, 全部用栈来实现
     * 核心思想是, 遇到非)的先全部压栈.
     * 直到遇到), 开始弹栈, 弹到最近一个(, 中间的表达式算一个结果, 再压入栈中
     * 如此往复, 最终s整个处理完, stack中保留的就是没有括号的纯+-表达式, 再一一弹栈计算最终结果即可!
     * 速度快了一些: beat 19.63%
     * @param s
     */
    public static int calculateFaster(String s) {
        if (s == null || s.length() == 0) return 0;
        int rst = 0;
        Stack<String> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (c == ' ') continue;
            if (c <= '9' && c>= '0') {
                sb.append(c);
            } else {
                //c不是数字, 那么sb部分可以压栈
                if (sb.length() != 0) stack.push(sb.toString());
                sb = new StringBuilder();
                if (c != ')') stack.push(String.valueOf(c)); // +, -号 以及左括号不做任何处理直接入栈
                else {
                    //c == ')', 开始弹栈, 同时弹栈的过程中计算
                    //弹到最近的一个左括号就停止
                    int tmp = 0;
                    while (!stack.peek().equals("(")) {
                        String token = stack.pop();//得出括号中各个字符串,可能是数字,可能是+,-号
                        int sign = 1;
                        if (!stack.peek().equals("(")) {
                            String op = stack.pop();
                            sign = op.equals("+") ? 1 : -1;
                        }
                        tmp += sign * Integer.parseInt(token);
                    }
                    stack.pop();//把匹配的左括号弹出来
                    stack.push(String.valueOf(tmp));//把值压入
                }
            }
        }
        if (sb.length() != 0) {
            stack.push(sb.toString());
        }
        //此时, stack中只剩全数字以及+,-号
        while (!stack.isEmpty()) {
            String token = stack.pop();//得出括号中各个字符串,可能是数字,可能是+,-号
            int sign = 1;
            if (!stack.isEmpty() && !stack.peek().equals("(")) {
                String op = stack.pop();
                sign = op.equals("+") ? 1 : -1;
            }
            rst += sign * Integer.parseInt(token);
        }
        return rst;
    }

    public static void main(String[] args) {
        System.out.println(calculateFaster("3 + 1 - (3 + 9 - (2 -5))"));
//        System.out.println("012345678".substring(4,7));
    }
}
