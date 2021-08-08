package com.gyb.day0808on2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gb
 * @version 1.0
 * description:
 * 给你两个非空 的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
 * <p>
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * <p>
 * 你可以假设除了数字 0 之外，这两个数都不会以 0开头。
 * 例：
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 * <p>
 * <p>
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 * @date 2021/8/8 22:38
 */

public class LinkAdd {
    public static void main(String[] args) {
        int[] arr1 = new int[]{8, 3, 2};
        int[] arr2 = new int[]{9, 2, 1};
        ListNode l1 = new ListNode();
        ListNode l2 = new ListNode();
        l1 = creatLink(l1, arr1);
        l2 = creatLink(l2, arr2);
        print(l1);
        print(l2);
        ListNode listNode = linkNodeAdd(l1, l2);
        print(listNode);

    }

    public static ListNode linkNodeAdd(ListNode l1, ListNode l2) {
        /**进位**/
        int i = 0;
        /**如果有进位，加和的个位**/
        int y = 0;
        List<Integer> result = new ArrayList<>();
        while (l1 != null || l2 != null) {
            int k = 0;
            int a = l1 == null ? 0 : l1.val;
            int b = l2 == null ? 0 : l2.val;
            k = a + b + i;
            if (k >= 10) {
                i = k / 10;
                y = k % 10;
                result.add(y);
            } else {
                i = 0;
                result.add(k);
            }
            if (l1 == null || l1.next == null) {
                l1 = null;
            } else {
                l1 = l1.next;
            }
            if (l2 == null || l2.next == null) {
                l2 = null;
            } else {
                l2 = l2.next;
            }
            if (l1 == null && l2 == null && k >= 10) {
                result.add(1);
            }

        }
        ListNode listNode = new ListNode();
        listNode = creatLink(listNode, result);


        return listNode;
    }

    /**
     * 反转
     *
     * @param node
     * @return
     */
    public static ListNode transfer(ListNode node) {
        ListNode succ = null;
        ListNode front = null;
        while (node != null) {
            succ = node.next;
            node.next = front;
            front = node;
            node = succ;
        }
        node = front;
        return node;
    }

    /**
     * @param l
     * @param arr
     * @return
     * @deprecated 头插法（无头节点）
     */
    public static ListNode creatLink(ListNode l, int[] arr) {
        int length = arr.length;
        l.val = arr[0];
        for (int i = length - 1; i > 0; i--) {
            ListNode p = new ListNode(arr[i]);
            p.next = l.next;
            l.next = p;
        }


        return l;

    }

    public static ListNode creatLink(ListNode l, List<Integer> list) {
        int length = list.size();
        l.val = list.get(0);
        for (int i = length - 1; i > 0; i--) {
            ListNode p = new ListNode(list.get(i));
            p.next = l.next;
            l.next = p;
        }


        return l;

    }

    public static void print(ListNode l) {
        while (l != null) {
            if (l.next != null) {
                System.out.print(l.val + "->");
            } else {
                System.out.println(l.val);
            }
            l = l.next;
        }
        System.out.println();
    }


}
