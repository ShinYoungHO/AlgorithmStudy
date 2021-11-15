package category.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TSP2 {
    static int n;
    static int min;
    static int[][] mtx;
    static boolean[] used;
    static void solve(int st, int node, int cnt, int res, boolean[] used){
        if(res >= min) return;
        if(cnt == n){
            if(st == node) min = res;
            return;
        }
        for(int i = 0; i < n; i++){
            int v = mtx[node][i];
            if(used[i] || v == 0) continue;
            if(i == st){
                if(cnt == n-1) solve(st,st,cnt+1,res+v, used);
                else continue;
            }
            used[i] = true;
            solve(st, i, cnt+1, res+v, used);
            used[i] = false;
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        mtx = new int[n][n];
        used = new boolean[n];
        min = Integer.MAX_VALUE;

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < n; j++){
                mtx[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i = 0; i < n; i++){
            solve(i, i, 0, 0, used);
        }
        System.out.println(min);
    }
}
