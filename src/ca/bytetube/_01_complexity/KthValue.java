package ca.bytetube._01_complexity;

import java.util.Arrays;

public class KthValue {
    public static void main(String[] args) {
        int[] array = {5, 2, 10, 8, 3};
        int k = 3;

        int kthMin = findKthMinimum(array, k);

        System.out.println("The " + k + "th minimum value in the array is: " + kthMin);
    }

    public static int findKthMinimum(int[] array, int k){
        Arrays.sort(array);

        if (k > 0 && k <= array.length){
            return array[k -1 ];
        }
        return Integer.MIN_VALUE;
    }
}
