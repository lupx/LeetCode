package com.leetcode.number235.LowestCommonAncestorOfBST;

/**
 * 给一个BST, 和其中的两个节点, 要求找出其公共祖先.
 * 注意, 公共祖先完全有可能是某个节点自己, 比如一个父节点和一个子节点, 公共祖先就是父节点本身
 * Created by Peixin Lu on 15/12/31.
 */
public class Solution {

    /**
     * 充分利用已有BST性质, 若p,q都大于,或者都小于root.val, 那说明root肯定不是LCA, 递归到root两个孩子其中之一
     * 知道p/q分别落入root两边, 那么root就是LCA
     * 同时注意考虑特殊情况
     * beat 48.78%
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val > root.val && q.val > root.val) return lowestCommonAncestor(root.right, p, q);
        if (p.val < root.val && q.val < root.val) return lowestCommonAncestor(root.left, p, q);
        return root;
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