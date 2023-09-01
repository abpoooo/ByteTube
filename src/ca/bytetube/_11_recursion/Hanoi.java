package ca.bytetube._11_recursion;

import ca.bytetube._05_queue.doubly.LinkedList;

import java.util.LinkedHashSet;

public class Hanoi {
    public static void main(String[] args) {
        hanoi0(3, "A", "C", "B");
        LinkedHashSet<MoveInfo> moveInfos = hanoi(3, "A", "C", "B");
        System.out.println("======================");
        System.out.println(moveInfos);
    }

    public static void hanoi0(int n, String from, String to, String help){

        //When n == 1, move the plate directly from A to C
        if (n == 1) System.out.println(" move " + n + " from " + from + " to " + to);
        //When n > 1, it can be divided into 3
        else {
            //major steps
            //1. Move n-1 plates from A to B
            //2. move the plate n from A to C
            //3. move n-1 plates from B to C
            //step 1, 3 are recursive call
            hanoi0(n - 1, from, help, to);
            System.out.println("move " + n + " from " + from + " to " + to);
            hanoi0(n - 1, help, to, from);
        }
    }

    // way 1. static private LinkedHashSet<MoveInfo> set = new LinkedHashSet<>(); //成员变量
    public static LinkedHashSet<MoveInfo> hanoi(int n, String from, String to, String help){
        LinkedHashSet<MoveInfo> set = new LinkedHashSet<>();
        hanoi(n, from, to, help, set);

        return set;
    }

    //way 2. 马甲函数作为参数进行传递
    private static void hanoi(int n, String from, String to, String help, LinkedHashSet<MoveInfo> set){
        //When n == 1, move the plate directly from A to C

        if (n == 1) {
            set.add(new MoveInfo(n, from, to));
            return;
        }
        //When n > 1, it can be divided into 3
        else {
            //major steps
            //1. Move n-1 plates from A to B
            //2. move the plate n from A to C
            //3. move n-1 plates from B to C
            //step 1, 3 are recursive call
            hanoi(n - 1, from, help, to, set);
            set.add(new MoveInfo(n, from, to));
            hanoi(n - 1, help, to, from, set);
        }
    }
    private static class MoveInfo{
        int index;
        String from;
        String to;

        public MoveInfo(int index, String from, String to) {
            this.index = index;
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return "move " + index + " from " + from + " to " + to + "\n";
        }
    }
}
