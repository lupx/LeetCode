package com.leetcode.number106;

/**
 *
 * Created by Peixin Lu on 15/10/24.
 */
public class Solution {

    /**
     * 从中序 + 后序重建Tree
     * 还是递归:
     * 后序从后往前看, 最后一个数字就是当前树的根, 在中序里找到, 然后当前根左右指向根数字左右序列对应的递归树
     * 202testcase, 2ms
     * @param postorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null
                || inorder.length == 0 || postorder.length == 0) {
            return null;
        }
        return buildTreeHelper(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    public TreeNode buildTreeHelper(int[] inorder, int instart, int inend,
                                    int[] postorder, int poststart, int postend) {
        if (postend < poststart || inend < instart) {
            return null;
        }
        if (postend == poststart) {
            return new TreeNode(postorder[postend]);
        }
        if (instart == inend) {
            return new TreeNode(inorder[instart]);
        }

        int curR = postorder[postend];
        TreeNode curRoot = new TreeNode(curR);
        int i = inend;
        for (; i >= 0 ;i--) {
            if (inorder[i] == curR) break;
        }

        curRoot.right = buildTreeHelper(inorder, i + 1, inend, postorder, poststart, postend - 1);
        curRoot.left = buildTreeHelper(inorder, instart, i - 1, postorder, poststart, postend - (inend - i + 1));
        return curRoot;
    }

    public static void main(String[] args) {
    }
}

