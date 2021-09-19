package category.dp2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class App {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st1 = new StringTokenizer(br.readLine(), " ");
        StringTokenizer st2;
        int n = Integer.parseInt(st1.nextToken());
        int k = Integer.parseInt(st1.nextToken());
        int res = 0;

        int[] An = new int[n];
        int[] Mn = new int[n];
        int[][] dp;

        st1 = new StringTokenizer(br.readLine(), " ");
        st2 = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < n; i++){
            int v = Integer.parseInt(st1.nextToken());
            An[i] = v;
            res += v;
            Mn[i] = Integer.parseInt(st2.nextToken());
        }
        dp = new int[n+1][res+1];
        for(int i = 0; i < n; i++){
            for(int j = 1; j <= res; j++){
                if(j-Mn[i] >= 0){
                    dp[i+1][j] = Math.max(dp[i][j], dp[i][j-Mn[i]]+An[i]);
                } else {
                    dp[i+1][j] = dp[i][j];
                }
            }

        }

        for(int i = 1; i <= res; i++){
            if(dp[n][i] >= k){
                res = i;
                break;
            }
        }
        System.out.println(res);
    }
}
// 비용이 dp[i] 의 idx 일때 최대로 커버할 수 있는 메모리 값 메모.
// 최대 커버 메모리 값이 타켓메모리 값보다 큰 인덱스 출력
