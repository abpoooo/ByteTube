package ca.bytetube._01_complexity;

public class CharacterCounter {
    public static void main(String[] args){
        String input = "Hello World! 123";
        countCharacters(input);
    }

    public static void countCharacters(String input){
        int letterCounter = 0;
        int spaceCounter = 0;
        int numberCounter = 0;
        int otherCounter = 0;

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (Character.isLetter(ch)){
                letterCounter++;
            } else if (Character.isDigit(ch)) {
                numberCounter++;
            } else if (Character.isSpaceChar(ch)) {
                spaceCounter++;
            }else {
                otherCounter++;
            }
        }

        System.out.println("Letter Counts " + letterCounter);
        System.out.println("Number Counts " + numberCounter);
        System.out.println("Space Counts " + spaceCounter);
        System.out.println("Other Counts " + otherCounter);
    }
}
