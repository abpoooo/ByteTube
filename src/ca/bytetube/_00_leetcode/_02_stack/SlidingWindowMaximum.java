package ca.bytetube._00_leetcode._02_stack;


import java.util.Deque;
import java.util.LinkedList;

//https://leetcode.com/problems/sliding-window-maximum/
public class SlidingWindowMaximum {
    // 1. If nums[1] >= nums[tail], removing tail until nums[tail] > nums[i]
    // 2. Add index i into tail of deque
    // 3. if w >= 0 remove expired head of dequeue update window max value at W position to nums[head]
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k == 1) return nums;
        int[] maxes = new int[nums.length - k + 1];
        Deque<Integer> deque = new LinkedList<>();

        for (int i = 0; i < nums.length; i++) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) deque.pollLast();
            deque.addLast(i);
            int w = i - k + 1;
            if (w < 0) continue;;
            // codes can be running here means legal window is true;

            //delete outdated element in deque
            if (w > deque.peekFirst()) deque.removeFirst();
            maxes[w] = nums[deque.peekFirst()];


        }

        return maxes;
    }
}
