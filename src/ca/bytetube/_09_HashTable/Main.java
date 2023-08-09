package ca.bytetube._09_HashTable;

public class Main {
    public static void main(String[] args) {
        float f = 10.6f;
        String s = "jack";
        System.out.println(s.hashCode());
        System.out.println(hash_code(s));
    }

    public static int hash_code(String s){
        int hashCode = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            hashCode = (hashCode<<5) - hashCode + ch;//better than hashCode*31 + ch
        }
        return hashCode;
    }
}
