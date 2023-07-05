package ca.bytetube._01_complexity;

public class Sum {
    public static void main(String[] args) {
        int[] array = {5, 2, 10, 8, 3};

        int sum = calculateSum(array);

        System.out.println("The sum of the array elements is: " + sum);
    }
    public static int calculateSum(int[] array){
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        return sum;
    }

}
