package com.leetcode.number66;

/**
 * Given a non-negative number represented as an array of digits, plus one to the number.
 * Created by Peixin Lu on 15/10/14.
 */
public class Solution {

    /**
     * 加1
     * digits是一个数组,代表了一个数字,
     * 比如[1,2,3],代表了数字123
     * 要求输出这个数字+1的结果
     *
     * 从最后一位往前, 不断考虑进位
     * 直到无进位, 循环退出, 返回数组即可
     * @param digits
     * @return
     */
    public static int[] plusOne(int[] digits) {
        if (digits == null) return null;
        if (digits.length == 0) return new int[]{1};

        int n = digits.length;
        int[] result = new int[n];
        int last = digits[n - 1] + 1;
        int carry = last / 10;
        result[n - 1] = last % 10;

        int i = n - 2;
        while (i >= 0) {
            result[i] = digits[i] + carry;
            carry = result[i] / 10;
            result[i] = result[i] % 10;
            i--;
        }

        if (carry != 0) {
            //说明还需要多N位
            String extra = String.valueOf(carry);
            int extraN = extra.length();
            int[] newResult = new int[n + extraN];
            for (int j = 0; j < extraN; j++) {
                newResult[j] = Integer.parseInt(extra.substring(j, j + 1));
            }
            for (int j = 0; j < result.length; j++) {
                newResult[j + extraN] = result[j];
            }
            return newResult;
        }
        return result;
    }

    public static void main (String[] args) {
        int[] a = new int[]{9,6,9,8,9,9};
        int[] result = plusOne(a);
        StringBuilder sb = new StringBuilder();
        for (int i : result) {
            sb.append(i).append(",");
        }
        System.out.println(sb.toString());
//        System.out.println(Integer.MAX_VALUE);

    }

}
