package com.gyb.day0807NthFibonacci;

import java.util.LinkedList;
import java.util.List;

/**
 * @author gb
 * @version 1.0
 * description:泰波那契序列Tn定义如下：
 * <p>
 * T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0的条件下 Tn+3 = Tn + Tn+1 + Tn+2
 * <p>
 * 给你整数n，请返回第 n 个泰波那契数Tn 的值。
 * 示例：
 * 输入：n = 4
 * 输出：4
 * 解释：
 * T_3 = 0 + 1 + 1 = 2
 * T_4 = 1 + 1 + 2 = 4
 */

public class NthFibonacci {
    public static void main(String[] args) {
        System.out.println(getNthFibonacci(25));
        System.out.println(getNthFibonacci2(25));

    }

    public static int getNthFibonacci(int n) {
        List<Integer> fi = new LinkedList<>();
        fi.add(0);
        fi.add(1);
        fi.add(1);
        while ((fi.size() - 1) < n) {
            int i = fi.size();
            fi.add(fi.get(i - 1) + fi.get(i - 2) + fi.get(i - 3));
        }


        return fi.get(n);

    }

    public static int getNthFibonacci2(int n) {
        if (n == 0) {
            return 0;
        }
        if (n <= 2) {
            return 1;
        }
        int q = 0;
        int p = 0;
        int r = 1;
        int s = 1;
        for (int i = 3; i <= n; i++) {
            q = p;
            p = r;
            r = s;
            s = q + p + r;
        }

        return s;
    }
}
