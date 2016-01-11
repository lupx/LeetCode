package com.leetcode.number92;


/**
 * Reverse a linked list from position m to n. Do it in-place and in one-pass.
 * For example:
 * Given 1->2->3->4->5->NULL, m = 2 and n = 4,
 * return 1->4->3->2->5->NULL.
 * Note:
 * Given m, n satisfy the following condition:
 * 1 ≤ m ≤ n ≤ length of list.
 * Created by Peixin Lu on 15/10/22.
 */
public class Solution {

    /**
     * 旋转制定范围内(第m个节点到第n个节点)的链表
     * 同时,题目保证 1 <= m <= n <= 链表长度
     * 所以不会出现异常情况
     *
     * @param head
     * @param m
     * @param n
     * @return
     */
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode newTail = dummy;
        ListNode firstTail = dummy;
        ListNode p1 = dummy;
        ListNode p2 = head;
        ListNode tmp = dummy;
        int count = Integer.MIN_VALUE;
        int pos = 0;//p1所处的位置, 从0开始
        while(p2 != null && count != n - m) {
            if (pos == m - 1) {
                firstTail = p1;
                count = 0;
            }
            if (pos >= m) {
                if (pos == m) {
                    newTail = p1;
                }
                count++;
                tmp = p2.next;
                p2.next = p1;
                p1 = p2;
                p2 = tmp;
            } else {
                p1 = p2;
                p2 = p2.next;
            }
            pos++;
        }
        firstTail.next = p1;
        if (newTail != dummy) {
            newTail.next = tmp;
        }
        return dummy.next;
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

        ListNode h = reverseBetween(head, 1, 1);
        while (h != null) {
            System.out.println(h.val);
            h = h.next;
        }
    }
}

