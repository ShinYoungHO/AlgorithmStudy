package category.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Wine {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] dp = new int[n+1][3];
        for(int i = 1; i < dp.length; i++){
            int v = Integer.parseInt(br.readLine());
            dp[i][0] = Math.max(dp[i-1][0], Math.max(dp[i-1][1], dp[i-1][2]));
            dp[i][1] = dp[i-1][0]+v;
            dp[i][2] = dp[i-1][1]+v;
            System.out.println(Arrays.toString(dp[i]));
        }
        System.out.println(Math.max(dp[n][0], Math.max(dp[n][1], dp[n][2])));
    }
}
// dp[i][j] j 인덱스 :: 연속으로 j 번 먹은 상태의 최대 와인 량.
// 0번은 해당 와인 마시지 않는 경우이고, 최대값은 이전 최댓값들의 최댓값
// 1번은 0번인 경우에서 해당 와인을 마시는 경우이므로 1가지
// 2번도 1번과 동일