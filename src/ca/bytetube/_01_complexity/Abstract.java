package ca.bytetube._01_complexity;

public class Abstract {
    public abstract class Shape {
        protected double area;
        protected double grith;
        public double getArea(){
            return  area;
        }
        public double getGrith() {
            return  grith;
        }
        public void show() {
            calculate();
            System.out.println(area + "_" + grith);
        }
        protected abstract void calculate();
    }
}
