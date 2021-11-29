package category.implementation4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class QueenB {

    static void solve(int[][] mtx, int p, int q, int r, int m){
        int k;
        for(int i = m-1; i > 0; i--){
            if(p > 0) {
                k = 0;
                p--;
            } else if(q > 0){
                k = 1;
                q--;
            } else {
                k = 2;
                r--;
            }
            mtx[i][0] += k;
        }
        for(int i = 0; i < m; i++){
            if(p > 0) {
                k = 0;
                p--;
            } else if(q > 0){
                k = 1;
                q--;
            } else {
                k = 2;
                r--;
            }
            mtx[0][i] += k;
        }


    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        StringBuilder sb = new StringBuilder();

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[][] mtx = new int[m][m];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < m; j++){
                mtx[i][j] = 1;
            }
        }

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine()," ");
            int p,q,r;
            p = Integer.parseInt(st.nextToken());
            q = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());

            solve(mtx, p, q, r, m);
        }

        for(int i = 1; i < m; i++){
            for(int j = 1; j < m; j++){
                mtx[i][j]=mtx[0][j];
            }
        }


        for(int i = 0; i < m; i++){
            for(int j = 0; j < m; j++){
                sb.append(mtx[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
