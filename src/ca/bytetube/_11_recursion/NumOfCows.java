package ca.bytetube._11_recursion;

public class NumOfCows {
    public static void main(String[] args) {
        System.out.println(numsOfCows0(5));
        System.out.println(numsOfCows(8));
    }
    public static int numsOfCows0(int n){
        if (n <= 4) return n;
        return numsOfCows0(n-1) + numsOfCows0(n - 4);
    }

    public static int numsOfCows(int n) {

        if (n <= 4) return n;
        int[] arr = new int[n + 1];//0

        arr[1] = 1;
        arr[2] = 2;
        arr[3] = 3;
        arr[4] = 4;
        return numsOfCows(n, arr);
    }

    private static int numsOfCows(int n, int[] arr) {
        if (arr[n] == 0) {
            arr[n] = numsOfCows(n - 1, arr) + numsOfCows(n - 4, arr);
        }

        return arr[n];
    }//装满数组的作用
}
