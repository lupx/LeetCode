package com.leetcode.number71;

import java.util.Stack;

/**
 * Given an absolute path for a file (Unix-style), simplify it.
 * For example,
 * path = "/home/", => "/home"
 * path = "/a/./b/../../c/", => "/c"
 *
 * Created by Peixin Lu on 15/10/14.
 */
public class Solution {

    /**
     * 一个点表示当前目录, 两个点表示上层目录
     * 考虑用栈实现:
     * 一个.的时候直接continue;
     * 两个点的时候栈顶出栈.如果栈里为空, 则忽略不管
     * 多个/当一个/处理
     *
     * @param path
     * @return
     */
    public static String simplifyPath(String path) {
        if (path == null) return null;
        if (path.equals("/") || path.equals("")) return "/";

        String[] folders = path.split("/");
        Stack<String> s = new Stack<String>();
        for (int i = 0; i < folders.length; i++) {
            if (folders[i].equals("")) {
                continue;
            }
            if (folders[i].equals(".")) {
                continue;
            }
            if (folders[i].equals("..")) {
                if (s.empty()) {
                    continue;
                } else {
                    s.pop();
                    continue;
                }
            }
            s.push(folders[i]);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("/");//根目录必须有
        if (s.size() == 0) {
            return sb.toString();
        }
        String[] results = new String[s.size()];
        for (int i = s.size() - 1; i >= 0; i--) {
            results[i] = s.pop(); //按顺序放入数组
        }
        for (int i = 0; i < results.length; i++) {
            sb.append(results[i]).append("/");
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }


    public static void main (String[] args) {
        String a = "  ";
        System.out.println(simplifyPath(a));
    }

}
