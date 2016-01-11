package com.leetcode.number4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by PeixinLu on 15/9/1.
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 */
public class Solution {
    /**
     * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
     * However, this one is O(m+n), which is much slower!
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //1st, we merge them
        int len = nums1.length + nums2.length;
        int[] nums = new int[len];
//        Integer[] nums_1 = new Integer[nums1.length];
//        Integer[] nums_2 = new Integer[nums2.length];
//        int i = 0, j=0;
//        if(nums1[i]>nums1[nums1.length/2] && nums1[0]>nums1[nums.length-1]){
//            List<Integer> l1 = new ArrayList<Integer>();
//            for(;i<nums1.length;i++){
//                l1.add(nums1[i]);
//            }
//            Collections.sort(l1);
//            nums_1 = (Integer[])l1.toArray();
//        }
//        if(nums2[i]>nums2[nums2.length/2] && nums2[0]>nums2[nums.length-1]&&i==0) {
//            List<Integer> l2 = new ArrayList<Integer>();
//            for(;i<nums2.length;i++){
//                l2.add(nums1[i]);
//            }
//            Collections.sort(l2);
//            nums_2 = (Integer[])l2.toArray();
//        }
        int p1 = 0;
        int p2 = 0;
        int i = 0;
        while(p1<nums1.length&&p2<nums2.length) {
            if(nums1[p1]<=nums2[p2]){
                nums[i] = nums1[p1];
                p1++;
            } else {
                nums[i] = nums2[p2];
                p2++;
            }
            i++;
        }
        while(p1<nums1.length){
            nums[i] = nums1[p1];
            p1++;
        }
        while(p2<nums2.length){
            nums[i] = nums2[p2];
            p2++;
        }

        //2nd, let's find the median in the nums[]!
        int a, b;
        double x;
        if((len%1)==0){
            //even
            a = nums[len/2-1];
            b = nums[len/2];
        } else {
            //odd
            a = nums[len/2];
            x = a/1;
            return x;
        }
        x = (double)(a+b)/2;
        return x;
    }

    /**
     * By using binary search in each array, we can narrow down the range in which we search next time.
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays_Log(int[] nums1, int[] nums2) {
        int lengthA = nums1.length;
        int lengthB = nums2.length;
        int len = lengthA + lengthB;
        if(len % 2 == 0) {
            return (findKth(nums1, nums2, 0, 0, len/2) + findKth(nums1, nums2, 0, 0, len/2+1))/ 2.0;
        } else {
            return findKth(nums1, nums2, 0, 0, len/2 + 1);
        }
    }


    public static double findKth(int[] nums1, int[]nums2, int starta, int startb, int k){
        int len1 = nums1.length;
        int len2 = nums2.length;

        if(starta >= len1){
            //If the start point is beyond the length of this array, then this array will be eliminated at once.
            //Because, starta moves when we 'cut' elements off array nums1. When the starta is larger than the len1.
            //That means it is not necessary need nums1 any more.
            //Same with the nums2.
            return nums2[startb + k - 1];//index starts from 0
        }
        if(startb >= len2){
            return nums1[starta + k - 1];//index starts from 0
        }
        if(k == 1){
            //This block means:
            //When we need to find the 1th min element in two Array
            //We just return the first element which is the element pointed by the 'start index' in each Array and compare them.
            //The reason for we picking the smaller one is when the two arrays merged, the smaller one will stand in front of the bigger one;
            //The smaller one will be the Kth, and the bigger one actually become the (k+1)th element in the merge Array.
            return Math.min(nums1[starta], nums2[startb]);

        }

        int mid = k/2 - 1;//index starts from 0, so the mid of K is K/2 - 1
        int keypoint1 = starta + mid >= len1? Integer.MAX_VALUE : nums1[starta + mid];//keypoint1 is the k/2 one of nums1
        int keypoint2 = startb + mid >= len2? Integer.MAX_VALUE : nums2[startb + mid];//keypoint2 is the k/2 one of nums2

        if(keypoint1 > keypoint2){
            //When we cut off some elements from one array, the 'start' index moves forward by [start + k/2]
            //k-k2 means that we have eliminated K/2 elements, so k-k/2 elements left
            return findKth(nums1, nums2, starta, startb + k/2, k - k/2);
        } else {
            return findKth(nums1, nums2, starta + k/2, startb, k - k/2);
        }
    }

    public static void main(String[] args){
        int[] a = {1,2};
        int[] b = {1,2,4,5,6,7,8,8,9};
        System.out.println(findMedianSortedArrays_Log(a, b));
    }
}
