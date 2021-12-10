package category.ns.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 행렬곱셈순서_11049 {
    static int n;
    static int INF;

    static void solve(int from, int to, int[][] arr, int[][] dp){
        for(int i = from; i < to; i++){
            int ans = dp[from][i] + dp[i+1][to] + arr[from][0] * arr[to][1] * arr[i][1];
            dp[from][to] = Math.min(ans, dp[from][to]);
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        INF  = Integer.MAX_VALUE;
        int[][] arr = new int[n][2];
        int[][] dp = new int[n][n];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine()," ");
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(i == j) continue;
                dp[i][j] = INF;
            }
        }

        for(int i = 1; i < n; i++){
            for(int j = 0; j+i < n; j++){
                solve(j, j+i, arr, dp);
            }
        }

        System.out.println(dp[0][n-1]);
    }

}


/*
7
5 3
3 2
2 6
6 3
3 8
8 2
2 3
 */