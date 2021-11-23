package category.graph;

import java.io.*;
import java.util.*;

public class WateringField {
    static int n,c;
    static int INF = Integer.MAX_VALUE;
    static boolean[] selected;
    static int[] dist;
    static int[][] weight;

    static int prim(int s) {
        int i, u, v, ans = 0;
        dist[s] = 0;
        for (i = 0; i < n; i++) {
            u = 0;
            for(int j = 0; j < n; j++){
                if(!selected[j]) {
                    u = j;
                    break;
                }
            }
            for (int j = 0; j < n; j++) {
                if (!selected[j] && (dist[j] < dist[u]) && dist[j] >= c){
                    u = j;
                }
            }
            selected[u] = true;
            if (dist[u] == INF) return -1;

            for (v = 0; v < n; v++) {
                if (weight[u][v] != INF) {
                    if (!selected[v] && weight[u][v] < dist[v] && weight[u][v] >= c){
                        dist[v] = weight[u][v];
                    }
                }
            }
        }

        for(int idx = 0; idx < n; idx++){
            if(dist[idx] == INF) return -1;
            ans += dist[idx];
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        weight = new int[n][n];
        selected = new boolean[n];
        dist = new int[n];

        int[][] crd = new int[n][2];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine()," ");
            crd[i][0] = Integer.parseInt(st.nextToken());
            crd[i][1] = Integer.parseInt(st.nextToken());
            Arrays.fill(weight[i], INF);
        }
        Arrays.fill(dist, INF);

        int a, b, ul;
        for(int i = 0; i < n-1; i++){
            for(int j = i+1; j < n; j++){
                a = crd[i][0]-crd[j][0];
                b = crd[i][1]-crd[j][1];
                ul = a*a+b*b;
                weight[i][j] = weight[j][i] = ul;
            }
        }

        System.out.println(prim(0));
    }
}




