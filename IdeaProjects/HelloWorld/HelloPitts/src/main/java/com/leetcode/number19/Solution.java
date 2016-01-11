package com.leetcode.number19;


/**
 * Given a linked list, remove the nth node from the end of list and return its head.
 * For example,
 * Given linked list: 1->2->3->4->5, and n = 2.
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 * Created by PeixinLu on 15/9/21.
 */
public class Solution {

    /**
     *
     * n will always be valid.
     * and head will never be null
     * Try to do this in one pass.
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode p = head;
        int count = 0;
        ListNode nth = new ListNode(0);
        /**
         * nth.next指向头节点.
         * 这样,如果需要删除的就是头节点,那么nth就不会移动,可以根据这个来判断是否需要删除头结点.
         */
        nth.next = head;
        while(p != null) {
            p = p.next;
            count++; //用来判断什么时候需要开始移动nth
            if(count >= n + 1) {
                /**
                 * 开始移动nth
                 */
                nth = nth.next;
            }
        }
        if(nth.next == head) {
            //means need to remove the head
            head = head.next;
        } else {
            //nth -> the previous of nth node from END
            //link operation
            p = nth.next;
            nth.next = p.next;
            p.next = null;
        }
        return head;
    }


    public static void main(String[] args) {
        int[] n = {1,2};
        ListNode p = new ListNode(n[0]);
        ListNode head = p;
        for(int i = 1; i < 2; i++){
            ListNode node = new ListNode(n[i]);
            p.next = node;
            p = node;
        }

        ListNode h = removeNthFromEnd(head, 2);
        for(int i = 0; i < 1; i++){
            System.out.println(h.val);
            h = h.next;
        }
    }
}
