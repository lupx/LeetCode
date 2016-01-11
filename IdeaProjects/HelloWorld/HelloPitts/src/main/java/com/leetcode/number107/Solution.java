package com.leetcode.number107;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 *
 * Created by Peixin Lu on 15/10/24.
 */
public class Solution {

    /**
     * 4ms
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> rst = new LinkedList<>();
        if (root == null) return rst;
        List<Integer> levelNumber = new ArrayList<>();
        List<TreeNode> levelQ = new ArrayList<>();//层队列
        levelQ.add(root);
        while(levelQ.size() > 0) {
            int i = 0;
            int len = levelQ.size();
            while (i < len) {
                TreeNode thisNode = levelQ.remove(i);
                levelNumber.add(thisNode.val);
                if (thisNode.left != null) levelQ.add(thisNode.left);
                if (thisNode.right != null) levelQ.add(thisNode.right);
                i++;
            }
            rst.add(0, new ArrayList<>(levelNumber));
            levelNumber.clear();
        }
        return rst;
    }

    public static void main(String[] args) {
    }
}

