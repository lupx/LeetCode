package com.leetcode.number65;

/**
 * Validate if a given string is numeric.
 * Some examples:
 * "0" => true
 * " 0.1 " => true
 * "abc" => false
 * "1 a" => false
 * "2e10" => true
 * Created by Peixin Lu on 15/10/14.
 */
public class Solution {

    /**
     * 经过试验, 表达式是false的,前后有空格可以,但中间有空格就不行
     * 中间可以有e,但是两边都不能有e
     * 还可以有+/-/. 三种符号
     * 用filter思路:
     * 第一层先滤e, 有e且位置合法,从e两边分别进入下一层
     *       无e, 直接进入下一层
     * 第二层滤., 有.且位置合法, 从.两边分别进入下一层
     *       无., 直接进入下一层
     * 第三层滤+/-, 有符号且个数位置合法, 使用replaceAll把符号去掉,
     *       最终剩下的串用Integer.parseInt判断是否是数字
     *
     * @param s
     * @return
     */
    public static boolean isNumber(String s) {
        //先trim
        String trimmed = s.trim();
        if (trimmed.length() == 0) return false;
        //替换" ",如果长度减少,那么肯定有问题
        int len = trimmed.length();
        trimmed = trimmed.replaceAll(" ", "");
        if (trimmed.length() != len) return false;

        int ecount = Integer.MIN_VALUE;

        String ermvd = trimmed.replaceAll("e", "");
        ecount = len - ermvd.length();

        if (ecount > 1) {return false;}
        if (ecount == 0) {
            //无e情况
            return isNumberForDot(trimmed, 0);
        }
        //一个e
        if (trimmed.charAt(0) == 'e'
                || trimmed.charAt(trimmed.length() - 1) == 'e') {
            return false;//e在两头肯定false
        }
        String[] esplit = trimmed.split("e");
        //两边字符串递归一下
        return isNumberForDot(esplit[0], 0) && isNumberForDot(esplit[1], 1);
    }

    /**
     * 判断串中小数点是否合法. 右半边无论如何不能有'.'
     * 如果小数点合法, 同时小数点后无符号:
     * 从小数点分割, 左右两边分别parseInt
     * @param trimmed
     * @param side 0左,1右
     * @return
     */
    public static boolean isNumberForDot(String trimmed, int side) {
        if (trimmed.equals(".")) return false;
        int dotcount = Integer.MIN_VALUE;
        String dotrmvd = trimmed.replaceAll("\\.", "");
        dotcount = trimmed.length() - dotrmvd.length();

        if (dotcount > 1) {
            return false;
        }
        if (dotcount == 1) {
            //点后不能是符号, 其他都可以
            if (side == 1) {
                //e右边不能有'.'
                return false;
            }
            String[] dotsplit = trimmed.split("\\.");
            if (dotsplit.length == 0) return false;
            if (dotsplit.length == 1) {
                return isNumberForSign(dotsplit[0], 0, false);
            }
            if (!dotsplit[1].equals("")
                    && (dotsplit[1].charAt(0) == '+')
                    || dotsplit[1].charAt(0) == '-') {
                return false;
            }
            return isNumberForSign(dotsplit[0], 0, true)
                    && isNumberForSign(dotsplit[1], 1, true);
        }
        return isNumberForSign(trimmed, 0, false);
    }

    /**
     * 最终判断+/-是否合法
     *
     * @param trimmed
     * @param side, 左或右, 左右情况是不同的.
     *              0: 左
     *              1: 右
     * @param hasSide, 有无另一边
     *                 false: 无
     *                 true: 有
     * @return
     */
    public static boolean isNumberForSign(String trimmed, int side, boolean hasSide) {
        if (trimmed.equals("")) return true;
        if (trimmed.equals("+") || trimmed.equals("-")) {
            if (side == 0 && !hasSide ) {
                //当前是左边, 且右边无
                return false;
            }
            if (side == 1) {
                //当前是右边, 无论如何是false
                return false;
            }
        }
        Integer negcount = Integer.MIN_VALUE;
        Integer pluscount = Integer.MIN_VALUE;
        String negrmvd = trimmed.replaceAll("-", "");
        negcount = trimmed.length() - negrmvd.length();
        String plusrmvd = trimmed.replaceAll("\\+", "");
        pluscount = trimmed.length() - plusrmvd.length();

        if (negcount > 1 || pluscount > 1) {
            return false;
        }
        if (negcount == 1 && pluscount == 1) {
            return false;
        }
        if (negcount == 1) {
            if (trimmed.charAt(0) != '-') {
                return false;
            }
        }
        if (pluscount == 1) {
            if (trimmed.charAt(0) != '+') {
                return false;
            }
        }

        String fin = trimmed.replaceAll("-", "");
        fin = fin.replaceAll("\\+", "");
        while (fin.length() > 0) {
            if (fin.length() > 8) {
                try {
                    Integer.parseInt(fin.substring(0,8));
                } catch (NumberFormatException n) {
                    return false;
                }
                fin = fin.substring(8, fin.length() - 1);
            } else {
                try {
                    Integer.parseInt(fin);
                } catch (NumberFormatException n) {
                    return false;
                }
                break;
            }
        }
        return true;
    }

    public static void main (String[] args) {
//        Integer.parseInt("12312.22");
        System.out.println(isNumber("+2e.5"));
    }

}
