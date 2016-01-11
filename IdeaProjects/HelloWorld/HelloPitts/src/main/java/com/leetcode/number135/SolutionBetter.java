package com.leetcode.number135;

import java.util.Arrays;
import java.util.Stack;

/**
 * There are N children standing in a line. Each child is assigned a rating value.
 * You are giving candies to these children subjected to the following requirements:
 * (1) Each child must have at least one candy.
 * (2) Children with a higher rating get more candies than their neighbors.
 * What is the minimum candies you must give?
 *
 * Created by Peixin Lu on 15/11/6.
 */
public class SolutionBetter {

    public static int candy(int[] ratings) {

        int candies[] = new int[ratings.length];
        Arrays.fill(candies, 1);// Give each child 1 candy

        // Scan from left to right, to make sure right higher rated child gets 1 more candy than left lower rated child
        for (int i = 1; i < candies.length; i++){
            if (ratings[i] > ratings[i - 1]) candies[i] = (candies[i - 1] + 1);
        }

        // Scan from right to left, to make sure left higher rated child gets 1 more candy than right lower rated child
        for (int i = candies.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) candies[i] = Math.max(candies[i], (candies[i + 1] + 1));
        }

        int sum = 0;
        for (int candy : candies)
            sum += candy;
        return sum;
    }


    public static void main(String[] args) {
        System.out.println(candy(new int[]{
                58,21,72,77,48,9,38,71,68, 77,
                82,47,25,94,89,54,26,54,54,99,
                64,71, 76,63,81,82,60,64,29,51,
                87,87,72,12,16,20,21,54,43,41,
                83,77,41,61, 72,82,15,50,36,69,
                49,53,92,77,16,73,12,28,37,41,
                79,25,80,3,37,48, 23,10,55,19,
                51,38,96,92,99, 68,75,14,18,63,
                35,19,68,28,49,36,53,61, 64,91,
                2,43,68,34,46,57,82,22,67,89
        }));
//        System.out.println(candy(new int[]{
//                1,5,5,3,2,1,8
//        }));
    }
}

