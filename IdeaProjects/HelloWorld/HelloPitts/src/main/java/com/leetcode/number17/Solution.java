package com.leetcode.number17;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a digit string, return all possible letter combinations that the number could represent.
 * Digit to letters just like on the telephone buttons.
 * For example:
 * digits = "23"
 * output should be ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]
 *
 * Created by PeixinLu on 15/9/21.
 */
public class Solution {
    /**
     * BackTracking!
     */
    public static List<String> letterCombinations(String digits) {
        List<String> strs = new ArrayList<String>();
        if(digits == "" || digits.length() == 0) return strs; //base-condition

        char c = digits.charAt(0);
        List<String> l = findChar(c);

        List<String> rest = new ArrayList<String>();
        if(digits.length()>1) {
            rest = letterCombinations(digits.substring(1));
        }
        for(String str: l) {
            if(rest != null && rest.size()!=0) {
                for (String s : rest) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(str).append(s);
                    strs.add(sb.toString());
                }
            } else {//说明digits其实已经用完了
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                strs.add(sb.toString());
            }
        }
        return strs;
    }

    /**
     * 数字对应的字母列表
     * 0/1不考虑
     * @param c
     * @return
     */
    public static List<String> findChar(char c) {
        List<String> l = new ArrayList<String>();
        switch (c) {
            case '2':
                l.add("a");l.add("b");l.add("c");
                break;
            case '3':
                l.add("d");l.add("e");l.add("f");
                break;
            case '4':
                l.add("g");l.add("h");l.add("i");
                break;
            case '5':
                l.add("j");l.add("k");l.add("l");
                break;
            case '6':
                l.add("m");l.add("n");l.add("o");
                break;
            case '7':
                l.add("p");l.add("q");l.add("r");l.add("s");
                break;
            case '8':
                l.add("t");l.add("u");l.add("v");
                break;
            case '9':
                l.add("w");l.add("x");l.add("y");l.add("z");
                break;
            default: return l;
        }
        return l;
    }

    public static void main(String[] args) {
        System.out.println(letterCombinations(""));
    }
}
