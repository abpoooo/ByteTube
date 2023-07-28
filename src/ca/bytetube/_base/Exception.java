package ca.bytetube._base;

public class Exception {
    public class AgeException extends RuntimeException{
        public AgeException(String message){
            super(message);
        }
    }

    public class MyClass{
        public void checkAge(int age) throws AgeException{
            if (age < 0 || age > 200) {
                throw new AgeException("age out of range");
            }
        }
    }
}
