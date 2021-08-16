package stepByStep.array;

import java.util.Scanner;

public class NumOfNums {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Integer subRes = 1;
        int[] result = {0,0,0,0,0,0,0,0,0,0};
        for(int i = 0; i < 3; i++){
            subRes *= Integer.parseInt(sc.nextLine());
        }

        while(subRes > 0){
            int digit = subRes % 10;
            result[digit] += 1;
            subRes /= 10;
        }

        for(int i = 0; i < 10; i++){
            System.out.println(result[i]);
        }
    }
}
