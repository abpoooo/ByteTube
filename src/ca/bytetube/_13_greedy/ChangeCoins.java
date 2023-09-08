package ca.bytetube._13_greedy;

import java.util.Arrays;

public class ChangeCoins {
    public static void main(String[] args) {

    }

    /**
     * 41 cents
     * faces{25,10,5,1}
     * Greed strategy give priority to the coin with largest domination every time
     * 1. face = 25, cents = 41 - 25 = 16
     * 2. face = 10, cents = 16 - 10 = 6
     * 3. face = 5, cents = 6 - 5 = 1
     * 4. face = 1, cents = 1 - 1 = 0
     * @return
     */
    public static int changeCoins(int[] faces, int money){
        int coins = 0;
        Arrays.sort(faces);
        for (int i = faces.length - 1; i >= 0 ; i--) {
            if (money < faces[i]) continue;
            money -= faces[i];
            coins++;
            i--;
        }
        return coins;
    }
}
