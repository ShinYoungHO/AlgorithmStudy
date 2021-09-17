package category.dp;

import java.util.Scanner;

public class BinaryCard {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();
        int[] res = new int[]{1, 2};
        while(v > 1){
            int nv = res[0]%15746+res[1]%15746;
            res[0] = res[1];
            res[1] = nv%15746;
            v--;
        }
        System.out.println(res[0]);
        sc.close();
    }
}
