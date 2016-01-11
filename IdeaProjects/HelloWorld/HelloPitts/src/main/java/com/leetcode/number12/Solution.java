package com.leetcode.number12;


/**
 * Given an integer, convert it to a roman numeral.
 * Input is guaranteed to be within the range from 1 to 3999.
 *
 * Created by Peixin Lu on 15/9/14.
 */
public class Solution {

    public static String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        int digits = 0;//位数

        //最简单的算位数的办法,因为题目说了不会大于3999.所以最多就是千位.
        if(num >= 1000) {
            digits = 4;
        } else if(num >= 100){
            digits = 3;
        } else if(num >= 10) {
            digits = 2;
        } else {
            digits = 1;
        }

        while(digits > 0) {
            if(digits == 4) {//千位转换
                int i = num / 1000;
                while(i > 0) {
                    sb.append("M");
                    i--;
                }
                num %= 1000;
            }
            if(digits == 3) {//百位转换
                int i = num / 100; //取得百位数字
                if(i == 9) {
                    sb.append("CM"); //900
                } else if(i>=5){
                    sb.append("D"); //500
                    i -= 5;
                    while(i > 0) {
                        sb.append("C");//还剩几个百,就直接加几个C
                        i--;
                    }
                } else if(i == 4) {
                    sb.append("CD"); //400
                } else {
                    while(i > 0) {
                        sb.append("C");//还剩几个百,就直接加几个C
                        i--;
                    }
                }
                num %= 100;
            }
            if(digits == 2) {//十位转换
                int i = num / 10;//取得十位数字
                if(i == 9) {
                    sb.append("XC"); //90
                } else if(i>=5){
                    sb.append("L"); //500
                    i -= 5;
                    while(i > 0) {
                        sb.append("X");//还剩几个十,就直接加几个X
                        i--;
                    }
                } else if(i == 4) {
                    sb.append("XL");
                } else {
                    while(i > 0) {
                        sb.append("X");//有几个十,就直接加几个X
                        i--;
                    }
                }
                num %= 10;
            }
            if(digits == 1) {//个位转换
                int i = num;//个位
                if(i == 9) {
                    sb.append("IX"); //90
                } else if(i>=5){
                    sb.append("V"); //500
                    i -= 5;
                    while(i > 0) {
                        sb.append("I");//还剩几,就直接加几个I
                        i--;
                    }
                } else if(i==4) {
                    sb.append("IV");
                } else {
                    while(i > 0) {
                        sb.append("I");//还剩几,就直接加几个I
                        i--;
                    }
                }
            }
            digits--;
        }

        return sb.toString();
    }

    public static void main(String[] args) {

        System.out.println(intToRoman(649));
    }
}
