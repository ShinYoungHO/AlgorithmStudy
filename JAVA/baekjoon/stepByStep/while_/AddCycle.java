package stepByStep.while_;

import java.util.Scanner;

public class AddCycle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Integer src = sc.nextInt();
        Integer current = src;

        int order = 0;
        while(true){
            int result = (current / 10) + (current % 10);
            current = (current % 10 * 10) + result % 10;
            order++;
            if(current == src) {
                System.out.println(order);
                break;
            }
        }
    }
}
