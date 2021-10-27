package category.graph;

import java.io.*;
import java.util.*;

public class Race {
    static int n,m;
    static int[] dp, road;
    static ArrayList<int[]>[] graph;
    static int solve(int idx){
        int i, weight;
        if (dp[idx] != -1) return dp[idx];
        dp[idx] = 0;
        for (i = 0; i < graph[idx].size(); i++) {
            weight = solve(graph[idx].get(i)[0]) + graph[idx].get(i)[1];
            if (dp[idx] < weight) {
                dp[idx] = weight;
                road[idx] = graph[idx].get(i)[0];
            }
        }
        return dp[idx];
    }

    static void print(){
        int s = 1;
        StringBuilder sb = new StringBuilder();
        sb.append(dp[1]).append("\n").append(1).append(" ");
        int ns;
        while(true){
            ns = road[s];
            if(ns == 1) {
                sb.append(1);
                break;
            }
            sb.append(ns).append(" ");
            s = ns;
        }
        System.out.println(sb);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int p,q,r;
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        
        graph = new ArrayList[n+2];
        dp = new int[n+2];
        road = new int[n+2];

        Arrays.fill(dp, -1);
        dp[n+1] = 0;

        for(int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine(), " ");
            p = Integer.parseInt(st.nextToken());
            q = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());

            graph[p].add(new int[]{q, r});
        }

        solve(1);
        print();
    }

}
