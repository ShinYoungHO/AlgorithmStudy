package stepByStep.sort1;

import java.util.Scanner;

public class BubbleSort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] src = new int[sc.nextInt()];
        for(int i = 0; i < src.length; i++) src[i] = sc.nextInt();

        for(int j = 0; j < src.length; j++){
            boolean isChanged = false;
            for(int i = 0; i < src.length-1; i++){
                if(src[i] > src[i+1]){
                    isChanged = true;
                    int tmp = src[i+1];
                    src[i+1] = src[i];
                    src[i] = tmp;
                }
            }

            if(!isChanged) break;
        }

        for(int i = 0; i < src.length; i++){
            System.out.println(src[i]);
        }
        sc.close();
    }
}
