package com.leetcode.number230.KthSmallestInBST;

import java.util.*;

/**
 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ BST's total elements. 可以假定k永远合法, 也即k肯定在BST元素个数范围内!

 * Follow up:
 * What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently?
    How would you optimize the kthSmallest routine?
 *
 * Created by Peixin Lu on 15/12/31.
 */
public class Solution {
    Map<TreeNode, Integer> leftNums;
    /**
     * 二叉搜索树上找第K小元素.
     * 充分利用BST性质, 用二分法来做
     * BST性质, 每个节点, 其左子树上所有元素一定小于自己, 右子树上所有元素一定大于自己.
     *
     * 思路有点像两个list找中位数那个题!
     * 由于每次都算了左子树节点个数, 所以速度非常慢, 考虑用一个map把每个节点左子树节点数存起来
     *
     * 然后, 用map优化意义不大, 因为如果遇到恶劣情况, 每次都是去右子树找, 那相当于还是每次重新计算....
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        leftNums = new HashMap<>();
        while (root.left != null || root.right != null) {
            System.out.println("当前root：" + root.val + ", 找kth:" + k);
            int leftCount = countNodes(root.left);
            if (leftCount + 1 == k) return root.val;
            if (leftCount >= k) {
                //说明在左子树
                root = root.left;
            } else {
                //说明kth 在root的右子树里
                root = root.right;
                k = k - leftCount - 1; // -1是减的root
            }
        }
        return root.val;
    }

    /**
     * 计算root为根的树的总节点数
     * @param root
     * @return
     */
    private int countNodes(TreeNode root) {
        if (root == null) return 0;
        if (leftNums.containsKey(root)) return leftNums.get(root);
        else {
            int count = 1 + countNodes(root.left) + countNodes(root.right);
            leftNums.put(root, count);
            return count;
        }
    }

    public static void main(String[] args) {
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
