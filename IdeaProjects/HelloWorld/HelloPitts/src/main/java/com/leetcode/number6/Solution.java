package com.leetcode.number6;

/**
 * Created by PeixinLu on 15/9/12.
 */
public class Solution {
    /**
     * Convert the string 's' into zigzag string. see leetcode problem 6
     * @param s
     * @param numRows
     * @return
     */
    public static String convert(String s, int numRows) {
        if(numRows == 1 || numRows >= s.length()) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        int length = s.length();
        int r = length % (2 * numRows - 2); //  total remain number, indicate that how many rows need to add more element
        int c = length / (2 * numRows - 2); //  columns number
        int excol = 0;
        int remain = 0; // total remain - numRow = remain
        if(r >= numRows) {
            excol = 1;
            remain = r - numRows;
        } else {
            excol = 0;
            remain = r;
        }

        for(int i = 0; i < numRows; i++) {
            if (i == 0) {
                for(int j = 0; j < c + (r > 0 ? 1:0); j++) {
                    sb.append(s.charAt((2 * numRows - 2) * j));
                }
            } else if(i == numRows - 1){
                for(int j = 0; j < c + excol; j++) {
                    sb.append(s.charAt((2 * numRows - 2) * j + i));
                }
            } else {
                int j = 1;
                sb.append(s.charAt(i));
                while(j * (2 * numRows - 2) - i < length) {
                    sb.append(s.charAt(j * (2 * numRows - 2) - i));
                    if(j * (2 * numRows - 2) + i < length) {
                        sb.append(s.charAt(j * (2 * numRows - 2) + i));
                    }
                    j++;
                }
            }
        }
        return sb.toString();
    }

    /**
     * find the cycle, the cycle = numRow * 2 - 2
     * except the head line and the tail line, every 2 element forms a j, secondJ group.
     * j = i + cycle * k,  k=0,1,2,3,4,5....
     * secondJ = (j - i) + cycle - i
     * @param s
     * @param numRows
     * @return
     */
    public static String convert_2 (String s, int numRows) {
        if(numRows <= 1) return s;
        StringBuilder sb = new StringBuilder();
        //the size of a cycle(period)
        int cycle = 2 * numRows - 2;
        for(int i = 0; i < numRows; ++i)
        {
            for(int j = i; j < s.length(); j = j + cycle){ //j = j + cycle is wonderful!!!
                sb.append(s.charAt(j));
                int secondJ = (j - i) + cycle - i;
                if(i != 0 && i != numRows-1 && secondJ < s.length())
                    sb.append(s.charAt(secondJ));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        String s1 = "PAYPALISHIR";
//        System.out.println(convert_2(s, 4));
        System.out.println(Integer.MAX_VALUE);
    }
}
