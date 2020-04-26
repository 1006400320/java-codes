package com.linhuanjie.javase.collection;

public class ReverseArrayTest {

    public static void main(String[] args) {
        int nums [] = new int[] {1,2,3,4,5,6,7,8,9,10};

//        printArray(reverse(nums));
        reverseSelf(nums);
        printArray(nums);
    }

    public static void printArray(int [] nums){
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]+",");
        }
    }

    public static int[] reverse(int[] arrs){
        int temp[] = new int[arrs.length];
        int index = 0;
        for (int x = arrs.length-1; x >= 0 ; x--) {
            temp[index++] = arrs[x];
        }

        return temp;
    }

    public static void reverseSelf(int[] arrs){
        for (int i = 0; i < arrs.length/2; i++) {
            int temp = arrs[i];
            arrs[i] = arrs[arrs.length-i-1];
            arrs[arrs.length-i-1] = temp;
        }
    }



}
