package com.leetcode.number147;

import java.util.*;

/**
 * 要求用插入排序给链表排序
 * Created by Peixin Lu on 15/11/6.
 */
public class Solution {

    /**
     * 用一个map, 把每个节点和其pre节点保存起来
     * 此做法速度很慢, 如果不用map, 每次都用.next互相比, 应该会提速不少
     * @param head
     * @return
     */
    public static ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) return head;

        Map<ListNode, ListNode> map = new HashMap<>();
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode p1 = dummy;
        ListNode p2 = head;
        while (p2 != null) {
            map.put(p2,p1);//保存每个节点和其pre节点的映射关系
            p1 = p1.next;
            p2 = p2.next;
        }

        //插入thisNode的时候有如下步骤 (待插入位置节点node.val > thisNode.val):
        //tmp = thisNode.
        //thisNode.pre.next = thisNode.next;
        //tmp.next = node;
        //node.pre.next = tmp;
        ListNode tmp = dummy;
        ListNode node = head;
        p1 = head.next;
        p2 = head;


        ListNode pre1 = null;
        ListNode pre2 = null;
        ListNode nextp1 = p1.next;

        //p1即为thisNode, 从头往后遍历
        while (p1 != null) {
            //p2即为node的遍历指针, 从头往node遍历
            nextp1 = p1.next;
            while (p2 != node.next) {
                if (p1.val < p2.val) {
                    tmp = p1;
                    pre1 = map.get(p1);
                    map.put(p1.next, pre1);//更新p1.next的前节点
                    pre1.next = p1.next;//删除p1, tmp此时是p1

                    pre2 = map.get(p2);
                    map.put(p2, tmp);
                    tmp.next = p2;
                    pre2.next = tmp;
                    map.put(tmp, pre2);

                    break;
                } else {
                    p2 = p2.next;
                }
            }
            p1 = nextp1;
            //如果p1直接追加到node后, node才移动
            if (p2 == node.next) {
                node = node.next;
            }
            p2 = dummy.next;
        }
        return dummy.next;
    }


    /**
     * 为加快速度, 不用map, 同时只要升序就continue, 遇到降序再考虑插入的问题
     * 速度飞快, beats 89.64%!
     * @param head
     * @return
     */
    public static ListNode insertionSortList_v2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode p1 = dummy;//插入指针
        ListNode p2 = dummy;//插入指针
        ListNode tmp = dummy;//临时指针
        ListNode pp = dummy.next;//从头往后遍历

        while (pp.next != null) {
            while (pp.next != null && pp.next.val > pp.val) {
                pp = pp.next;
            }
            if (pp.next == null) break;
            //while ends when pp.next.val < pp.val
            //pp.next即为待插入元素
            //pp即为之前区间最大元素,最右边元素
            //从dummy开始一个一个拿.next和pp.next做比较, 找到合适的插入位
            p1 = dummy;
            p2 = pp;
            while (p1 != pp) {
                if (p1.next.val <  p2.next.val) {
                    p1 = p1.next;
                } else {
                    //p1.next即为插入位
                    tmp = pp.next;
                    pp.next = tmp.next;
                    tmp.next = p1.next;
                    p1.next = tmp;
                    break;
                }
            }
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        int[] n = {5,12,31,2,1,4,7,8,4,2,31,1};
        ListNode p = new ListNode(n[0]);
        ListNode head = p;
        for(int i = 1; i < n.length; i++){
            ListNode node = new ListNode(n[i]);
            p.next = node;
            p = node;
        }

        ListNode h = insertionSortList_v2(head);
        while (h != null) {
            System.out.println(h.val);
            h = h.next;
        }
    }
}

