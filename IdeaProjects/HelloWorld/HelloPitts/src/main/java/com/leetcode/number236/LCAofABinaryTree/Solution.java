package com.leetcode.number236.LCAofABinaryTree;

import java.util.HashMap;
import java.util.Map;

/**
 * 给一个普通二叉树, 和其中的两个节点, 要求找出其公共祖先.
 * 注意, 公共祖先完全有可能是某个节点自己, 比如一个父节点和一个子节点, 公共祖先就是父节点本身
 * Created by Peixin Lu on 15/12/31.
 */
public class Solution {

    Map<TreeNode, Boolean> foundP = new HashMap<>();
    Map<TreeNode, Boolean> foundQ = new HashMap<>();
    /**
     * 情况略微复杂了. 无法利用大小关系来确定了.
     * 思路还是一样, 只要p和q同时存在于root一边, 那root肯定不是, 递归!
     * 如果分别存在于root两边, 那么root就是
     * beat 11.53%, 这里偷懒了, 用map作了一个缓存
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val == p.val || root.val == q.val) return root;
        boolean foundP = foundP(root.left, p);
        boolean foundQ = foundQ(root.left, q);
        if (foundP && foundQ) return lowestCommonAncestor(root.left, p, q);
        else if (!(foundP||foundQ)) {
            return lowestCommonAncestor(root.right, p, q);
        } else return root;
    }

    /**
     * 在root树里找p是否存在
     * @param root
     * @param node
     * @return
     */
    private boolean foundP(TreeNode root, TreeNode node) {
        if (root == null) return false;
        if (root == node) return true;
        if (foundP.containsKey(root)) return foundP.get(root);
        boolean rst = foundP(root.left, node) || foundP(root.right, node);
        foundP.put(root, rst);
        return rst;
    }

    /**
     * 在root树里找q是否存在
     * @param root
     * @param node
     * @return
     */
    private boolean foundQ(TreeNode root, TreeNode node) {
        if (root == null) return false;
        if (root == node) return true;
        if (foundQ.containsKey(root)) return foundQ.get(root);
        boolean rst = foundQ(root.left, node) || foundQ(root.right, node);
        foundQ.put(root, rst);
        return rst;
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