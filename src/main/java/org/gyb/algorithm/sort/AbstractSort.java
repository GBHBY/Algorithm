package org.gyb.algorithm.sort;

/**
 * @author GB
 * @desc 排序
 * @date 2023/11/25 21:34
 */
public abstract class AbstractSort {
    static int[] arr = new int[]{10, 5, 6, 4, 11, 88, 33, 2, 1, 5};

    /**
     * 排序算法
     *
     * @param arr 要排序的数组
     */
    void sortAndPrint(int[] arr) {
        System.out.print("排序前：");
        print(arr);
        int[] sorted = sort(arr);
        System.out.print("排序后：");
        print(sorted);
    }

    /**
     * 排序方法
     *
     * @param arr 要排序的数组
     * @return int[] 排序完成的数组
     */
    abstract int[] sort(int[] arr);


    void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (i != arr.length - 1) {
                System.out.print(arr[i] + "      ");
            } else {
                System.out.println(arr[i]);
            }
        }
    }

}
