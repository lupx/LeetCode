package com.leetcode.number7;

import java.util.InputMismatchException;

/**
 * Reverse Integer, for example, input is 321, returns 123. input is -321, returns -123
 *
 * Created by PeixinLu on 15/9/13.
 */
public class Solution {
    /**
     * Need to consider about overflow, for instance, 1000000003 will get a overflow if reverse it.
     * And, 1000, 100, these numbers if reversed, will only get 1
     * @param x
     * @return
     */
    public static int reverse(int x) {
        if(x == 0){
            return 0;
        }
        int length = 0;
        int tmp = x;
        while(tmp != 0) {
            tmp /= 10;
            length++;
        }
        if(length>10) { return 0; }
        int[] arr = new int[length];
        tmp = x;
        int i = 0;
        while(tmp != 0) {
            arr[i++] = tmp % 10;
            tmp /= 10;
        }

        int sum = 0;
        i = 0;
        while(i < length) {
            if(length < 10) {
                sum = sum * 10 + arr[i++];
            } else if(length == 10){
                if (sum > Integer.MAX_VALUE/10 || sum < Integer.MIN_VALUE/10) {
                    return 0;
                } else if(sum == Integer.MAX_VALUE/10 || sum == Integer.MIN_VALUE/10) {
                    if ( x > 0 && arr[i] > 7){
                        return 0;
                    } else if(x < 0 && arr[i] < -8) {
                        return 0;
                    }
                }
                sum = sum * 10 + arr[i++];
            }
        }
        return sum;
    }

    public static int reverse_2(int x) {
        if(x == 0) return 0;
        int tmp;
        if(x < 0) {tmp = x * -1;}
        else tmp = x;
        String xx = String.valueOf(tmp);
        char[] arr = xx.toCharArray();
        if(arr.length>10) {return 0;}

        if(arr.length % 2 == 0) {
            int mid1 = arr.length / 2 - 1;
            int mid2 = arr.length / 2;
            char tmpc;
            for(int i = 0, j = arr.length-1; i <= mid1 && j >= mid2; i++, j--) {
                tmpc = arr[i];
                arr[i] = arr[j];
                arr[j] = tmpc;
            }
        } else {
            int mid = arr.length / 2;
            char tmpc;
            for(int i = 0, j = arr.length-1; i < mid && j > mid; i++, j--) {
                tmpc = arr[i];
                arr[i] = arr[j];
                arr[j] = tmpc;
            }
        }

        if(arr.length == 10) {
            //dealing with overflow
            char[] arr_10 = new char[9];
            for(int i = 0;i<9; i++){
                arr_10[i] = arr[i];
            }
            String str = new String(arr_10);
            Integer num = Integer.parseInt(str);
            if(num > Integer.MAX_VALUE / 10) {
                if(x < 0) {
                    if(arr[9] > 8) {
                        return 0;
                    }
                } else {
                    if(arr[9] > 7) {
                        return 0;
                    }
                }
            }
        }

        xx = new String(arr);
        Integer result = Integer.parseInt(xx);

        if(x < 0) return result * -1;
        return result;
    }


    public static int reverse_3(int x) {
        long result = 0;
        while (x != 0) {
            result = result * 10 + x % 10;
            x /= 10;
        }
        return (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE)? 0: (int) result;
    }

    public static void main(String[] args) {
//        System.out.println(reverse(-321));
        System.out.println(reverse_3(2123847222));
//        String x = String.valueOf(-3123 * -1);
//        System.out.println(x);
//        char[] arr = x.toCharArray();
//        System.out.println(arr.length);
    }
}
