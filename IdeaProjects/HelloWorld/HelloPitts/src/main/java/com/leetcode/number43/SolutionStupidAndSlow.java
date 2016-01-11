package com.leetcode.number43;


import java.util.*;

/**
 * Given two numbers represented as strings, return multiplication of the numbers as a string.
 * Note: The numbers can be arbitrarily large and are non-negative.
 * Created by Peixin Lu on 15/10/5.
 */
public class SolutionStupidAndSlow {

    /**
     * 需要注意, 两个数可能非常大, 这样的话, 不能直接乘.
     * 所以, 考虑i,j两个指针, 一开始分别指向nums1和nums2的最后一位
     * 然后, 再分别给2个变量count1和count2, 用来记录当前数字相乘的子结果后有几个0.
     * 比如: 3*9, 子结果后有0个0
     * 30*20,这个时候, 3后有1个0, 2后有1个0 ,所以其积后有2个0, "6" + "00"
     * 当i和j都>0的时候, i和j一起向0移动. 并且移动一位,需要算当前子结果. 并且把当前子结果append到总的result字符串中
     *
     * 计算子结果的过程:
     * 两次循环, nums1从i位置起之后的每个数 * nums2从j位置起之后的每个数. 得到一个结果, 每个结果还对应了一个"0"的count.
     * 这些结果对都存入map中, 并且把map存入一个arrayList里.
     * 最终循环结束后, 从list中一个一个取出来,根据"0"count数把结果对应append到result字符串中.
     * 上面全部执行完后, i--, j-- ( 当i和j, 其中某个数到达0的时候, 它就不再--, 只另外一个--)
     *
     * 同时,下一次开始, 计算中间结果的时候, 循环策略变化: 指针未到0那边所指数字乘以指针已到0这边的所有数字,得到结果list,循环就结束
     * 当i和j全部指向0的时候, 循环全部结束
     *
     * 最终返回result字符串.
     *
     * 同时,有一个细节: 进位. 每次加的时候要考虑进位.
     *
     * @param num1
     * @param num2
     * @return
     */
    public static String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) return "0";

        int i = num1.length() - 1;
        int j = num2.length() - 1;
        StringBuilder sb = new StringBuilder("");
        StringBuilder tmp;
        boolean isCommonPart = true;
        boolean leftOnNum1 =  false;
        boolean leftOnNum2 = false;
        while (i >= 0 && j >= 0) {
            int counti;
            int countj;
            if (num1.charAt(i) == '0') {
                i--;
                continue;
            }
            if (num2.charAt(j) == '0') {
                j--;
                continue;
            }
            List<Integer[]> list = new LinkedList<Integer[]>();
            if (i >= 0 && j >= 0 && isCommonPart) {//公共部分,两边数字都得互相乘
                //先算i位*j位
                counti = num1.length() - 1 - i;
                countj = num2.length() - 1 - j;
                char c1 = num1.charAt(i);
                char c2 = num2.charAt(j);
                Integer first = Integer.parseInt(String.valueOf(c1));
                Integer second = Integer.parseInt(String.valueOf(c2));
                Integer[] thisResult = {first * second, counti + countj};
                list.add(0, thisResult);//倒序加入,最终最前面的是最低位的结果

                //然后i位*j位后每一位, 同样, j位*i位后每一位
                for (int jj = j + 1; jj < num2.length(); jj++) {
                    countj = num2.length() - 1 - jj;
                    c2 = num2.charAt(jj);
                    first = Integer.parseInt(String.valueOf(c1));
                    second = Integer.parseInt(String.valueOf(c2));
                    if (first * second != 0) {
                        thisResult = new Integer[]{first * second, counti + countj};
                        list.add(0, thisResult);//倒序加入,最终最前面的是最低位的结果
                    }
                }

                c2 = num2.charAt(j);
                countj = num2.length() - 1 - j;
                for (int ii = i + 1; ii < num1.length(); ii++) {
                    counti = num1.length() - 1 - ii;
                    c1 = num1.charAt(ii);
                    first = Integer.parseInt(String.valueOf(c1));
                    second = Integer.parseInt(String.valueOf(c2));
                    if (first * second != 0) {
                        thisResult = new Integer[]{first * second, counti + countj};
                        list.add(0, thisResult);//倒序加入,最终最前面的是最低位的结果
                    }
                }
                if (i == 0 && j != 0) {
                    j--;
                    isCommonPart = false;
                    leftOnNum2 = true;//说明num2还有元素,而num1已到头
                } else if (j == 0 && i != 0) {
                    i--;
                    isCommonPart = false;
                    leftOnNum1 = true;//说明num1还有元素,而num2已到头
                } else {
                    i--;
                    j--;
                }
            } else {//一个串已到头, 只需要用另外一个串当前数字乘即可
                if (leftOnNum2) {//i==0, j >= 0
//                    List<Integer[]> list = new LinkedList<Integer[]>();
                    Integer first = Integer.parseInt(String.valueOf(num2.charAt(j)));
                    countj = num2.length() - 1 - j;
                    for (int ii = i; ii < num1.length(); ii++) {
                        //j当前数字,乘以i往后每个数字i
                        counti = num1.length() - 1 - ii;
                        char c1 = num1.charAt(ii);
                        Integer second = Integer.parseInt(String.valueOf(c1));
                        Integer[] thisResult;
                        if (first * second != 0) {
                            thisResult = new Integer[]{first * second, counti + countj};
                            list.add(0, thisResult);//倒序加入,最终最前面的是最低位的结果
                        }
                    }
                    j--;
                }
                if (leftOnNum1) {//j == 0, i >= 0
                    Integer first = Integer.parseInt(String.valueOf(num1.charAt(i)));
                    counti = num1.length() - 1 - i;
                    for (int jj = j; jj < num2.length(); jj++) {
                        //j当前数字,乘以i往后每个数字i
                        countj = num2.length() - 1 - jj;
                        char c1 = num2.charAt(jj);
                        Integer second = Integer.parseInt(String.valueOf(c1));
                        Integer[] thisResult;
                        if (first * second != 0) {
                            thisResult = new Integer[]{first * second, counti + countj};
                            list.add(0, thisResult);//倒序加入,最终最前面的是最低位的结果
                        }
                    }
                    i--;
                }
            }
            for (Integer[] in : list) {
                if (in[1] >= sb.toString().length()) {
                    //直接append
                    tmp = new StringBuilder();
                    tmp.append(in[0]);
                    int leftZero = in[1] - sb.length();
                    while (leftZero > 0) {
                        tmp.append("0");
                        leftZero--;
                    }
                    tmp.append(sb);
                    sb = tmp;
                } else {
                    int len = sb.toString().length();
                    String str = sb.toString().substring(0, len - in[1]);

                    //循环取str数和in[0]加, 有进位就进位继续循环,无进位break
                    String smaller = String.valueOf(in[0]);
                    int length = smaller.length();
                    if (str.length() < 10) {
                        //直接相加即可
                        long sub = Long.parseLong(str) + 0L ; //这里有可能溢出!
                        sub += in[0];
                        tmp = new StringBuilder();
                        tmp.append(sub);
                    } else {
                        //循环进位,数学的问题,还是得依靠数学来解决啊!
                        //首先,从str末尾取和in[0]等长出来和in[0]相加
                        //如果无进位, 直接拼结果即可
                        //如果有进位, 把结果保存起来, 然后循环从str刚才截取的最后位置往前每个数字+carry.如果依然进位,和append到结果里, 循环继续.
                        //最后, 必然找到了没有进位的位置, 然后str从0到此位置 + 结果, 就是最终的结果
                        tmp = new StringBuilder();
                        String number = String.valueOf(in[0]);
                        int num = in[0];
                        String firstHalf = str.substring(0, str.length() - number.length());
                        String secondHalf = str.substring(str.length() - number.length(), str.length());
                        Integer second = Integer.parseInt(secondHalf);
                        int sum = num + second;
                        if (sum < 10) {
                            //无进位,最简单的情况
                            tmp.append(firstHalf).append(sum);
                        } else {
                            StringBuilder newTmp;
                            StringBuilder curSum = new StringBuilder();
                            curSum.append(String.valueOf(sum).charAt(1));//最后一位append入curSum
                            int carry = 1;
                            while (carry != 0) {//循环进位
                                Integer first = Integer.parseInt(String.valueOf(firstHalf.charAt(firstHalf.length() - 1)));//取当前first最后一位
                                firstHalf = firstHalf.substring(0, firstHalf.length() - 1);//first更新
                                Integer newSum = first + carry;//新的和
                                if (newSum < 10) {
                                    carry = 0;
                                    tmp.append(firstHalf).append(newSum).append(curSum);
                                } else {
                                    //更新curSum
                                    newTmp = new StringBuilder();
                                    String newSumStr = String.valueOf(newSum);
                                    newTmp.append(newSumStr.charAt(1)).append(curSum);
                                    curSum = newTmp;//更新curSum
                                }
                            }//结束的时候, tmp里存的就是最终后半部分结果
                        }
                    }
                    tmp.append(sb.substring(len - in[1], len));
                    sb = tmp;
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(multiply("518239691153","1261236165919"));
        String right = "653622641096856424414607";
    }
}
