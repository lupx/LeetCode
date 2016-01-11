package com.leetcode.number86;


/**
 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
 * You should preserve the original relative order of the nodes in each of the two partitions.
 * For example,
 * Given 1->4->3->2->5->2 and x = 3,
 * return 1->2->2->4->3->5.
 * Created by PeixinLu on 15/10/16.
 */
public class Solution {

    /**
     * 分割排序, 小于x的放到那些大于等于x的节点的左边
     * 并且两边的相对顺序要保持不变
     * 两个dummyNode,d1, d2. 一个用来链接所有小于x的节点, 另外一个用来链接大于x的节点
     * 一个cur指针用来遍历链表. fir指针用来串小的节点, sec指针用来串大与等于的节点.
     *    遇到小于x的节点, fir.next = cur, 然后fir = cur, cur = cur.next
     *    遇到大于等于x的节点, sec.next = cur, 然后sec = cur, cur = cur.next
     * 在循环结束后, fir.next = d2.next.
     * return d1.next;
     * 此算法MLE! 因为多new了一个dummyNode
     * @param head
     * @param x
     * @return
     */
    public static ListNode partition(ListNode head, int x) {
        if (head == null) return head;
        ListNode dummy1 = new ListNode(0);
        ListNode dummy2 = new ListNode(0);
        ListNode fir = dummy1;
        ListNode sec = dummy2;
        while (head != null) {
            if (head.val == x || head.val > x) {
                sec.next = head;
                sec = head;
                head = head.next;
            } else {
                fir.next = head;
                fir = head;
                head = head.next;
            }
        }

        fir.next = dummy2.next;
        return dummy1.next;
    }

    /**
     * 少new一个dummynode的算法
     * sec和secHead的改链,以及最后特殊情况的考虑
     * @param head
     * @param x
     * @return
     */
    public static ListNode partitionv2(ListNode head, int x) {
        if (head == null) return head;
        ListNode dummy = new ListNode(0);
        ListNode fir = dummy;
        ListNode sec = dummy;
        ListNode secHead = dummy;
        while (head != null) {
            if (head.val == x || head.val > x) {
                if (secHead == dummy) {
                    sec = head;
                    secHead = head;
                } else {
                    sec.next = head;
                    sec = head;
                }
                head = head.next;
            } else {
                fir.next = head;
                fir = head;
                head = head.next;
            }
        }
        //如果根本没有等于或大于x的节点,那么sec和secHead不能随便动
        if (secHead != dummy) {
            fir.next = secHead;
            sec.next = null;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        int[] n = {2,2,5,2,1};
        ListNode p = new ListNode(n[0]);
        ListNode head = p;
        for(int i = 1; i < n.length; i++){
            ListNode node = new ListNode(n[i]);
            p.next = node;
            p = node;
        }

        ListNode h = partitionv2(head, 3);
        while (h != null) {
            System.out.println(h.val);
            h = h.next;
        }
    }
}

