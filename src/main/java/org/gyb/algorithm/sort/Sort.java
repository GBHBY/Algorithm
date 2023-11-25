package org.gyb.algorithm.sort;

/**
 * @author GB
 * @desc 排序
 * @date 2023/11/25 21:34
 */
public interface Sort {
    int[] arr = new int[]{10, 5, 6, 4, 11, 88, 33, 2, 1, 5};
    /**
     * 排序算法
     *
     * @param arr 要排序的数组
     */
    void sort(int[] arr);

    static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (i != arr.length - 1) {
                System.out.print(arr[i] + "      ");
            } else {
                System.out.println(arr[i]);
            }
        }
    }

}
