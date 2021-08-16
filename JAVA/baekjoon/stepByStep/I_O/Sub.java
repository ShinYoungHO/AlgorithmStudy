package stepByStep.I_O;

import java.util.Scanner;

public class Sub {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String result = "";
        int v1 = sc.nextInt();
        int v2 = sc.nextInt();

        int total = v1*v2;

        while(v2 > 0){
            int d = v2 % 10;
            result += v1*d+"\n";
            v2 = v2/10;
        }
        System.out.println(result+total);
        sc.close();
    }
}
