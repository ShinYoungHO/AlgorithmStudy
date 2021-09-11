package sw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Tertromino {
    static int max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[][] mtx = new int[m][n];

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < n; j++){
                mtx[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        max = 0;

        solve(mtx, m, n);
        System.out.println(max);
        br.close();
    }

    static void solve(int[][] mtx, int m, int n){
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(i >= 1 && j >= 1) updateMax(mtx[i][j], mtx[i-1][j], mtx[i][j-1], mtx[i-1][j-1]);
                if(i >= 3) updateMax(mtx[i-3][j], mtx[i-2][j], mtx[i-1][j], mtx[i][j]);
                if(j >= 3) updateMax(mtx[i][j-3], mtx[i][j-2], mtx[i][j-1], mtx[i][j]);
                if(i >= 2 && j >= 1) {
                    updateMax(mtx[i][j], mtx[i][j-1], mtx[i-1][j-1], mtx[i-2][j-1]);
                    updateMax(mtx[i][j], mtx[i-1][j], mtx[i-2][j], mtx[i-2][j-1]);
                    updateMax(mtx[i][j-1], mtx[i-1][j-1], mtx[i-2][j-1], mtx[i-2][j]);
                    updateMax(mtx[i][j], mtx[i][j-1], mtx[i-1][j], mtx[i-2][j]);

                    updateMax(mtx[i][j], mtx[i-1][j], mtx[i-1][j-1], mtx[i-2][j-1]);
                    updateMax(mtx[i][j-1], mtx[i-1][j-1], mtx[i-1][j], mtx[i-2][j]);

                    updateMax(mtx[i-1][j], mtx[i][j-1], mtx[i-1][j-1], mtx[i-2][j-1]);
                    updateMax(mtx[i][j], mtx[i-1][j], mtx[i-1][j-1], mtx[i-2][j]);
                }
                if(i >= 1 && j >= 2){
                    updateMax(mtx[i-1][j], mtx[i-1][j-1], mtx[i-1][j-2], mtx[i][j-2]);
                    updateMax(mtx[i][j], mtx[i][j-1], mtx[i][j-2], mtx[i-1][j]);
                    updateMax(mtx[i][j], mtx[i-1][j], mtx[i-1][j-1], mtx[i-1][j-2]);
                    updateMax(mtx[i][j], mtx[i][j-1], mtx[i][j-2], mtx[i-1][j-2]);


                    updateMax(mtx[i][j-1], mtx[i][j-2], mtx[i-1][j], mtx[i-1][j-1]);
                    updateMax(mtx[i][j], mtx[i][j-1], mtx[i-1][j-1], mtx[i-1][j-2]);

                    updateMax(mtx[i][j], mtx[i][j-1], mtx[i][j-2], mtx[i-1][j-1]);
                    updateMax(mtx[i][j-1], mtx[i-1][j-2], mtx[i-1][j-1], mtx[i-1][j]);
                }
            }
        }
    }

    static void updateMax(int v1, int v2, int v3, int v4){
        int v = v1 + v2 + v3 + v4;
        if(v > max) max = v;
    }
}
