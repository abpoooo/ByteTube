package ca.bytetube._13_greedy;

public class Article {
    public int value;
    public int weight;
    public double density;

    public Article( int weight, int value) {
        this.value = value;
        this.weight = weight;
        this.density = value * 1.0 / weight;
    }

    @Override
    public String toString() {
        return "weight=" + weight +
                ", value=" + value +
                ", density=" + density +
                '}';
    }
}
