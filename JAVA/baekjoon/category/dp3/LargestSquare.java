package category.dp3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class LargestSquare {
    static int r,c;
    static int INF = Integer.MAX_VALUE;
    static int[] dx = {0, 1, 1};
    static int[] dy = {1, 0, 1};
    static int[][] dp;
    static int max;
    static int solve(int[][] mtx, int x1, int y1){
        if(x1 == r-1 || y1 == c-1) {
            if(max < mtx[x1][y1]) max = mtx[x1][y1];
            return dp[x1][y1] = mtx[x1][y1];
        }
        if(dp[x1][y1] != INF) return dp[x1][y1];

        int v = INF;
        for(int i = 0; i < 3; i++){
            int k = solve(mtx, x1+dx[i], y1+dy[i]);
            if(k < v) v = k;
        }

        if(mtx[x1][y1] == 0) dp[x1][y1] = 0;
        else dp[x1][y1] = v+1;

        if(max < dp[x1][y1]) max = dp[x1][y1];

        return dp[x1][y1];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        int[][] mtx = new int[r][c];
        dp = new int[r][c];
        max = 0;
        for(int i = 0; i < r; i++){
            String s = br.readLine();
            for(int j = 0; j < c; j++){
                mtx[i][j] = s.charAt(j)-'0';
            }
            Arrays.fill(dp[i], INF);
        }

        solve(mtx, 0, 0);
        System.out.println(max*max);
    }
}
/*
4 4
0100
0111
1110
0010
 */