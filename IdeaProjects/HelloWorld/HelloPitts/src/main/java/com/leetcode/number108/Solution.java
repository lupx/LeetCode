package com.leetcode.number108;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 * Created by Peixin Lu on 15/10/24.
 */
public class Solution {

    /**
     * 构造平衡二叉查找树
     * 递归, 每次找中点作为根,然后左右作为子树
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        return helper(nums, 0, nums.length - 1);
    }

    private TreeNode helper(int[] nums, int start, int end) {
        if (start == end) return new TreeNode(nums[start]);
        if (end < 0) return null;
        if (start > end) return null;
        int mid = start + (end - start) / 2;
        TreeNode curRoot = new TreeNode(nums[mid]);
        curRoot.left = helper(nums, start, mid - 1);
        curRoot.right = helper(nums, mid + 1, end);
        return curRoot;
    }

    public static void main(String[] args) {
    }
}

