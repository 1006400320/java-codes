package com.linhuanjie.javase;

public class ReverseArrayTest2 {
    public static void main(String[] args) {
        int nums [][] = new int [][]{
                {1,2,3},{4,5,6},{7,8,9}
        };
        printArray(nums);
        reverse(nums);
        printArray(nums);
        System.out.println("==========================");
        System.out.println(7/2);
    }

    public static void reverse(int [][] arr){
        for (int x=0;x<arr.length;x++){
            for (int y = x; y < arr[x].length; y++) {
                if(x != y) {
                    int temp = arr[x][y];
                    arr[x][y] = arr[y][x];
                    arr[y][x] = temp;
                }
            }
        }
    }

    public static void printArray(int[][] arr){
        for (int x=0;x<arr.length;x++){
            for (int y=0;y<arr[x].length;y++){
                System.out.print(arr[x][y]+"、");
            }
            System.out.println();
        }
    }
}
