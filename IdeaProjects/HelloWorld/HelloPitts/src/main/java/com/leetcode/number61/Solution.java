package com.leetcode.number61;

/**
 * Given a list, rotate the list to the right by k places, where k is non-negative.
 * For example:
 * Given 1->2->3->4->5->NULL and k = 2,
 * return 4->5->1->2->3->NULL.
 *
 * Created by Peixin Lu on 15/10/13.
 */
public class Solution {

    /**
     * 链表右转k个位置
     * 3个指针就搞定:
     * p1指针从头往后走, 当走到第2个位置时, tail指向head并且开始和p1保持同步.
     * 当走到k+2位置时, p2放到头部, 并且开始和p1保持同步移动.
     * 当p1走到null的时候, while-loop停止.
     * 此时, p2所处位置将变为新链表尾部, p2.next变为新头部
     * tail所处位置是现在的尾部, tail = head. 即可保证尾部衔接头部.
     * 最后, p2.next = null
     *
     * return newHead.
     * @param head
     * @param k
     * @return
     */
    public static ListNode rotateRight(ListNode head, int k) {
        if (k == 0) return head;
        if (head == null) return head;
        //k对长度取余赋给k,然后进行下面的计算
        int i = 0;
        ListNode p = head;
        while(p != null) {
            p = p.next;
            i++;
        }
        k = k % i;

        ListNode newHead = new ListNode(0);
        ListNode p1 = head;
        ListNode newTail = head;
        ListNode tail = head;
        boolean stMvNewTail = false;
        boolean stMvTail = false;

        //p1先走一步, 然后tail开始移动
        p1 = p1.next;
        stMvTail = true;
        int count = 1;//计步器
        while (p1 != null) {
            p1 = p1.next;//p1移动
            tail = tail.next;//tail移动
            count++;
            if (count == k + 1) {
                stMvNewTail = true;//newTail开始移动
                break;
            }
        }

        while (p1 != null) {
            p1 = p1.next;
            tail = tail.next;
            newTail = newTail.next;
        }

        //走到这里有2个可能性:
        //(1)newTail启动了移动, 那么newTail停留的位置就是新的尾节点
        //(2)newTail还没开始移动, 说明k>=链表长度,直接返回head即可
        if (stMvNewTail) {
            newHead = newTail.next;
            tail.next = head;
            newTail.next = null;
            return newHead;
        } else {
            return head;
        }
    }

    public static void main (String[] args) {
        int[] n = {1,2};
        ListNode p = new ListNode(n[0]);
        ListNode head = p;
        for(int i = 1; i < n.length; i++){
            ListNode node = new ListNode(n[i]);
            p.next = node;
            p = node;
        }
        head = rotateRight(head, 3);
        for(int i = 0; i < n.length; i++){
            System.out.println(head.val);
            head = head.next;
        }

    }

}
