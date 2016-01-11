package com.leetcode.number204;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Count the number of prime numbers less than a non-negative number, n.
 * Created by Peixin Lu on 15/12/2.
 */
public class Solution {

    /**
     * 关键在于判断质数的方法
     * 此方法正确,但是不够快
     * @param n
     * @return
     */
    public static int countPrimes(int n) {
        int count = 0;
        Set<Integer> set = new LinkedHashSet<>();
        for (int i = 2; i < n; i++) {
            if (isPrime(i, set)) count++;
        }
        return count;
    }

    private static boolean isPrime(int n, Set<Integer> primeSet) {
        for (Integer i : primeSet){
            if (i <= n / 2) {
                if (n % i == 0) return false;
            } else {
                break;
            }
        }
        primeSet.add(n);
        return true;
    }


    /**
     * 本质是动态规划的思想, 用一个数组记录1-n之间所有的质数
     * 没找到一个质数, 把后面所有可以质数分解得到它的数全部置为非质数
     * beat 86.18%
     * @param n
     * @return
     */
    public static int countPrimesFasters(int n) {
        boolean[] isPrime  = new boolean[n];//// isComposite[i]: If i is a composite number
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (!isPrime[i]) {
                count++;//首先,i是一个质数
                //开始标记所有由i作为质因子组成的数
                if (i < Math.sqrt(n)) {
                    for (int j = i * i; j < n; j += i) {
                        isPrime[j] = true;
                    }
                }
            }
        }
        return count;
    }


    public static void main(String[] args) {
        System.out.println(countPrimesFasters(499979));
    }
}
