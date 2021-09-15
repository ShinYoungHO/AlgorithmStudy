package category.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JiuJitsu {

    static int[][] dp;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = getInteger(st);
        int M = getInteger(st);


        dp = new int[N+1][M+1];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                dp[i+1][j+1] += dp[i+1][j] + dp[i][j+1] - dp[i][j] + getInteger(st);
            }
        }

        int K = Integer.parseInt(br.readLine());
        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            int res = solve(getInteger(st)-1, getInteger(st)-1, getInteger(st), getInteger(st));
            sb.append(res);
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static int solve(int x1, int y1, int x2, int y2){
        return dp[x2][y2] + dp[x1][y1] - dp[x1][y2] - dp[x2][y1];
    }

    static Integer getInteger(StringTokenizer st){
        return Integer.parseInt(st.nextToken());
    }
}
