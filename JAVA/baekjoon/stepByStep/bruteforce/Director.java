package stepByStep.bruteforce;

import java.util.Scanner;

public class Director {
    public static void main(String[] args) {
        while(true){
            main2(new String[]{});
        }
    }
    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();

        int i = 0;

        while (v > 0){
            i++;
            int target = i;
            int count = 0;
            boolean isValid = false;

            while(target > 0){
                int t = target%10;
                if(t == 6) count++;
                else count = 0;

                if(count == 3){
                    isValid = true;
                    break;
                }
                target/=10;
            }
            if(isValid) v--;
        }
        System.out.println(i);
    }
}
/**
 x666x
 xx666
 666xx
 */