package com.leetcode.number105;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Peixin Lu on 15/10/24.
 */
public class Solution {

    /**
     * 从前序 + 中序重建Tree
     * 递归:
     * 前序第一个字符是整个树的根, 然后在中序中找到这个数字, 然后左右两边递归重构, 左右指针指向两边即可.
     * 此解法TLE! 但是discuss里基本也就这个解法了!
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null
                || preorder.length == 0 || inorder.length == 0) {
            return null;
        }
        return buildTreeHelper(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    public TreeNode buildTreeHelper(int[] preorder, int startpre, int endpre,
                                    int[] inorder, int startin, int endin) {
        if (startpre >= preorder.length) return null;
        if (endin >= inorder.length) return null;
        if (startpre == endpre) {
            return new TreeNode(preorder[startpre]);
        }
        if (startin == endin) {
            return new TreeNode(preorder[startin]);
        }

        int curR = preorder[startpre];
        TreeNode curRoot = new TreeNode(curR);

        //find curR in inorder
        int i = startin;
        for (; i <= endin; i++) {
            if (inorder[i] == curR) {
                break;
            }
        }
        curRoot.left = buildTreeHelper(preorder, startpre + 1, endpre, inorder, startin, i - 1);
        curRoot.right = buildTreeHelper(preorder, startpre + i + 1, endpre, inorder, i + 1, endin);
        return curRoot;
    }

    public static void main(String[] args) {
    }
}

