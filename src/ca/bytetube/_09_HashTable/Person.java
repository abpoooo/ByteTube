package ca.bytetube._09_HashTable;

import java.util.HashMap;
import java.util.Objects;

public class Person {
    private int age;
    private float height;
    private String name;

    public Person() {

    }

    // generate all three constructor
    public Person(int age, float height, String name) {
        this.age = age;
        this.height = height;
        this.name = name;
    }

    // generate getter and setter of all three
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    /**
     * @param
     * @return 重写equals的目的， 当发生hash冲突时， 用来比较两个keys 是否相同
     * 假设有一个index的位置上连接了多个node（bucket）
     * 这时我们先插入一个数据让他生成自己的hash code， 生成的code， 通过%运算， 得到bucket array 所对应的index
     * 假设新插入数据的key 所对应的index 和之前的数据的key所对应的index相同
     * 既然index是相同的， 我们则需要马拿到这个key和list上多个node（bucket） 所对应的key进行比较
     * 如果是同一个key， 发生覆盖， 如果不同则在 list上追加
     * <p>
     * 在jdk的HashMap中， 为什么不使用hash code来进行比较进而确定他们是不是同一个对象那？
     * 不能原因
     * 1. 不同类型的数据可能对应相同的hash code ： “Dal”-> 123; 123-> 123
     * 2. 同一个类型的数据也可能对应相同的hash code ： 【50, 17.7f, "jeff"】， 【45, 18.2f, "pony"】
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return this.age == person.age &&
                Float.compare(person.height, this.height) == 0 &&
                Objects.equals(this.name, person.name);
    }

    @Override
    public int hashCode() {
        int hashCode = Integer.hashCode(this.age);
        hashCode = hashCode * 31 + Float.hashCode(this.height);
        hashCode = hashCode * 31 + (this.name != null ? this.name.hashCode() : 0);
        return hashCode;
    }

    //generate toString of all three
    @Override
    public String toString() {
        return "Person{" +
                "age:" + age +
                ", height:" + height +
                ", name:'" + name + '\'' +
                '}';
    }

    public static void main(String[] args) {
        Person p1 = new Person(50, 17.7f, "jeff");
        Person p2 = new Person(45, 18.2f, "pony");
        Person p3 = new Person(50, 17.7f, "jeff");

        System.out.println(p1.hashCode());
        System.out.println(p2.hashCode());
        System.out.println(p3.hashCode());
        HashMap<Person, Integer> map = new HashMap<>();
        map.put(p1, 999);
        map.put(p2, 222);
        map.put(p3, 555);
        System.out.println(map.size());
        //如果只写了equals，
        // size可以是 2 或 3， 如果只写了hashCode（）size只能是 3，
        // 如果都写了size（）只能是 2
    }
}
