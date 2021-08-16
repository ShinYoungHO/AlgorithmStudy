package stepByStep.If;

import java.util.Scanner;

public class Compare {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v1 = sc.nextInt();
        int v2 = sc.nextInt();

        if(v1 > v2) {
            System.out.println(">");
        } else if(v1 < v2) {
            System.out.println("<");
        } else{
            System.out.println("==");
        }
    }
}
