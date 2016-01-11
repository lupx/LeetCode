package com.leetcode.number138;

import java.util.HashMap;
import java.util.Map;

/**
 * A linked list is given such that each node contains an additional random pointer,
 *    which could point to any node in the list or null.
 * Return a deep copy of the list.
 * 深拷贝一个带随机指针的链表
 * Created by Peixin Lu on 15/11/6.
 */
public class Solution {


    /**
     * 肯定是借助hash表做比较方便一点
     * dummy node思想
     * 先复制next链, 全部节点和新节点建立映射关系
     * 再从头挨个找random指针, 新节点建立random关系
     * 最后返回dummy.next
     *
     * 6ms, 有点慢
     * @param head
     * @return
     */
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) return null;
        Map<RandomListNode, RandomListNode> map = new HashMap<>();
        RandomListNode cur = head;
        RandomListNode dummy = new RandomListNode(0);
        RandomListNode newCur = dummy;

        while (cur != null) {
            RandomListNode newNode = new RandomListNode(cur.label);
            map.put(cur, newNode);
            newCur.next = newNode;
            newCur = newNode;
            cur = cur.next;
        }

        /**
         * 复制随机指针
         */
        cur = head;
        while (cur != null) {
            RandomListNode newNode = map.get(cur);
            newNode.random = map.get(cur.random);
            cur = cur.next;
        }
        return dummy.next;
    }


    public static void main(String[] args) {
    }
}

