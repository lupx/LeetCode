package com.leetcode.number206;

/**
 * Reverse a singly linked list.
 * Created by Peixin Lu on 15/12/2.
 */
public class Solution {

    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode tmp;
        ListNode cur = head.next;
        ListNode pre = head;
        while (cur != null) {
            tmp = cur.next;
            cur.next = pre;
            if (pre == head) pre.next = null;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }

    public static void main(String[] args) {
        int[] n = {1};
        ListNode p = new ListNode(n[0]);
        ListNode head = p;
        for(int i = 1; i < n.length; i++){
            ListNode node = new ListNode(n[i]);
            p.next = node;
            p = node;
        }

        ListNode h = reverseList(head);
        while (h != null) {
            System.out.println(h.val);
            h = h.next;
        }
    }
}



class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}