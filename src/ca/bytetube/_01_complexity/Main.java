package ca.bytetube._01_complexity;

import ca.bytetube._02_dynamicarray.ArrayList;

public class Main {


    public static void test1(int n) {
        if (n > 10) {
            System.out.println(" n > 10");
        }
        else if (n > 5){
            System.out.println(" n > 5");
        }
        else if (n > 2){
            System.out.println(" n > 2");
        }
        else {
            System.out.println(" n < 2");
        }
        //1//

        //1 + 4 + 4 + 4 = 13
        for (int i = 0; i < 4; i++) {
            System.out.println(" test1 ");

        }
        //14 O(1)
    }


    public static void test2(int n){
        //1 + n + n + n = 3n + 1 = O(n)
        for (int i = 0; i < n; i++) {
            System.out.println("test2");
        }
    }

    public static void test3(int n){
        //1 + n + n(3n + 1) + n = 3n^2 + 3n + 1 = O(n^2)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.println("test3");
            }
        }
    }

    public static void test4(int n) {
        /**
         * n = 16
         *
         *
         * log216 = 4
         * 1 + log2n + log2n(3n + 1) + log2n = (3n+3)log2n + 1 = O(lgn)
         * */
        for (int i = 1; i < n; i *= 2) {
            for (int j = 0; j < n; j++) {
                System.out.println("test3");
            }
        }
    }

    public static void test5(int n) {
        /**
         * n = 16
         *
         * n = 8
         * n = 4
         * n = 2
         * n = 1
         *
         * end
         *
         * log2n
         * O(lgn)
         * */
        while (((n = n /2) != 0)) {
            System.out.println("test5");
        }
    }
}
