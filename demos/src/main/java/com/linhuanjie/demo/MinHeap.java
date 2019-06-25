package com.linhuanjie.demo;

/**
 * java实现最小堆
 * 原文：https://www.cnblogs.com/xp12/p/4264773.html
 *
 * @author: linhuanjie
 * @description:
 * @createTime : 2018-11-29 10:17
 * @email: lhuanjie@qq.com
 */


public class MinHeap {
    private int[] data;

    public MinHeap(int[] data) {
        this.data = data;
    }

    public void createHeap() {
        for (int i = (data.length) / 2 - 1; i >= 0; i--) {
            heapIfy(i);
        }
    }

    public void heapIfy(int value) {
        int lchild = left(value);
        int rchild = right(value);
        int smallest = value;
        if (lchild < data.length && data[lchild] < data[value])
            smallest = lchild;
        if (rchild < data.length && data[rchild] < data[smallest])
            smallest = rchild;
        if (value == smallest)
            return;
        swap(value, smallest);
        heapIfy(smallest);
    }

    public int left(int value) {
        return ((value + 1) << 1) - 1;
    }

    public int right(int value) {
        return (value + 1) << 1;
    }

    public void swap(int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    public void setRoot(int value) {
        data[0] = value;
        heapIfy(0);
    }

    public static void main(String[] args) {
        int[] value = {10, 100, 12, 73, 45, 32, 11, 23, 55, 34, 90, 21};
        MinHeap heap = new MinHeap(value);
        heap.createHeap();
        for (int i = 0; i < value.length; i++) {
            System.out.print(heap.data[i] + " ");
        }
        System.out.println();
        heap.setRoot(64);
        for (int i = 0; i < value.length; i++) {
            System.out.print(heap.data[i] + " ");
        }
        System.out.println();
    }
}