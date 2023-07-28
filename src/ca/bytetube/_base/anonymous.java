package ca.bytetube._base;

public class anonymous {
    public interface Eatable{
        String name();
        int energy();
    }

    public class Person{
        public void eat(Eatable e){
            System.out.println("eat -" + e.name() + "_" + e.energy());
        }
    }

}
