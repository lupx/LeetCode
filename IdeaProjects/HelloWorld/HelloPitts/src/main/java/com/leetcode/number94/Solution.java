package com.leetcode.number94;

import java.util.ArrayList;
import java.util.List;

/**
 * 树的中序遍历
 * Created by Peixin Lu on 15/10/22.
 */
public class Solution {

    /**
     * 先输出左子树, 再当前val,再右子树
     * @param root
     * @return
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        traversalHelper(root, result);
        return result;
    }

    private static void traversalHelper(TreeNode root, List<Integer> result) {
        if (root == null) return;
        traversalHelper(root.left, result);
        result.add(root.val);
        traversalHelper(root.right, result);
    }

    public static void main(String[] args) {
        List<Integer> list = inorderTraversal(new TreeNode(1));
        for (Integer s : list) {
            System.out.println(s);
        }
    }
}

