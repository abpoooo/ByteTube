package ca.bytetube._base;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class homework {
        //psvm
        public static void main(String[] args) {
            List<Person> boxes = new ArrayList<>();
            boxes.add(new Person(10.5));
            boxes.add(new Person(8.2));
            boxes.add(new Person(12.7));

            System.out.println("排序前:");
            for (Person box : boxes) {
                System.out.println(box.getWeight());
            }

            Collections.sort(boxes);

            System.out.println("\n排序后:");
            for (Person person : boxes) {
                System.out.println(person.getWeight());
            }
        }
}


    class Person implements Comparable<Person>{
        private double weight;

        public Person(double weight){
            this.weight = weight;
        }

        public double getWeight(){
            return weight;
        }

        @Override
        public int compareTo(Person person) {
            return Double.compare(person.weight, this.weight);
        }
    }


