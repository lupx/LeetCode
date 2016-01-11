package com.leetcode.number43;

/**
 * Given two numbers represented as strings, return multiplication of the numbers as a string.
 * Note: The numbers can be arbitrarily large and are non-negative.
 * Created by Peixin Lu on 15/10/5.
 */
public class Solution {

    /**
     * DP解法
     * num1中从尾到头各位乘以num2从尾到头各位.
     * 可以把products看作是最后的结果的数组
     * products[]各位实际保存了最后结果的各位.
     * products[len1 + len2 - 1]保存结果的个位, 以此类推.
     * @param num1
     * @param num2
     * @return
     */
    public static String multiply(String num1, String num2) {
        int len1 = num1.length();
        int len2 = num2.length();
        int[] products = new int[len1 + len2];
        for (int i = len1 - 1; i >= 0; i--) {
            for (int j = len2 - 1; j >= 0; j--) {
                //各位相乘, 放入products相应的位置
                int n1 = num1.charAt(i) - '0';
                int n2 = num2.charAt(j) - '0';
                products[i + j + 1] += n1 * n2;
            }
        }

        //各位累加, 有进位考虑进位.
        int carry = 0;
        for (int i = products.length - 1; i >= 0; i--) {
            int tmp = products[i];
            products[i] = (tmp + carry) % 10;
            carry = products[i] / 10;
        }

        //从头到尾append进结果String
        StringBuilder rs = new StringBuilder();
        for (int i = 0; i < products.length - 1; i++) {
            rs.append(products[i]);
        }
        while (rs.length() != 0 && rs.charAt(0) == '0') {
            rs.deleteCharAt(0);//去掉所有开头的0
        }

        return rs.toString().length() == 0? "0" : rs.toString();
    }

    public static void main(String[] args) {

        System.out.println(multiply("518239691153","1261236165919"));
    }
}
