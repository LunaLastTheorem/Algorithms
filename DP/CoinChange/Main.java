package DP.CoinChange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static int coinExchange(int target, int[] coins) {
        int[] ways = new int[target + 1];
        ways[0] = 1;

        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j < ways.length; j++) {
                System.out.println("adding ways to make " + j + " from ways to make:" + j + " - " + coins[i ]+ " = " + (j - coins[i]) + " which is "
                        + ways[j - coins[i]] + " from " + ways[j]);
                ways[j] += ways[j - coins[i]];
            }
            System.err.println(Arrays.toString(ways));
        }
        System.err.println(Arrays.toString(ways));

        return ways[target];
    }

    public static void main(String[] args) {
        int[] coins = { 1, 2 };

        System.err.println(coinExchange(3, coins));
    }
}
