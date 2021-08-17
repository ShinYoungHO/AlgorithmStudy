package stepByStep.math;

import java.util.Scanner;

public class HoneyComb {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();
        if(v == 1) {
            System.out.println(1);
            return;
        }
        int i = 1;
        while(3*i*(i+1)+1<v){
            i++;
        }
        System.out.println(i+1);
    }
}
// 2~7
// 8~12