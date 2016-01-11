package com.leetcode.number8;

import java.math.BigDecimal;

/**
 * My atoi
 * Given a String, convert it to Integer
 * For example. give "31232",  the Integer will be 31232
 * And other possibilities need to be consider about:
 * (1) if the string is "   " or "" or null,  should return 0;
 * (2) str = "3124123fah1;kjf;lasdf", should return 3124123
 * (3) str = "      fafa123fnaf21j", should return 0;
 * (4) str = "     -312341", should return -312341
 * (5) str = "ajklfla;hf;f", should return 0
 * (6) str = "    +0  12323", should return 12323
 * (7) str = "   - 321", should return 0
 * (7) str = "9127391264908261836180730891036801236", should return Integer.MAX_VALUE
 * (8) OVERFLOW!!! if overflowed, return Integer.MAX_VALUE or Integer.MIN_VALUE
 *
 * Created by PeixinLu on 15/9/13.
 */
public class Solution {
    public static int myAtoi(String str) {
        if(str == null || str.equals("")) {
            return 0;
        }
        char tmp;
        boolean flag = false;
        boolean signflag = false;
        long result = 0L;
        char sign = ' ';
        int count = 0;
        for(int i = 0; i < str.length(); i++){
            tmp = str.charAt(i);
            if(tmp == ' ' && flag) break;
            if(tmp == ' ' && !flag) {
                if(signflag){
                    return 0;
                } else {
                    continue;
                }
            }
            if(!Character.isDigit(tmp) && flag) {
                break;
            }
            if(!Character.isDigit(tmp) && !flag) {
                if((tmp == '-' || tmp == '+') && !signflag) {
                    sign = tmp;
                    signflag = true;
                } else {
                    return 0;
                }
            }
            if(Character.isDigit(tmp)) {
                count++;
                flag = true;
                result = result * 10 + (tmp - '0');
                if(count >= 10){
                    if(signflag && sign == '-') {
                        if(result * -1 < Integer.MIN_VALUE) return Integer.MIN_VALUE;
                    }
                    if(signflag && sign == '+') {
                        if(result > Integer.MAX_VALUE) return Integer.MAX_VALUE;
                    }
                    if(!signflag && result > Integer.MAX_VALUE) {return Integer.MAX_VALUE ;}
                }
            }
        }
        if(result == 0) {return 0;}
        if(signflag && sign == '-') {
            return (int)result * -1;
        }
        if(signflag && sign == '+') {
            return (int)result;
        }
        return (int)result;
    }


    public static int myAtoi_revised(String str) {
        if(str == null || str.equals("")) {
            return 0;
        }
        str = str.trim();
        char tmp;
        boolean signflag = false;//是否之前已经有正负号的标志位
        long result = 0L;//用long做累加器,可以防止溢出
        char sign = ' ';//记录正负号
        int count = 0;//位数计数器,用来做溢出可能的判断
        if(str.charAt(0) == '+' || str.charAt(0) == '-') {
            sign = str.charAt(0);
            signflag = true;
        } else if(!Character.isDigit(str.charAt(0))) {
            return 0;
        } else { //第一位是数字
            result = str.charAt(0) - '0';
        }
        for(int i = 1; i < str.length(); i++){
            tmp = str.charAt(i);

            //情况1, 遇到非数字的字符,因为之前第一位检查过是否为正负号, 所以,这里如果不是数字,就要看第一位是什么
            if(!Character.isDigit(tmp)) {
                if(i==1) {
                    //第一位后紧跟着非数字字符
                    if(Character.isDigit(str.charAt(0))){//如果第一位是数字,那就break.
                        break;
                    }
                    return 0;
                } else {
                    //不是紧跟着出现,说明前面已经有数字
                    break;
                }
            } else {//遇到数字
                count++;
                result = result * 10 + (tmp - '0');
                if(count >= 9){//数字位数达到9位,需要考虑溢出问题
                    if(signflag && sign == '-') { //有符号,且为负
                        if(result * -1 < Integer.MIN_VALUE) return Integer.MIN_VALUE;
                    }
                    if(signflag && sign == '+') { //有符号,且为正
                        if(result > Integer.MAX_VALUE) return Integer.MAX_VALUE;
                    }
                    //这个可能性不能漏了,没有符号,按照正的对待
                    if(!signflag && result > Integer.MAX_VALUE) {return Integer.MAX_VALUE ;}
                }
            }
        }
        //程序走到这里,说明没有溢出,我们也累加得到了数字result, 但是result是无符号的,所以需要考虑正负号
        if(signflag && sign == '-') { //如果有符号且为负,转换符号输出
            return (int)result * -1;
        }
        if(signflag && sign == '+') { //如果有符号且为正,直接输出
            return (int)result;
        }
        return (int)result; //这种情况是捡漏的,如果无符号,走到这里,直接输出
    }


    public static void main(String[] args) {
        System.out.println(myAtoi_revised("2147483648"));
    }
}
