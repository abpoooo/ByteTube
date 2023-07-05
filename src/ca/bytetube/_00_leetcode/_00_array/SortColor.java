package ca.bytetube._00_leetcode._00_array;
//https://leetcode.com/problems/sort-colors/solutions/
public class SortColor {
 // 母题 荷兰国旗 数据 给一个分界点 分组 in place, no other space O(1)
    public void sortColors(int[] nums) {
        int less = 0;
        int more = nums.length-1;
        int pivot = 0;
        while (pivot <= more){
            if (nums[pivot] == 0){
                int temp = nums[less];
                nums[less] = nums[pivot];
                nums[pivot] = temp;
                less++;
                pivot++;
            }
            else if (nums[pivot] == 1){
                pivot++;
            }
            else if (nums[pivot] == 2){
                int temp = nums[more];
                nums[more] = nums[pivot];
                nums[pivot] = temp;
                more--;
            }
        }

    }
}
