package stepByStep.recursive;

import java.util.Scanner;

public class Factorial {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();

        System.out.println(factorial(v));
    }

    public static int factorial(int v){
        if(v == 1) return 1;
        return v*factorial(v-1);
    }
}
