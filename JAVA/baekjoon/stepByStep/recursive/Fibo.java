package stepByStep.recursive;

import java.util.Scanner;

public class Fibo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int v = sc.nextInt();
        System.out.println(fibo(v));
    }

    public static int fibo(int v){
        if(v == 0) return 0;
        if(v == 1) return 1;
        return fibo(v-1)+fibo(v-2);
    }
}
