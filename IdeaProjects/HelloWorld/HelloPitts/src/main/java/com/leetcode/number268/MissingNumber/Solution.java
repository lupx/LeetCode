package com.leetcode.number268.MissingNumber;

import java.util.LinkedList;
import java.util.List;

/**
 * 非负数字 to 英语单词:
 * Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.
 * For example,
 * 123 -> "One Hundred Twenty Three"
 * 12345 -> "Twelve Thousand Three Hundred Forty Five"
 * 1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 *
 * Created by Peixin Lu on 16/1/7.
 */
public class Solution {

    /**
     * beat 14.61%
     * @param num
     * @return
     */
    public static String numberToWords(int num) {
        if (num == 0) return "Zero";
        StringBuilder sb = new StringBuilder();
        List<String> list = new LinkedList<>();
        int con = 0;
        while (num != 0) {
            int remainder = num % 1000;
            num /= 1000;
            sb = new StringBuilder();
            if (remainder != 0) {
                sb.append(helper(remainder));
                if (con == 1) sb.append("Thousand ");
                if (con == 2) sb.append("Million ");
                if (con == 3) sb.append("Billion ");
            }
            list.add(0, sb.toString());
            con++;
        }
        sb = new StringBuilder();

        for (String str : list) {
            sb.append(str);
        }
        return sb.toString().trim();
    }

    /**
     * 这里专职将n变为一个词语, n<1000
     * n个位直接变, 没有例外
     * 十位和个位的组合有一个例外, 11和12. 其他情况, 十位和个位分开翻译即可
     * 百位也没有例外.
     * @param n
     * @return
     */
    private static String helper(int n) {
        StringBuilder sb = new StringBuilder();
        if (n <= 10) {
            switch (n) {
                case 0: sb.append("Zero ");break;
                case 1: sb.append("One ");break;
                case 2: sb.append("Two ");break;
                case 3: sb.append("Three ");break;
                case 4: sb.append("Four ");break;
                case 5: sb.append("Five ");break;
                case 6: sb.append("Six ");break;
                case 7: sb.append("Seven ");break;
                case 8: sb.append("Eight ");break;
                case 9: sb.append("Nine ");break;
                case 10: sb.append("Ten ");break;
            }
        } else if (n / 100 == 0) {
            //说明只有2位, 特殊情况是十几的写法
            if (n / 10 == 1) {
                switch (n) {
                    case 11: sb.append("Eleven ");break;
                    case 12: sb.append("Twelve ");break;
                    case 13: sb.append("Thirteen ");break;
                    case 14: sb.append("Fourteen ");break;
                    case 15: sb.append("Fifteen ");break;
                    case 16: sb.append("Sixteen ");break;
                    case 17: sb.append("Seventeen ");break;
                    case 18: sb.append("Eighteen ");break;
                    case 19: sb.append("Nineteen ");break;
                }
            } else {
                int shi = n / 10;
                switch (shi) {
                    case 2: sb.append("Twenty ");break;
                    case 3: sb.append("Thirty ");break;
                    case 4: sb.append("Forty ");break;
                    case 5: sb.append("Fifty ");break;
                    case 6: sb.append("Sixty ");break;
                    case 7: sb.append("Seventy ");break;
                    case 8: sb.append("Eighty ");break;
                    case 9: sb.append("Ninety ");break;
                }
                int ge = n % 10;
                if (ge != 0)
                    sb.append(helper(ge));
            }
        } else {//说明有百位
            int bai = n / 100;
            int remainder = n % 100;
            sb.append(helper(bai)).append("Hundred ");
            if (remainder != 0) sb.append(helper(remainder));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(numberToWords(10120));
    }
}