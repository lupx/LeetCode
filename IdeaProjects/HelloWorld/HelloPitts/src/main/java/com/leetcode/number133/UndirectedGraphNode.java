package com.leetcode.number133;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by PeixinLu on 15/11/10.
 */
class UndirectedGraphNode {
    int label;
    List<UndirectedGraphNode> neighbors;
    UndirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<UndirectedGraphNode>();
    }
}
