package com.leetcode.number282.ExpressionsAddOperators;

import java.util.ArrayList;
import java.util.List;

/**
 * This problem has a lot of edge cases to be considered:
 *
 * overflow: we use a long type once it is larger than Integer.MAX_VALUE or minimum, we get over it.
 * 0-sequence: because we can't have numbers with multiple digits started with zero, we have to deal with it too.
 * a little trick is that we should save the value that is to be multiplied in the next recursion.
 * beat 62.53%
 * Created by PeixinLu on 16/1/9.
 */
public class SolutionCorrect {

    public List<String> addOperators(String num, int target) {
        List<String> rst = new ArrayList<String>();
        if (num == null || num.length() == 0) return rst;
        helper(rst, "", num, target, 0, 0, 0);
        return rst;
    }

    public void helper(List<String> rst, String path, String num, int target, int pos, long eval, long multed){
        if (pos == num.length()){
            if (target == eval) rst.add(path);
            return;
        }
        for (int i = pos; i < num.length(); i++){
            if (i != pos && num.charAt(pos) == '0') break;//get over the 0-sequence.
            long cur = Long.parseLong(num.substring(pos, i + 1));
            if (pos == 0){
                helper(rst, path + cur, num, target, i + 1, cur, cur);
            } else {
                helper(rst, path + "+" + cur, num, target, i + 1, eval + cur , cur);

                helper(rst, path + "-" + cur, num, target, i + 1, eval - cur, -cur);

                helper(rst, path + "*" + cur, num, target, i + 1, eval - multed + multed * cur, multed * cur );
            }
        }
    }
}
