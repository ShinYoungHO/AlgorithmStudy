package stepByStep.greedy;

import java.util.Scanner;

public class LostBracket {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] inputArr = input.split("-");
        int result = 0;
        for(int i = 0; i < inputArr.length; i++){
            String[] elem = inputArr[i].split("\\W");
            if(i == 0) result += getTotal(elem);
            else result -= getTotal(elem);
        }
        System.out.println(result);
    }
    public static int getTotal(String[] arr){
        int result = 0;
        for(int i = 0; i < arr.length; i++){
            result += Integer.parseInt(arr[i]);
        }
        return result;
    }
}
