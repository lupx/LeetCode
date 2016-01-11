package com.leetcode.number20;

import java.util.Stack;

/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 * Created by PeixinLu on 15/9/21.
 */
public class Solution {

    /**
     * 典型地, 用Stack解决
     * @param s
     * @return
     */
    public static boolean isValid(String s) {
//        Stack<Character> stack = new Stack<Character>();
        Stack stack = new Stack();
        if(s == "" || s.length() == 0) return true;
        if(s.length() % 2 != 0) return false; //可以节省一点时间
        Character a = s.charAt(0);
        if(a == ')' || a == '}' || a == ']') return false;

        for(int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);

            if(c == '(' || c == '{' || c == '[')
                stack.push(c);
            else {
                //出栈
                Character d = (Character)stack.pop();
                if(c == ')' && d == '(') continue;
                if(c == '}' && d == '{') continue;
                if(c == ']' && d == '[') continue;
                return false;
            }
        }
        if(stack.size()!=0) return false;
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isValid("([})"));
    }

}
