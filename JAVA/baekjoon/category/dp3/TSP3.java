package category.dp3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TSP3 {
    static int n;
    static double min;
    static double[][] mtx;
    static double[][] dp;
    static int INF = Integer.MAX_VALUE;

    static double solve(int c, int vis){
        if (dp[c][vis] != -1) return dp[c][vis];
        if (vis == (1 << n) - 1) return dp[c][vis] = mtx[c][0] == 0 ? INF : mtx[c][0];

        dp[c][vis] = INF;
        for (int i = 0; i < n; i++) {
            if ((vis & (1 << i)) == (1 << i) || mtx[c][i] == 0) continue;
            dp[c][vis] = Math.min(dp[c][vis], solve(i, vis | (1 << i)) + mtx[c][i]);
        }

        return dp[c][vis];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        mtx = new double[n][n];
        min = Double.MAX_VALUE;
        dp = new double[20][1<<16];

        int[][] dots = new int[n][2];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine()," ");
            dots[i][0] = Integer.parseInt(st.nextToken());
            dots[i][1] = Integer.parseInt(st.nextToken());
        }
        for(int i = 0; i < n; i++){
            Arrays.fill(dp[i], -1);
        }

        int x1,x2,y1,y2;
        double dst;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(i == j) continue;
                x1 = dots[i][0];
                y1 = dots[i][1];
                x2 = dots[j][0];
                y2 = dots[j][1];
                dst = Math.sqrt(Math.pow(x2-x1,2)+Math.pow(y2-y1,2));
                mtx[i][j] = mtx[j][i] = dst;
            }
        }

        System.out.println(solve(0, 1));
    }
}
