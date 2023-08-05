package ca.bytetube._00_leetcode;

import ca.bytetube._06_tree.BinaryTree;
import ca.bytetube._08_heap.BinaryHeap;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

//Top K problem
//get first k values in a large nums of n numbers which is much larger than k
// use minHeap

public class TopK {



    public static void topK(Integer[] nums, int k){
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            int value = nums[i];
            if (minHeap.size() < k){
                minHeap.add(nums[i]);
            }else if (nums[i] > minHeap.peek()){
//                minHeap.replace(i);
                minHeap.poll();
                minHeap.add(value);
            }
        }
        Iterator<Integer> iterator= minHeap.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
//        BinaryHeap<Integer> heap = new BinaryHeap<>(new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o2 - o1;
//            }
//        });
//
//        for (int i = 0; i < nums.length; i++) {
//            if (heap.size() < k){
//                heap.add(nums[i]);
//            }else if (nums[i] > heap.get()){
//                heap.replace(i);
//            }
//        }

    }
}
