package com.leetcode.number227.BasicCalculator2;

import java.util.Stack;

/**
 *
 * Created by Peixin Lu on 15/12/30.
 */
public class Solution {
    /**
     * 本题实际是去掉了括号, 在224的计算过程上加了个*和/. 同时题目保证所有输入都是合法的, 所以不用考虑/0的情况.
     * 不同点在于, *和/优先级高于+和-.
     * 还是得借助于栈解决
     * beat 15.53%
     * @param s
     */
    public static int calculate(String s) {
        if (s == null || s.length() == 0) return 0;
        int rst = 0;
        Stack<String> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        char sign = '+';
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (c == ' ') continue;
            if (c <= '9' && c >= '0') {
                sb.append(c);
            } else {
                if (sb.length() != 0) stack.push(sb.toString());
                sb = new StringBuilder();
                if (sign == '*' || sign == '/') {
                    //先把乘除解决了
                    int tmp = 1;
                    String str1 = stack.pop();
                    String str2 = stack.pop();
                    if (sign=='*') tmp = Integer.parseInt(str1)
                            * Integer.parseInt(str2);
                    else tmp = Integer.parseInt(str2)
                            / Integer.parseInt(str1);
                    stack.push(String.valueOf(tmp));
                }
                if (c != '*' && c != '/') {
                    stack.push(String.valueOf(c));
                    sign = c;
                } else {
                    //c是*或/, 把c后的数字拿到
                    sign = c;
                }
            }
        }
        if (sb.length() != 0) stack.push(sb.toString());
        if (sign == '*' || sign == '/') {
            //最后的符号是乘除, 需要特殊处理一下
            int tmp = 1;
            String str1 = stack.pop();
            String str2 = stack.pop();
            if (sign=='*') tmp = Integer.parseInt(str1)
                    * Integer.parseInt(str2);
            else tmp = Integer.parseInt(str2)
                    / Integer.parseInt(str1);
            stack.push(String.valueOf(tmp));
        }
        while (!stack.isEmpty()) {
            String token = stack.pop();//得出括号中各个字符串,可能是数字,可能是+,-号
            int operator = 1;
            if (!stack.isEmpty() && !stack.peek().equals("(")) {
                String op = stack.pop();
                operator = op.equals("+") ? 1 : -1;
            }
            rst += operator * Integer.parseInt(token);
        }
        return rst;
    }

    public static void main(String[] args) {
        System.out.println(calculate("5 / 2 + 3"));
//        System.out.println("012345678".substring(4,7));
    }
}
