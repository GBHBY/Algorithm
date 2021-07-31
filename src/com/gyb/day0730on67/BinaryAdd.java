package com.gyb.day0730on67;

/**
 * @author gb
 * @version 1.0
 * description: 将两个二进制字符串进行相加
 * @date 2021/7/30 20:48
 */

public class BinaryAdd {

    public static void main(String[] args) {
        String add = add("1101", "1110");

    }

    public static String add(String str1, String str2) {
        int i = str1.length() - 1;
        int y = str1.length() - 1;
        int count = 0;
        StringBuilder builder = new StringBuilder();
        while (i >= 0 || y >= 0 || count != 0) {
            if (i >= 0) {
                count += str1.charAt(i--) - '0';
            }
            if (y >= 0) {
                count += str2.charAt(y--) - '0';
            }
            builder.append(count % 2);
            count /= 2;
        }
        return builder.reverse().toString();
    }
}
