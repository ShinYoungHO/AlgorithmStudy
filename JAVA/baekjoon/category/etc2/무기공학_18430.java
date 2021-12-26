package category.ns.baekjoonSol;

import java.io.*;
import java.util.*;

public class 무기공학_18430 {
    static int n,m;
    static int[] dx = {-1, -1, 0, 0};
    static int[] dy = {-1, 0, 0, -1};
    static int ans;
    static void solve(int[][] order, int start, int total,int[][][] dp, boolean[][] check, int[][][] fix){
        int i,j;
        int x1,y1,x2,y2,x3,y3;
        if(start == order.length){
            if(ans < total) ans = total;
            return;
        }
        solve(order, start+1, total, dp, check, fix);

        i = order[start][0];
        j = order[start][1];
        for(int k = 0; k < 4; k++){
            x1=fix[start][k][0]; y1=fix[start][k][1];
            x2=fix[start][k][2]; y2=fix[start][k][3];
            x3=fix[start][k][4]; y3=fix[start][k][5];

            if(check[x1][y1]||check[x2][y2]||check[x3][y3]) continue;
            check[x1][y1] = true; check[x2][y2] = true; check[x3][y3] = true;

            solve(order, start+1, total+dp[i][j][k], dp, check, fix);

            check[x1][y1] = false; check[x2][y2] = false; check[x3][y3] = false;
        }
        if(ans < total) ans = total;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        ans = 0;
        int[][] mtx = new int[n][m];
        int[][] order = new int[(n-1)*(m-1)][2];
        int[][][] dp = new int[n][m][4];
        boolean[][] check = new boolean[n][m];
        int[][][] fix = new int[(n-1)*(m-1)][4][6];

        int idx = 0;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine()," ");
            for(int j = 0; j < m; j++){
                mtx[i][j] = Integer.parseInt(st.nextToken());
                if(i>=1&&j>=1) order[idx++] = new int[]{i, j};
            }
        }
        int x1,y1,x2,y2,x3,y3;
        for(idx = 0; idx < order.length; idx++){
            int i = order[idx][0];
            int j = order[idx][1];
            for(int k = 0; k < 4; k++){
                x1=(i+dx[k%4]); y1=(j+dy[k%4]);
                x2=(i+dx[(k+1)%4]); y2=(j+dy[(k+1)%4]);
                x3=(i+dx[(k+2)%4]); y3=(j+dy[(k+2)%4]);

                fix[idx][k] = new int[]{x1, y1, x2, y2, x3, y3};
                dp[i][j][k] = mtx[x1][y1]+mtx[x2][y2]*2+mtx[x3][y3];
            }
        }
        solve(order, 0,0, dp, check, fix);
        System.out.println(ans);
    }
}
