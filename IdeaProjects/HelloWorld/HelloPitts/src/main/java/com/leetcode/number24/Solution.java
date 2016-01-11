package com.leetcode.number24;
/**
 * Given a linked list, swap every two adjacent nodes and return its head.
 * For example,
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 * Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.
 * 常数空间, 不能改值, 也即只能改指针.
 * Created by PeixinLu on 15/9/21.
 */
public class Solution {

    /**
     * 主方法
     * @param head
     * @return
     */
    public static ListNode swapPairs(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return head;//单节点情况

        ListNode newHead = head.next;//先把头找到,待会儿返回这个newHead
        /**
         * p1和p2共同组成一个环节.环节间换链
         */
        ListNode p1 = head;
        ListNode p2 = head.next;
        ListNode p = head.next.next;

        /**
         * 保证每次循环后,前面能接上后面
         * 实际上,pre指向的就是前一个环节换链后的最后一个元素
         */
        ListNode pre = p1;

        int i = 0;
        while(p2 != null) {
            p2.next = p1;
            p1.next = p;
            /**
             * 第一次换链的时候,pre就指向了p1,无需衔接
             * 第二次换链的时候,第一个环节的最后一个节点pre需要接上当前环节
             */
            if (i > 0) {
                pre.next = p2;
                pre = p1;
            }
            /**
             * p==null,对应了偶数个元素的时候
             * p.next==null,对应了奇数个元素的时候
             * 两者满足其一,就退出
             */
            if(p == null || p.next == null) break;
            p1 = p;
            p2 = p.next;
            p = p2.next;
            i++;
        }
        return newHead;
    }

    public static void main(String[] args) {
        int[] n1 = {1,2,6,3,1,7,4};
        ListNode p = new ListNode(n1[0]);
        ListNode head1 = p;
        for(int i = 1; i < n1.length; i++){
            ListNode node = new ListNode(n1[i]);
            p.next = node;
            p = node;
        }

//        p = head1;
//        for(int i = 0; i < n1.length; i++){
//            System.out.println(p.val);
//            p = p.next;
//        }
//
        ListNode h = swapPairs(head1);
        for(int i = 0; i < n1.length; i++){
            System.out.println(h.val);
            h = h.next;
        }
    }
}
