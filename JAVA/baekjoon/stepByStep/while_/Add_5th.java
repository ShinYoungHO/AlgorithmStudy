package stepByStep.while_;

import java.util.Scanner;

public class Add_5th {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true){
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();

            if(v1 == 0 && v2 == 0) break;

            System.out.println(v1+v2);
        }
    }
}
