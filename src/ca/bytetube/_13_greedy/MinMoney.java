package ca.bytetube._13_greedy;

import java.util.Arrays;

public class MinMoney {
    public static void main(String[] args) {

    }

    public static int minMoney(int[] arr){
        Arrays.sort(arr);

        int count = arr.length;
        int cost = 0;
        for (int i = count - 1; i > 0 ; i--) {
            int subCost = sum(arr,i);

            cost += subCost;
        }

        return cost;
    }

    private static int sum(int[] arr, int length){
        int res = 0;
        for (int i = length; i >= 0 ; i--) {
            res += arr[i];
        }
        return res;
    }
}
