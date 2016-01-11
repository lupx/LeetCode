package com.leetcode.number109;

/**
 * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 * Created by Peixin Lu on 15/10/24.
 */
public class Solution {

    /**
     * 递归, 每次找中点作为根,然后左右作为子树
     * 其实和数组一个道理, 就是找到中点即可:
     * 快慢指针找到中点, 断链, 然后两个新list递归
     * 2ms
     * @param  head
     * @return
     */
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode quicker = dummy;
        ListNode slower = dummy;
        ListNode tail = dummy;
        while (quicker != null && quicker.next != null) {
            quicker = quicker.next.next;
            slower = slower.next;
            if (slower != dummy && slower != dummy.next) {
                tail = tail.next;
            }
        }
        //slower所处位置可以理解为中点
        //此时有几个可能性:
        //(1)1个节点的时候, s和tail都没动过
        //(2)2个节点的时候, tail指向dummy, tail.next指向s
        //(3)此外, tail.next指向s, s为mid点
        if (slower == dummy && tail == dummy) {
            return new TreeNode(head.val);
        }
        if (tail == dummy) {//2个节点的情况, 不需要左子树
            TreeNode curRoot = new TreeNode(slower.val);
            curRoot.right = sortedListToBST(slower.next);
            return curRoot;
        }

        TreeNode curRoot = new TreeNode(slower.val);
        tail.next = null;//断链
        curRoot.left = sortedListToBST(head);
        curRoot.right = sortedListToBST(slower.next);
        return curRoot;
    }

    public static void main(String[] args) {
    }
}

