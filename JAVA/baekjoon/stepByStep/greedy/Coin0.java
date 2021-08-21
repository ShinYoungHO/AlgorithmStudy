package stepByStep.greedy;

import java.util.Arrays;
import java.util.Scanner;

public class Coin0 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int result = 0;
        int[] coins = new int[n];
        for(int i = 0; i < n; i++) {
            coins[i] = sc.nextInt();
        }

        Arrays.sort(coins);
        for(int i = n-1; i >= 0; i--){
            while(k >= coins[i]){
                result++;
                k-=coins[i];
            }
        }

        System.out.println(result);
    }
}
