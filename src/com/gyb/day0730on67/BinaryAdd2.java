package com.gyb.day0730on67;

/**
 * @author gb
 * @version 1.0
 * description:
 * @date 2021/8/1 0:34
 */

public class BinaryAdd2 {
    public static void main(String[] args) {
        System.out.println(add("1101", "1101"));

    }

    private static String add(String s, String s1) {
        int i = Integer.parseInt(s, 2);
        int i1 = Integer.parseInt(s1, 2);
        int count = i + i1;
        String i2 = Integer.toString(count, 2);

        return i2;
    }
}
