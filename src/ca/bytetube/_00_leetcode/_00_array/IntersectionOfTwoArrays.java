package ca.bytetube._00_leetcode._00_array;

import java.util.ArrayList;

//https://leetcode.com/problems/intersection-of-two-arrays/description/
//We will make an extra array "temp[]" of size equal to range of nums array (given in constraint).
//Now we will set nums1[i] position in temp[] array as 1
// (which means a number is occurred in first array).

//Next, we will iterate over second array i.e. nums2[]
// with a condition that checks whether the number already occurred in nums1[]
// or not with the help of temp[] array.


//If condition becomes true we will make temp[nums2[i]] as 2 i.e. it occured twice and is the intersection between nums1 & nums2


// Also, a counter variable will store the number of intersection elements
//Make an ans[] array of size of counter variable to store the intersection variable as index of temp;
//        return the ans[] array.
public class IntersectionOfTwoArrays {
    public int[] intersection(int[] nums1, int[] nums2) {
        int [] temp = new int[1001];
        int count = 0;
        for (int i = 0; i < nums1.length; i++) {
            temp[nums1[i]] = 1;
        }
        for (int i = 0; i < nums2.length; i++) {
            if (temp[nums2[i]] == 1){
                temp[nums2[i]] = 2;
                count++;
            }
        }
        int [] ans = new int[count];
        for (int i = 0, j=0; i <temp.length && j< count; i++) {
            if (temp[i] == 2){
                ans[j] = i;
                j++;
            }
        }
        return ans;
    }
}
