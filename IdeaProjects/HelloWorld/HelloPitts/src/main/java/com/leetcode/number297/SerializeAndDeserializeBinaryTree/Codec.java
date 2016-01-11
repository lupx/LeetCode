package com.leetcode.number297.SerializeAndDeserializeBinaryTree;

import java.util.LinkedList;
import java.util.List;

/**
 * Serialization is the process of converting a data structure or object into a sequence of bits so that
 *    it can be stored in a file or memory buffer,
 *    or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary tree.
 * There is no restriction on how your serialization/deserialization algorithm should work.
 * You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
 * For example, you may serialize the following tree
 *     1
 *    / \
 *   2   3
 *  / \
 * 4   5
 * as "[1,2,3,null,null,4,5]"
 *
 * 不要求一定和题目一样的串, 能序列化和反序列化即可!
 *
 * Created by PeixinLu on 16/1/10.
 */
public class Codec {

    /**
     * Encodes a tree to a single string.
     * 用层序遍历去做, 最简单的序列化方法还是leetcode官方的办法.
     * beat 51.55%
     * @param root
     * @return
     */
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if (root == null) return sb.toString();
        List<TreeNode> levelQ = new LinkedList<>();
        levelQ.add(0, root);
        sb.append(root.val).append(",");
        while (!levelQ.isEmpty()) {
            int cur = 0;
            int size = levelQ.size();
            while (cur < size) {
                //根据本层情况确定下一层的levelsb
                //同时下一层加节点入层序队列
                TreeNode thisNode = levelQ.remove(0);
                if (thisNode.left != null) {
                    sb.append(thisNode.left.val).append(",");
                    levelQ.add(thisNode.left);
                } else sb.append("n").append(",");

                if (thisNode.right != null) {
                    sb.append(thisNode.right.val).append(",");
                    levelQ.add(thisNode.right);
                } else sb.append("n").append(",");
                cur++;
            }
        }
        //sb字符串去掉最后一个,
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    /**
     * Decodes your encoded data to tree.
     * 还是反向走层序组建的过程.
     * 根据当前层的有效节点个数, 判断取下层的节点个数
     * @param data
     * @return
     */
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) return null;
        String[] nodes = data.split(",");
        if (nodes.length == 0) return null;
        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
        List<TreeNode> levelQ = new LinkedList<>();
        levelQ.add(root);
        int i = 0;
        while (!levelQ.isEmpty()) {
            int cur = 0;
            int size = levelQ.size();//此size决定了下一层取几个nodes
            while (cur < size) {
                TreeNode thisNode = levelQ.remove(0);
                if (!nodes[++i].equals("n")) {
                    TreeNode left = new TreeNode(Integer.parseInt(nodes[i]));
                    thisNode.left = left;
                    levelQ.add(left);
                }
                if (!nodes[++i].equals("n")) {
                    TreeNode right = new TreeNode(Integer.parseInt(nodes[i]));
                    thisNode.right = right;
                    levelQ.add(right);
                }
                cur++;
            }
        }
        return root;
    }

    public static void main(String[] args) {
        Codec c = new Codec();
//        TreeNode root = new TreeNode(1);
//        TreeNode node2 = new TreeNode(2);
//        TreeNode node3 = new TreeNode(3);
//        TreeNode node4 = new TreeNode(4);
//        TreeNode node5 = new TreeNode(5);
//        TreeNode node6 = new TreeNode(6);
//        TreeNode node7 = new TreeNode(7);
//        TreeNode node8 = new TreeNode(8);
//        TreeNode node9 = new TreeNode(9);
//        TreeNode node10 = new TreeNode(10);
//        root.left = node2; root.right = node3;
//        node2.left = node4; node2.right = node5;
//        node3.right = node6;
//        node4.left = node7;
//        node6.left = node8; node6.right = node9;
//        node8.right = node10;
//        System.out.println(c.serialize(root));
//        String[] ss = "1,2,3,4,5,n,6,7,n,n,n,8,9,n,n,n,10,n,n,n,n".split(",");
//        System.out.println(ss.length);
        String s = "1,2,3,4,5,n,6,7,n,n,n,8,9,n,n,n,10,n,n,n,n";
        TreeNode root = c.deserialize(s);
    }

}



class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
