package ca.bytetube._13_greedy;

import java.util.Arrays;

public class Pirates {
    public static void main(String[] args) {
        int maxAntique = maxAntique(30,new int[]{3,5,4,10,7,14,2,11});
        System.out.println("maxAnique " + maxAntique);
    }

    public static int maxAntique(int capacity, int [] weights){
        Arrays.sort(weights);
        int count = 0;
        int weight = 0;
        for (int i = 0; i < weights.length && weight < capacity; i++) {
            int newWeight = weights[i] + weight;
            if (newWeight <= capacity){
                weight = newWeight;
                count++;
                System.out.println(weights[i]);
            }
        }

        return count;
    }
}
