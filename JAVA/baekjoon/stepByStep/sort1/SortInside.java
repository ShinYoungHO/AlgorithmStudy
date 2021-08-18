package stepByStep.sort1;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class SortInside {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] vs = sc.nextLine().split("");

        Arrays.sort(vs, Collections.reverseOrder());
        for(int i = 0; i < vs.length; i++){
            System.out.print(vs[i]);
        }
    }
}
