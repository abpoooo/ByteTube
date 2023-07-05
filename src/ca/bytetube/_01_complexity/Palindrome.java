package ca.bytetube._01_complexity;

public class Palindrome {
    //palindrome is from start and end are the same in an array or a number
    public static void main(String[] args) {
        int number = 12321;

        boolean isPalindrome = isPalindromeNumber(number);

        if (isPalindrome) {
            System.out.println(number + " is a palindrome number.");
        } else {
            System.out.println(number + " is not a palindrome number.");
        }
    }

    public static boolean isPalindromeNumber(int number){
        String numStr = String.valueOf(number);
        int left = 0;
        int right = numStr.length() - 1;

        while (left < right){
            if (numStr.charAt(left) != numStr.charAt(right)){
                return  false;
            }
            left++;
            right--;
        }
        return true;
    }
}
