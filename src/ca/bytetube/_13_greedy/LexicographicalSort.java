package ca.bytetube._13_greedy;

import java.util.Arrays;
import java.util.Comparator;

public class LexicographicalSort {
    public static void main(String[] args) {
        String s = lexicographicalSort(new String[]{"ba", "b"});
        System.out.println(s);
    }

    public static String lexicographicalSort(String[] strings) {
        Arrays.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o1 + o2).compareTo(o2 + o1);
            }
        });
        String res = "";
        for (int i = 0; i < strings.length; i++) {
            res += strings[i];
        }
        return res;
    }
}
