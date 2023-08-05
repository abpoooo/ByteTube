package ca.bytetube._08_heap;

import java.util.Iterator;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
//        test1();
        Integer[] arr = {70, 30, 34, 73, 60, 68, 43, 25, 72, 90, 57};
        topK(arr, 4);
    }

    public static void test2(){
        Integer [] arr = {70, 30, 34, 73, 60, 68, 43, 25, 72, 78, 90, 57};
//        BinaryHeap<Integer> heap = new BinaryHeap<>();
    }
    public static void test1(){
        BinaryHeap<Integer> heap = new BinaryHeap<>();
        heap.add(70);
        heap.add(30);
        heap.add(34);
        heap.add(73);
        heap.add(60);
        heap.add(68);
        heap.add(43);
        while (heap.size > 0){
            Integer remove = heap.remove();
            System.out.println(remove);
        }
        System.out.println();
    }
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
