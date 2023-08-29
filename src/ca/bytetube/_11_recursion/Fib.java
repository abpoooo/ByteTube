package ca.bytetube._11_recursion;

public class Fib {
    public int fib0(int n) {
        //fib(n) = fib(n - 1) + fib(n - 2) 递推式模型
        //weakness 大量数据计算
        if (n < 0 || n > 30) {
            throw new RuntimeException("Illegal Input");
        }
        if (n <= 1) return n;
        return fib0(n - 1) + fib0(n - 2);
    }

    //container array
    public int fib1(int n) {
        if (n < 0 || n > 30) {
            throw new RuntimeException("Illegal Input");
        }

        if (n <= 1) return n;
        int[] arr = new int[n + 1];//0

        arr[1] = 1;
        arr[2] = 1;
        return fib1(n, arr);
    }

    private int fib1(int n, int[] arr) {
        if (arr[n] == 0) {
            arr[n] = fib1(n - 1, arr) + fib1(n - 2, arr);
        }

        return arr[n];
    }//装满数组的作用

    public int fib2(int n) {
        //fib(n) = fib(n - 1) + fib(n - 2) 递推式模型
        //weakness 大量数据计算
        if (n < 0 || n > 30) {
            throw new RuntimeException("Illegal Input");
        }
        if (n <= 1) return n;
        int[] arr = new int[2];
        arr[0] = 1;
        arr[1] = 1;
        for (int i = 3; i <= n; i++) {
            arr[i % 2] = arr[(i - 1) % 2] + arr[(i - 2) % 2];
        }
        return arr[n % 2];
    }


    public int fib3(int n) {
        //fib(n) = fib(n - 1) + fib(n - 2) 递推式模型
        //weakness 大量数据计算
        if (n < 0 || n > 30) {
            throw new RuntimeException("Illegal Input");
        }
        if (n <= 1) return n;
        int[] arr = new int[2];
        arr[0] = 1;
        arr[1] = 1;
        for (int i = 3; i <= n; i++) {
            arr[i & 1] = arr[(i - 1) & 1] + arr[(i - 2) & 1]; //位移符号
        }
        return arr[n & 1];
    }

    public static int fib4(int n) {
        if (n < 0 || n > 30) {
            throw new RuntimeException("illegal input!");
        }
        if (n <= 1) return n;
        int first = 0;
        int second = 1;
        for (int i = 0; i < n-1; i++) {
            int sum = first + second;
            first = second;
            second = sum;
        }

        return second;
    }

}
