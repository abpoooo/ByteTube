package ca.bytetube._00_leetcode._06_backTracking;

import java.util.Map;

public class NQueenProblem {

    public static void main(String[] args) {
        NQueenProblem nQueenProblem = new NQueenProblem();
        nQueenProblem.totalNQueens(4);
    }

    //public variables
    private int ways = 0;
    int [] cols; //1 queen 0 no matrix[4] = 5; //表示在第五行第六列放入下一个皇后

    public int totalNQueens(int n){
        cols = new int[n];
        place(0);
        return ways;
    }

    /*
    用来摆放第row行的皇后
    row 是第row行
     */
    private void place(int row){
        if (row == cols.length){
            ways++;
            showQueens();
            return;
        }
        for (int col = 0; col <cols.length; col++) {
            if (isValid(row, col)){
                cols[row] = col;
                place(row + 1);
            }
        }
    }

    private boolean isValid(int row, int col){
        for (int i = 0; i < row; i++) {
            //列
            //之前皇后所在的列
            if (cols[i] == col) return false;
            //对角线
            if (Math.abs(col - cols[i]) == row - i) return false;
        }
        return true;
    }

    private void showQueens(){
        for (int row = 0; row < cols.length ; row++) {
            for (int col = 0; col < cols.length; col++) {
                if (cols[row] == col) System.out.println("Q ");
                else System.out.println(". ");
            }
        }
        System.out.println("==========================");
    }
}
