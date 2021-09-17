package category.dp;

import java.util.Scanner;

public class StepLikeNum {
    static int DIV = 1_000_000_000;
    public static void main(String[] args) {
        int res = 0;
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] dp = new int[]{1,1,1,1,1,1,1,1,1,1};
        int[] ddp = new int[10];

        for(int i = 1; i < n; i++){
            int[] from;
            int[] to;

            if(i%2==1) {
                from = dp;
                to = ddp;
            } else {
                from = ddp;
                to = dp;
            }

            for(int j = 1; j < 9; j++){
                to[j] = (from[j-1]+from[j+1])%DIV;
            }
            to[9] = from[8];
            to[0] = from[1];
        }

        int[] target;
        if(n%2==1) target = dp;
        else target = ddp;
        for(int i = 1; i < 10; i++){
            res = res % DIV + target[i] % DIV;
        }

        System.out.println(res%DIV);
    }
}
// 길이가 N이면서 맨 앞자리가 i인 경우의 갯수는 길이가 N-1이면서 맨 앞자리가 i-1 or i+1 인 경우의 합.
