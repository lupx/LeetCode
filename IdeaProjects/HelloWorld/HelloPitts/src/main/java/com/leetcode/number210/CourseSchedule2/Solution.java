package com.leetcode.number210.CourseSchedule2;

import java.util.*;

/**
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 * Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.
 * There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.
 * For example:
 * 2, [[1,0]]
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1]
 * 4, [[1,0],[2,0],[3,1],[3,2]]
 *   There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2.
 *   Both courses 1 and 2 should be taken after you finished course 0.
 *   So one correct course order is [0,1,2,3].
 *       Another correct ordering is[0,2,1,3].
 *
 * Created by Peixin Lu on 15/12/25.
 */
public class Solution {

    /**
     * 等价为有向图找环:
     * 考虑拓扑排序
     * 构造邻接矩阵, 然后从入度为0开始找
     * beat 49.36%
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];//入度记录
        Map<Integer, List<Integer>> map = new HashMap<>();//邻接表

        //构造邻接表
        for (int i = 0; i < prerequisites.length; i++) {
            List<Integer> adj = map.getOrDefault(prerequisites[i][1], new ArrayList<Integer>());
            adj.add(prerequisites[i][0]);
            map.put(prerequisites[i][1], adj);
            indegree[prerequisites[i][0]]++;//该节点入度+1
        }
        List<Integer> zeroIndegree = new LinkedList<>();//保存入度为0的点, 广搜的基础
        List<Integer> orderList = new ArrayList<>();//order's list
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                zeroIndegree.add(i);
                orderList.add(i);
            }
        }
        //广搜实现拓扑排序 (其实也是个层序遍历,每一轮遍历的是入度为0的点)
        while (!zeroIndegree.isEmpty()) {
            int pointer = 0;
            int size = zeroIndegree.size();//必须这么做!
            while (pointer < size) {
                //取每一个入度为0元素, 根据其邻接表把其邻接点入度-1
                //同时,如果邻接点此时变为0, 加入zeroIndegree
                int thisCourse = zeroIndegree.remove(0);
                List<Integer> adj = map.get(thisCourse);
                if (adj != null) {
                    for (Integer i : adj) {
                        if (--indegree[i] == 0) {//邻接点入度减1
                            zeroIndegree.add(i);
                            orderList.add(i);
                        }
                    }
                }
                pointer++;
            }
        }
        //上面处理完, indegree如果全部变为0, 说明无环. 如果有非0存在,说明必然有环!
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] != 0) return new int[]{};
        }
        int[] rst = new int[orderList.size()];
        int j = 0;
        for (Integer i : orderList) {
            rst[j] = i;
            j++;
        }
        return rst;
    }

    public static void main(String[] args) {
        int[] rst = findOrder(3, new int[][]{{0,1},{1,2}});
        for (int i : rst)
            System.out.println(i);
    }
}
