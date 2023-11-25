package org.gyb.algorithm.sort;

/**
 * @author GB
 * @desc 计数排序
 * @date 2023/11/25 21:34
 */
public class CountSort extends Sort {
    public static void main(String[] args) {
        Sort sort = new CountSort();
        sort.sortAndPrint(arr);
    }

    @Override
    public int[] sort(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if(arr[i] > max){
                max = arr[i];
            }
        }
        int[] temp = new int[max + 1];
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            temp[arr[i]] = temp[arr[i]] + 1;
        }
        int y = 0;
        for (int i = 0; i < temp.length; i++) {
            while (temp[i] != 0){
                result[y] = i;
                y++;
                temp[i] --;
            }
        }
        return result;
    }
}
