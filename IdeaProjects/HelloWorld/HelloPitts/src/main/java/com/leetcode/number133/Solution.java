package com.leetcode.number133;

import java.util.*;

/**
 * Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
 * 克隆图
 * Created by Peixin Lu on 15/11/6.
 */
public class Solution {

    /**
     * 重点在于邻接list的克隆.
     * 使用map做原节点和new出来节点的映射关系
     * 然后BFS:
     *  {分别把新旧节点映射进map中, 然后把邻接表加入新节点, 然后BFS其邻居, 加入队列}
     *
     * 最后, map取node的新节点返回, 即为新图
     *
     * @param node
     * @return
     */
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) return null;
        /**
         * store the mapping from old Node to new Node.
         */
        Map<UndirectedGraphNode, UndirectedGraphNode> map
                = new HashMap<>();

        Queue<UndirectedGraphNode> q = new LinkedList<>();
        q.offer(node);
        while (!q.isEmpty()) {
            UndirectedGraphNode oldNode = q.poll();
//            int label = oldNode.label;
            UndirectedGraphNode newNode = new UndirectedGraphNode(oldNode.label);
            map.put(oldNode, newNode);
            for (UndirectedGraphNode n : oldNode.neighbors) {
                if (!map.containsKey(n))
                    q.offer(n);
            }
        }

        Iterator<UndirectedGraphNode> itr = map.keySet().iterator();
        while (itr.hasNext()) {
            UndirectedGraphNode oldNode = itr.next();
            UndirectedGraphNode newNode = map.get(oldNode);
            for (UndirectedGraphNode thisNode : oldNode.neighbors) {
                newNode.neighbors.add(map.get(thisNode));
            }
        }
        return map.get(node);
    }

    public static void main(String[] args) {
        UndirectedGraphNode node1 = new UndirectedGraphNode(1);
        UndirectedGraphNode node2 = new UndirectedGraphNode(1);
        Map<Integer, UndirectedGraphNode> map = new HashMap<>();
        map.put(node1.label, node1);
        map.put(node2.label, node2);
        if (map.containsKey(node1.label)) {
            System.out.println("Yes");
        }
    }
}

