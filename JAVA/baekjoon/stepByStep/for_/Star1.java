package stepByStep.for_;

import java.util.Scanner;

public class Star1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();
        for(int i = 1; i <= v; i++){
            System.out.println("*".repeat(i));
        }
        sc.close();
    }
}
