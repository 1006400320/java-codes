package com.linhuanjie.javase;

public class BubbleSortTest {

    public static void main(String[] args) {
        int nums [] = new int[] {1,2,3,4,5,6,7,8,9};

//        sortArray(nums);
//        printArray(nums);


    }

    public static void printArray(int [] nums){
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]+",");
        }
    }

    public static void sortArray(int [] nums){
        int temp = 0;
        for (int x = 0; x < nums.length; x++) {
            for (int y = 0; y < nums.length-1; y++) {
                if(nums[y]<nums[y+1]){
                    temp = nums[y+1];
                    nums[y+1] = nums [y];
                    nums [y] = temp;
                }

            }

        }
    }

}
