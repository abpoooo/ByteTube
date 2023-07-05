package ca.bytetube._02_dynamicarray;

public class Main {
    public static void main(String[] args){
        test1();
    }

    public static void test1(){
        ArrayList<Integer> arrayList = new ArrayList<>(20);
        for (int i = 0; i < 10; i = i + 2) {
            arrayList.add(0,i);
        }
        arrayList.add(5, 100);
        Integer removed = arrayList.remove(2);
        System.out.println(removed);


        System.out.println(arrayList);
        System.out.println(arrayList.indexOf(4));
        System.out.println(arrayList.contains(100));
        //System.out.println(arrayList.get(3));
    }
    public static void test2(){
        ArrayList<Person> arrayList = new ArrayList<>();

    }
}
