package com.leetcode.number82;


/**
 * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
 * For example,
 * Given 1->2->3->3->4->4->5, return 1->2->5.
 * Given 1->1->1->2->3, return 2->3.
 *
 * Created by PeixinLu on 15/10/16.
 */
public class Solution {

    /**
     * dummy node思想,一定要注意.
     * 但凡是涉及在链表里做增删操作的, 最好用dummy node来做
     *
     * 一个cur指针,和一个pre指针, pre.next = cur,
     * 两个指针同时从头往后遍历链表, 如果cur后一个和当前不同, 两指针继续移动.
     * 如果cur后一个和当前一样, cur继续移动,直到找到后一个和当前不同的:
     *    pre.next = cur.next
     *    cur = cur.next
     *    循环继续
     *
     *
     * @param head
     * @return
     */
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;
        boolean duplicated = false;
        while (cur != null) {
            if (cur.next != null && cur.val == cur.next.val) {
                cur = cur.next;
                duplicated = true;
                continue;
            }
            if (cur.next != null && cur.val != cur.next.val) {
                if (duplicated) {
                    pre.next = cur.next;
                    cur = cur.next;
                    duplicated = false;
                    continue;
                }
                pre = cur;
                cur = cur.next;
            } else {
                if (duplicated) {
                    pre.next = null;
                }
                break;
            }
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        int[] n = {1,2,2,2,3,4};
        ListNode p = new ListNode(n[0]);
        ListNode head = p;
        for(int i = 1; i < n.length; i++){
            ListNode node = new ListNode(n[i]);
            p.next = node;
            p = node;
        }

        ListNode h = deleteDuplicates(head);
        while (h != null) {
            System.out.println(h.val);
            h = h.next;
        }
    }
}

