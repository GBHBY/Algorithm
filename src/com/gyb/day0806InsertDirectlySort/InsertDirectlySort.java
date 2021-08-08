package com.gyb.day0806InsertDirectlySort;

/**
 * @author gb
 * @version 1.0
 * description:直接插入排序
 * @date 2021/8/7 0:05
 */

public class InsertDirectlySort {

    public static void main(String[] args) {

        int[] arr = new int[]{3, 6, 1, 10, 5};
        sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "  ");
        }

    }


    public static void sort(int[] arr) {
        int len = arr.length;
        for (int i = 1; i < len; i++) {
//            待插入的元素
            int tem = arr[i];
            int j = i - 1;
            while (j >= 0 && tem < arr[j]) {
                arr[i] = arr[j];
                arr[j] = tem;
                j--;
                i--;
            }

        }

    }

}
