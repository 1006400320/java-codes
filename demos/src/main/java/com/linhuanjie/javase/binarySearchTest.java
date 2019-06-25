package com.linhuanjie.javase;

/**
 * 二分法查找
 */
public class binarySearchTest {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};

        binarySearch(nums, 0, nums.length, 2,0);
    }

    public static int binarySearch(int[] arr, int from, int to, int key,int index) {
        int mid = from/2 + to/2;
        if (from < to) {
            if (arr[mid] == key) {
                System.out.println("恭喜你，找到数字：" + key + " 了。查找次数：" + index);
            } else if (key < arr[mid]) {
                return binarySearch(arr, from, mid - 1, key,index++);
            } else if (key > arr[mid]) {
                return binarySearch(arr, mid + 1, to, key,index++);
            }
        }
        return -1;
    }


}
