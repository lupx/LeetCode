package com.leetcode.number83;


/**
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 * For example,
 * Given 1->1->2, return 1->2.
 * Given 1->1->2->3->3, return 1->2->3.
 *
 * Created by PeixinLu on 15/10/16.
 */
public class Solution {

    /**
     * 对于重复的, 得保留一个.
     * 和上一题的唯一区别在于pre指针的移动, 每当遇到重复的情况,
     * pre先移动到自己的下一个,然后再改链
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
                    pre = pre.next; //和上一题的变化点!
                    pre.next = cur.next;
                    cur = cur.next;
                    duplicated = false;
                    continue;
                }
                pre = cur;
                cur = cur.next;
            } else {
                if (duplicated) {
                    pre = pre.next;//和上一题的变化点!
                    pre.next = null;
                }
                break;
            }
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        int[] n = {1,2,2,2,4};
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

