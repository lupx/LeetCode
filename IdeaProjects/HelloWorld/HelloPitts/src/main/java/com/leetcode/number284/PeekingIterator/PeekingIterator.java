package com.leetcode.number284.PeekingIterator;

import java.util.Iterator;

/**
 * beat 85.14%
 * Created by PeixinLu on 16/1/9.
 */
public class PeekingIterator implements Iterator<Integer> {

    Iterator<Integer> iterator;
    Integer next = 0;
    boolean hasNext = true;

    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        this.iterator = iterator;
        if (iterator.hasNext()) next = iterator.next();
        else this.hasNext = false;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        return next;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        int tmp = next;
        if (iterator.hasNext()) next = iterator.next();
        else hasNext = false;
        return tmp;
    }

    @Override
    public boolean hasNext() {
        return hasNext;
    }
}
