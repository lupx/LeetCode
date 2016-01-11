package com.leetcode.number146;

import java.util.*;

/**
 * Design and implement a data structure for Least Recently Used (LRU) cache.
 *  It should support the following operations:
 *  get and set.
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * set(key, value) - Set or insert the value if the key is not already present.
 *  When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 * Created by Peixin Lu on 15/11/6.
 */
public class LRUCache {

    /**
     * cache
     */
    Map<Integer, Integer> cache;

    /**
     * The "queue" head, which contains 'node's.
     */
    Node queue;
    /**
     * The "queue" tail, means the MOST RECENTLY USED value
     */
    Node tail;

    /**
     * element's index in the queue
     * value to its prev-node
     */
    Map<Integer, Node> queuePre;



    /**
     * 定义了node节点
     */
    class Node {
        Integer val;
        Node next;
        Node (Integer v) {
            this.val = v;
        }
    }

    /**
     * 总容量
     */
    int capacity;

    /**
     * 用队列(链表)来实现先来后到
     * 用map来实现cache
     * @param capacity
     */
    public LRUCache(int capacity) {
        this.cache = new HashMap<>();
        this.queue = new Node(0);//dummy node
        this.tail = this.queue;
        this.queuePre = new HashMap<>();
        this.capacity = capacity;
    }

    /**
     * 每次get的时候,先从map中找一下
     *   如果有, 那么把队列中对应的元素删除掉, 然后重新加入队尾
     *   如果没有,返回-1
     * @param key
     * @return
     */
    public int get(int key) {
        if (cache.containsKey(key)) {
            Node pre = queuePre.get(key);//get the pre node
            //move this key node to the tail.
            Node thisNode = pre.next;
            if (thisNode != tail) {
                pre.next = pre.next.next;
                queuePre.put(thisNode.next.val, pre);
                tail.next = thisNode;
                Node last = tail;
                queuePre.put(key, last);
                tail = thisNode;
                thisNode.next = null;
            }
            return cache.get(key);
        } else {
            return -1;
        }
    }

    /**
     * "cache"中没有的时候, 才需要set新值
     * 每次set的时候,
     *    如果队列已达到capacity, 那么队头出列, 新值加入队尾
     *    如果队列尚未达到capacity, 那么直接入队尾
     * 同时, 新值入map
     * @param key
     * @param value
     */
    public void set(int key, int value) {
        if (get(key) == -1) {
            //no such value
            Node thisNode = new Node(key);//以key为node的值
            if (cache.size() == capacity) {
                //remove the head
                Node head = queue.next;
                cache.remove(head.val);
                if (tail == queue.next) {//capacity==1
                    queue.next = thisNode;
                    queuePre.put(key, queue);
                    tail = thisNode;
                } else {
                    queue.next = queue.next.next;//去头节点
                    queuePre.put(queue.next.val, queue);
                    tail.next = thisNode;
                    Node last = tail;
                    queuePre.put(key, last);
                    tail = thisNode;
                }
            } else {
                Node last = tail;
                queuePre.put(key, last);
                tail.next = thisNode;//加链
                tail = thisNode;//更新tail
            }
            cache.put(key, value);
        } else {
            //has such value
            //just move the node to the tail
            Node pre = queuePre.get(key);//get the pre node
            //move this key node to the tail.
            Node thisNode = pre.next;
            if (thisNode != tail) {
                pre.next = pre.next.next;
                queuePre.put(thisNode.next.val, pre);
                tail.next = thisNode;
                Node last = tail;
                queuePre.put(key, last);
                tail = thisNode;
                thisNode.next = null;
            }
            cache.put(key,value);
        }
    }

    public static void main(String[] args) {
        LRUCache lru = new LRUCache(2);
        lru.set(2,1);
        lru.set(2,2);
        System.out.println(lru.get(2));
//        System.out.println(lru.get(2));
        lru.set(1,1);
        lru.set(4,1);
        System.out.println(lru.get(2));
//        System.out.println(lru.get(3));
//        System.out.println(lru.get(4));
    }
}

