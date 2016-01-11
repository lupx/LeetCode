package com.leetcode.number25;

/**
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 * If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 * You may not alter the values in the nodes, only nodes itself may be changed.
 * Only constant memory is allowed.
 * For example,
 * Given this linked list: 1->2->3->4->5
 * For k = 2, you should return: 2->1->4->3->5
 * For k = 3, you should return: 3->2->1->4->5
 *
 * Created by PeixinLu on 15/9/21.
 */
public class Solution {

    /**
     * 翻转K组,翻转每K个元素
     *
     * @param head 给定链表头
     * @param k 给定k,翻转每k个元素
     * @return 翻转后链表头
     */
    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) return null;
        if (k == 0 || k == 1 || head.next == null) return head; //特殊情况
        if (head.next.next == null && k <= 2) {
            //由于我习惯使用3个指针翻转链表,所以2个元素的时候,算是个特殊情况
            //如果k<=2,当前链表才需要翻转,否则不需要
            ListNode newHead = head.next;
            head.next.next = head;
            head.next = null;
            return newHead;
        }

        ListNode nextHead = new ListNode(0); //下一个环节的头节点,用来递归
        ListNode tail = head;//用来数元素个数,到K个的时候,循环结束

        int i = 0;
        while (tail.next != null) {
            tail = tail.next;
            i++;
            if (i == k - 1) {
                //到达了前k个元素
                //因为i从0开始,所以i==k-1才是结束条件
                nextHead = tail.next;
                break;
            }
        }
        /**
         * 上面循环如果自然结束,此时i肯定小于k-1
         * 由于不足k个,所以直接返回当前链表
         */
        if(i < k - 1) {return head;}

        /**
         * 如果走到这里,说明已经数够了k个元素
         * 使用3个指针翻转链表,翻转个数为k个.
         * 当翻转个数够了的时候, 翻转应当结束, p1最终就是头结点,直接返回
         */
        ListNode p1 = head;
        ListNode p2 = head.next;
        ListNode p = head.next.next;
        head.next = reverseKGroup(nextHead, k);//当前头节点肯定是翻转后最后一个节点,所以提前指向剩余链表的头结点
        int x = 0;
        while(x < k - 1) {
            p2.next = p1;
            p1 = p2;
            p2 = p;
            if(p == null) break;
            p = p.next;
            x++;
        }
        return p1;
    }

    public static void main(String[] args) {
        int[] n1 = {1,2};
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
        ListNode h = reverseKGroup(head1, 3);
        for(int i = 0; i < n1.length; i++){
            System.out.println(h.val);
            h = h.next;
        }


    }
}
