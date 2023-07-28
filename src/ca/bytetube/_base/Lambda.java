package ca.bytetube._base;

public class Lambda {
    @FunctionalInterface
    public interface Calculator{
        int calculate(int v1, int v2);
    }
    static void execute(int v1, int v2, Calculator c){
        System.out.println(c.calculate(v1, v2));
    }

    public static void main(String[] args) {

        execute(11, 22, (v1, v2) -> v1+ v2);
    }

    // another expression instead of a separate class or
    // an anonymous inner class to run a method we could use lambda
    Runnable runnable = () -> {

    };
}
