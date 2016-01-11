package com.leetcode.number165;

/**
 * Compare two version numbers version1 and version2.
 * If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.
 * You may assume that the version strings are non-empty and contain only digits and the . character.
 * The . character does not represent a decimal point and is used to separate number sequences.
 * For instance, 2.5 is not "two and a half" or "half way to version three",
 *    it is the fifth second-level revision of the second first-level revision.
 * Here is an example of version numbers ordering:
 * 0.1 < 1.1 < 1.2 < 13.37
 * Created by Peixin Lu on 15/11/23.
 */
public class Solution {

    /**
     *
     * @param version1
     * @param version2
     * @return
     */
    public static int compareVersion(String version1, String version2) {
        Integer v1 = 0;
        Integer v2 = 0;
        String[] arr1 = version1.split("\\.");
        String[] arr2 = version2.split("\\.");
        int i = 0;
        int j = 0;
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i].equals("") && !arr2[j].equals("")) {
                v2 = Integer.parseInt(arr2[j]);
                if (v2.equals("0")) {
                    j++;
                } else {
                    return -1;
                }
            }
            if (arr2[j].equals("") && !arr1[i].equals("")) {
                v1 = Integer.parseInt(arr1[i]);
                if (v1.equals(0)) {
                    i++;
                } else {
                    return 1;
                }
            }
            if (arr1[i].equals("") && arr2[j].equals("")) {
                return 0;
            }

            v1 = Integer.parseInt(arr1[i]);
            v2 = Integer.parseInt(arr2[j]);
            if (v1.compareTo(v2) < 0) return -1;
            if (v1.compareTo(v2) > 0) return 1;
            if (v1.compareTo(v2) == 0) {
                i++;
                j++;
            }
        }
        if (i == arr1.length) {
            while (j < arr2.length) {
                if (arr2[j].equals("")) return 0;
                else {
                    v2 = Integer.parseInt(arr2[j]);
                    if (!v2.equals(0)) return -1;
                    j++;
                }
            }
        }
        if (j == arr2.length) {
            while (i < arr1.length) {
                if (arr1[i].equals("")) return 0;
                else {
                    v1 = Integer.parseInt(arr1[i]);
                    if (!v1.equals(0)) return 1;
                    i++;
                }
            }
        }

        return 0;
    }

    public static void main(String[] args) {
//        int v1 = Integer.parseInt("0");
//        int v2 = Integer.parseInt("0000.1");
        System.out.println(compareVersion("1.0.","1.0.0.000"));
//        System.out.println(v1 == v2);
//        System.out.println("11.2".split("\\.").length);
//        System.out.println("00001".hashCode());
//        System.out.println(Integer.parseInt("00100"));
    }

}

