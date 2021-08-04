package com.gyb.day0801QuikSort;

import java.util.Arrays;

/**
 * @author gb
 * @version 1.0
 * description:快速排序
 * @date 2021/8/2 2:03
 */


public class QuickSort {
    public static void main(String[] args) {

        int[] arr = new int[]{2, 5, 1, 67, 3, 10, 0};
        System.out.print("排序前：");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");

        }
        sort(arr, 0, arr.length - 1);

        System.out.print("排序后：");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    private static void sort(int[] arr, int left, int right) {
        System.out.println("a");
        if (right < 0 || left > right) {
            return;
        }
        int mark = arr[left];
        int i = left;
        int y = right;
        while (i != y) {

            while (mark <= arr[y] && i < y) {
                y--;
            }
            while (mark >= arr[i] && i < y) {
                i++;
            }

            if (i < y) {
                int temp = arr[y];
                arr[y] = arr[i];
                arr[i] = temp;
            }
        }
        arr[left] = arr[i ];
        arr[i ] = mark;

        sort(arr, left, i - 1);
        sort(arr, i + 1, right);


    }
}


















