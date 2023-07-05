package ca.bytetube._01_complexity;

public class MaxArray {
    public static void main(String[] args) {
        int[] array = {5, 2, 10, 8, 3};

        int maxValue = array[0];

        for (int i = 1; i < array.length ; i++) {
            if (array[i] > maxValue){
                maxValue = array[i];
            }
        }

        System.out.println("The Max is: " + maxValue);
    }
}
