package com.leetcode.number75;


/**
 * Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent,
 * with the colors in the order red, white and blue.
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 * Note:
 * You are not suppose to use the library's sort function for this problem.
 *
 * Created by PeixinLu on 15/10/16.
 */
public class Solution {

    /**
     * 颜色排序, 0代表红, 1代表白, 2代表蓝
     * 排序后, 相同颜色应该在一起, 同时满足: 红色/白色/蓝色的 总序
     * 不能使用库函数!
     *
     * 说白了,排序后, 0在最左边, 2在最右边, 1在中间
     * 用两个指针做:
     * (1)首先,把所有红色放到中间位置.
     *    给两个指针i和j分别指向头尾, 然后分别往中间移动
     *    如果i位置==0, i++, 直到遇到i位置不是0,而j位置是0的时候就交换. 然后i++,j--
     *    如果i位置!=0, 那么j--, 直到遇到0, 交换两者
     *
     * (2)找到第一个白色的位置, 如果小于nums长度, 然后, i = 该位置+1, j=结尾
     * 继续上面的过程:
     *    左边小,右边大, 直接i++,j--. 直到i = j, 此时说明左1右2, 顺序符合要求, 程序结束.
     *    左边为1, 右边为1, i++直到j, 如果最终i==j, 说明i到j全是1, 符合顺序要求,程序结束
     *    左边为2, 右边为2, j--直到i, 如果最终j==i, 说明i到j全是2, 符合顺序要求,程序结束
     * @param nums
     */
    public static void sortColors(int[] nums) {
        if (nums == null || nums.length == 1) return;
        int i = 0;
        int j = nums.length - 1;
        while (i < j) {
            while (j > i && nums[i] != 0 && nums[j] != 0) {
                j--;
            }
            if (j == i) break;//剩余无0,直接break
            while (i < j && nums[i] == 0) {
                i++;
            }
            if (i == j) break;//i->j全是0
            if (nums[i] != 0
                    && nums[j] == 0) {
                swap(nums, i, j);
                i++;
                j--;
            }
        }

        int k = 0;
        while (k < nums.length && nums[k] == 0) {
            k++;
        }
        i = k;
        j = nums.length - 1;
        while (i < j) {
            while (nums[i] < nums[j]
                    && i < j) {
                i++;
                j--;
            }
            if (i >= j) break;//顺序已排好
            while (nums[i] == 1
                    && nums[j] == nums[i]
                    && i < j) {
                i++;
            }
            if (i == j) break;//i到j全部是1
            while (nums[i] == 2
                    && nums[j] == nums[i]
                    && j > i) {
                j--;
            }
            if (j == i) break;//i到j全部是2
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[j];
        nums[j] = nums[i];
        nums[i] = tmp;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{2,2,2,0};
//        int[] nums = new int[]{0,2,2,2,0,2,1,1};
        sortColors(nums);

    }
}

