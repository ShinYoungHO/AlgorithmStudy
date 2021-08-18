package stepByStep.bruteforce;

import java.util.Scanner;

public class Decomposition {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        int target = Integer.parseInt(input);
        int min = 1_000_001;

        for(int i = 0; i < input.length()*9; i++){
            int v = target - i;
            int sum = 0;
            while(v > 0){
                sum += v%10;
                v /= 10;
            }
            if(sum == i && min > target - sum){
                min = target-sum;
            }
        }
        if(min == 1_000_001){
            System.out.println(0);
            return;
        }
        System.out.println(min);
        sc.close();
    }
}
