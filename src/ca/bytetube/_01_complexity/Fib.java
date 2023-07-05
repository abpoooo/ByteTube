package ca.bytetube._01_complexity;


public class Fib {
    public static void main(String[] args) {
        Timer.test("fib0", () -> fib0(30));

        Timer.test("fib1", () -> fib1(30));

        Timer.test("fib2", () -> fib2(30));
    }
    /**
     * https://leetcode.com/problems/fibonacci-number/
     * n = 5
     * fib(5) = fib(4) + fib(3) =  3 + ...execute fib(3) ... execute fib(2) = 5
     * ...
     * fib(4) = 2 + 1 = 3
     * fib(3) = 1 + 1 = 2
     * fib(2) = fib(1) + fib(0) = 1 + 0 = 1
     * */
    public static int fib0(int n) {
        if (n < 0 || n > 30) {
            throw new RuntimeException("illegal input!");
        }
        if (n <=1) return 0;
        return fib0(n-1) + fib0(n-2);
    }

    public static int fib1(int n) {
        if (n < 0 || n > 30) {
            throw new RuntimeException("illegal input!");
        }
        if (n <= 1) return n;
        int[] arr = new int[n + 1]; //element = 0
        arr[1] = 1;
        arr[2] = 1;

        return fib1(n, arr);
    }
    /** majia hanshu tongming*/
    private static int fib1(int n, int[] arr) {
        if (arr[n] == 0) {
            arr[n] = fib1(n-1, arr) + fib1(n-2, arr);
        }
        return arr[n];
    }

    public static int fib2(int n) {
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
