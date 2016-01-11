package com.leetcode.number143;

/**
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 * You must do this in-place without altering the nodes' values.
 * For example,
 * Given {1,2,3,4}, reorder it to {1,4,2,3}.
 *
 * Created by Peixin Lu on 15/11/6.
 */
public class Solution {

    /**
     * 快慢指针找链表中点, 再把后半段翻转
     * 然后你一个我一个链起来就行了
     * @param head
     */
    public static void reorderList(ListNode head) {
        if (head == null || head.next == null
                || head.next.next == null) return;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode faster = dummy;
        ListNode slower = dummy;
        while (faster != null && faster.next != null) {
            faster = faster.next.next;
            slower = slower.next;
        }
        //slower所指就是中点
        ListNode head2 = slower.next;
        slower.next = null;//注意拆链

        //head2逆序
        ListNode p1 = head2;
        ListNode p2 = head2.next;
        ListNode tmp;
        while (p2 != null) {
            tmp = p2.next;
            p2.next = p1;
            if (p1 == head2) {
                p1.next = null;//这里一定注意!
            }
            p1 = p2;
            p2 = tmp;
        }
        head2 = p1;//head2指向逆序后的头节点

        //穿针引线
        p1 = head;
        p2 = head2;
        ListNode needle = dummy;
        while (p1 != null && p2 != null) {
            needle.next = p1;
            p1 = p1.next;
            needle.next.next = p2;
            p2 = p2.next;
            needle = needle.next.next;
        }
        if (p1 != null) {
            needle.next = p1;
        }
    }

    public static void main(String[] args) {
        int[] n = {1,2};
        ListNode p = new ListNode(n[0]);
        ListNode head = p;
        for(int i = 1; i < n.length; i++){
            ListNode node = new ListNode(n[i]);
            p.next = node;
            p = node;
        }

        reorderList(head);
        System.out.println("aa");
    }
}

