package ca.bytetube._01_complexity;

import java.util.Arrays;

public class ReverseArray {
    public static void main(String[] args){
        int[] array = {1,2,3,4,5};
        System.out.println("Original array " + Arrays.toString(array));
        revereArray(array);
        System.out.println("Reversed array " + Arrays.toString(array));
    }

//    public static void revereArray(int[] array){
//        int start = 0;
//        int end = array.length - 1;
//
//        while (start < end){
//            int temp = array[start];
//            array[start] = array[end];
//            array[end] = temp;
//
//            start++;
//            end--;
//        }
//    }
    public static void revereArray(int[] array){
        int start = 0;
        int end = array.length -1;

        while (start < end){
            int temp = array[start];
            array[start] = array[end];
            array[end] = temp;

            start++;
            end--;
        }
    }
}
