package ca.bytetube._13_greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Knapsack {
    public static void main(String[] args) {
        Article[] article = new Article[]{
                new Article(35, 10),
                new Article(30, 40),
                new Article(60, 30),
                new Article(50, 50),
                new Article(40, 35),
                new Article(10, 40),
                new Article(25, 30),
        };
//        int vall = select(150, article, new Comparator<Article>() {
//            @Override
//            public int compare(Article o1, Article o2) {
//                return o2.value - o1.value;
//            }
//
//        });
//        System.out.println(vall);

//        int val2 = select(150, article, new Comparator<Article>() {
//            @Override
//            public int compare(Article o1, Article o2) {
//                return o1.weight - o2.weight;
//            }
//
//        });
//        System.out.println(val2);

        int val3 = select(150, article, new Comparator<Article>() {
            @Override
            public int compare(Article o1, Article o2) {
//                return o2.density - o1.density;
                return Double.compare(o2.density ,o1.density);
            }

        });
        System.out.println(val3);
    }

    public static int select(int W, Article[] article, Comparator<Article> comparator){
        Arrays.sort(article, comparator);
        int weight = 0;
        int value = 0;
        List<Article> list = new LinkedList<>();
        for (int i = 0; i < article.length; i++) {
            int newWeight = article[i].weight + weight;
            if (newWeight <= W){
                weight = newWeight;
                value += article[i].value;
                list.add(article[i]);
            }
        }
        System.out.println(list);
        return value;
    }

}
