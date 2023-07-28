package ca.bytetube._base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Comparator1 {

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Alice Wang", 20, 20.1));
        students.add(new Student("Bob Li", 20, 19.1));
        students.add(new Student("Charlie Yang", 21, 12.2));
        students.add(new Student("Charlie Huang", 21, 15.2));
        students.add(new Student("Charlie Zhang", 21, 30.2));

        System.out.println("排序前:");
        for (Student student : students) {
            System.out.println(student);
        }

        Comparator<Student> ageNameComparator = new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                int ageComparison = Integer.compare(o1.getAge(), o2.getAge());
                if(ageComparison != 0) return ageComparison;
                return o1.getName('\n').compareTo(o2.getName('\n'));
            }
        };
        Collections.sort(students, ageNameComparator);
        System.out.println("\n排序后:");
        for (Student student : students) {
            System.out.println(student);
        }
    }

}
class Student{

    private double weight;
    private String name;
    private int age;

    public Student(String name, int age, double weight) {
        this.name = name;
        this.age = age;
        this.weight = weight;
    }

    public String getName(char c){
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getWeight(){
        return weight;
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", age=" + age + "]";
    }
}

