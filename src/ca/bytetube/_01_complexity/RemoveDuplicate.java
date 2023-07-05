package ca.bytetube._01_complexity;

import java.util.Arrays;
import java.util.HashSet;

public class RemoveDuplicate {
    public static void main(String[] args) {
        int[] array = {2, 4, 6, 8, 4, 2, 10, 6};

        int[] uniqueArray = removeDuplicates(array);

        System.out.println("Array with duplicates: " + Arrays.toString(array));
        System.out.println("Array without duplicates: " + Arrays.toString(uniqueArray));
    }

    public static int[] removeDuplicates(int[] array){
        HashSet<Integer> uniqueElements = new HashSet<>();

        for (int element : array){
            uniqueElements.add(element);
        }

        int[] uniqueArray = new int[uniqueElements.size()];
        int i = 0;
        for (int element : uniqueElements){
            uniqueArray[i++] = element;
        }

        return uniqueArray;
    }
}
