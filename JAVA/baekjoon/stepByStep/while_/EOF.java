package stepByStep.while_;

import java.util.Scanner;

public class EOF {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while( sc.hasNext()){
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();

            System.out.println(v1+v2);
        }
    }
}
