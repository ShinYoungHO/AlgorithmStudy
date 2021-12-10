package category.ns.solved;

import java.io.*;
import java.util.*;
// fail - 50%
// need to refactor with Floyd-Warshall Algorithm

public class 우주탐사선_17182 {
    static int n,k;
    static int[][] map;
    static int INF = Integer.MAX_VALUE;
    static int max;
    static int ans;

    static void floydWarshall(){
        for(int k = 0; k < n; k++){
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }
    }

    static void DFS(int cur, int visited, int total){
        if(visited == max){
            ans = Math.min(ans, total);
            return;
        }
        for(int i = 0; i < n; i++){
            int nv = 1 << i;
            if((nv&visited) == nv) continue;
            DFS(i, visited|nv, total+map[cur][i]);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        max = (1<<n)-1;
        ans = INF;

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine()," ");
            for(int j = 0; j < n; j++){
                int v = Integer.parseInt(st.nextToken());
                map[i][j] = v;
            }
        }

        floydWarshall();
        DFS(k, 1<<k,  0);

        System.out.println(ans);
    }


//    static void solveWithOutFW(int node, int visited, int cnt){
//        if(visited == max){
//            ans = Math.min(ans, cnt);
//            return;
//        }
//        for(int i = 0; i < mtx[node].size(); i++){
//            int[] a = mtx[node].get(i);
//            int next = a[0];
//            int nv = visited | (1<<next);
//            int nc = cnt+a[1];
//            if(vis[next][nv] > nc && nc < ans){
//                int tmp = vis[next][nv];
//                vis[next][nv] = nc;
//                solveWithOutFW(next, nv, nc);
//                vis[next][nv] = tmp;
//            }
//        }
//    }

}

