package com.leetcode.number13;


/**
 * Given an roman numeral, convert it to a integer.
 * Input is guaranteed to be within the range from 1 to 3999.
 *
 * Created by Peixin Lu on 15/9/14.
 */
public class Solution {
    /**
     * @param s
     * @return
     */
    public static int romanToInt(String s) {
        int i = 0;
        int result = 0 ;
        while (i < s.length() && s.charAt(i) == 'M') {
            result = result + 1000;
            i++;
        }

        while(i < s.length() && (s.charAt(i)=='C'||s.charAt(i)=='D')) {//处理百位情况
            if(s.charAt(i) == 'C' && i<s.length()-1 && s.charAt(i + 1) == 'M') {
                result += 900;
                i += 2;
                break;
            }
            if(s.charAt(i) == 'C' && i<s.length()-1 && s.charAt(i+1) == 'D') {
                result += 400;
                i += 2;
                break;
            }
            if(s.charAt(i) == 'D') {
                result += 500;
                i++;
                continue;
            }
            //只能是C的情况了
            int count = 0;
            while(i<s.length() && s.charAt(i) == 'C') {
                count++;
                i++;
            }
            result += count * 100;
            break;
        }

        while(i < s.length() && (s.charAt(i)=='X'||s.charAt(i)=='L')) {//处理十位情况
            if(s.charAt(i) == 'X' && i<s.length()-1 && s.charAt(i + 1) == 'C') {
                result += 90;
                i += 2;
                break;
            }
            if(s.charAt(i) == 'X' && i<s.length()-1 && s.charAt(i+1) == 'L') {
                result += 40;
                i += 2;
                break;
            }
            if(s.charAt(i) == 'L') {
                result += 50;
                i++;
                continue;
            }
                //只能是X的情况了
                int count = 0;
                while(i<s.length() && s.charAt(i) == 'X') {
                    count++;
                    i++;
                }
                result += count * 10;
                break;
        }

        while(i < s.length() && (s.charAt(i)=='I'||s.charAt(i)=='V')) {//处理十位情况
            if(s.charAt(i) == 'I' && i<s.length()-1 && s.charAt(i + 1) == 'X') {
                result += 9;
                i += 2;
                break;
            }
            if(s.charAt(i) == 'I' && i<s.length()-1 && s.charAt(i+1) == 'V') {
                result += 4;
                i += 2;
                break;
            }
            if(s.charAt(i) == 'V') {
                result += 5;
                i++;
                continue;
            }
                //只能是I的情况了
                int count = 0;
                while(i<s.length() && s.charAt(i) == 'I') {
                    count++;
                    i++;
                }
                result += count;
                break;
        }
        return result;
    }

    public static void main(String[] args) {

//        System.out.println(romanToInt("MMMDCXLVIII"));
        String a = "C";
        System.out.println(a.startsWith("CM"));
    }
}
