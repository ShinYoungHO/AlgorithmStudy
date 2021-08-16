package stepByStep.array;

import java.util.Scanner;

public class Max {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int order = 1;
        Integer result = Integer.parseInt(sc.nextLine());

        for(int i = 2; i <=9; i++){
            String value = sc.nextLine();
            Integer current = Integer.parseInt(value);
            if(current > result){
                result = current;
                order = i;
            }
        }
        System.out.println(result+"\n"+order);
        sc.close();
    }
}
