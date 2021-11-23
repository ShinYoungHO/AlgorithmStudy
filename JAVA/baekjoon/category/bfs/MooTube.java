package category.bfs;


import java.io.*;
import java.util.*;

public class MooTube {
    static int N,Q;
    static ArrayList<int[]>[] map;
    static ArrayList<int[]> arr;
    static int solve(int k, int v){
        arr = map[v];
        if(arr.size() == 0) return 0;
        boolean[] vis = new boolean[N+1];
        vis[v] = true;
        Queue<Integer> q = new LinkedList<>();
        int res = 0;
        for(int i = 0; i < arr.size(); i++){
            int[] n = arr.get(i);
            if(n[1] >= k) {
                vis[n[0]] = true;
                q.add(arr.get(i)[0]);
                res++;
            }

        }
        int next,weight;
        while(!q.isEmpty()){
            next = q.poll();
            arr = map[next];
            if(arr.size() == 0) break;

            for(int i = 0; i < arr.size(); i++){
                int[] nq = arr.get(i);
                if(vis[nq[0]]) continue;
                weight = nq[1];
                if(weight >= k){
                    q.add(nq[0]);
                    vis[nq[0]] = true;
                    res++;
                }
            }
        }
        return res;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        map = new ArrayList[N+1];

        for(int i = 1; i <= N; i++){
            map[i] = new ArrayList<>();
        }

        for(int i = 1; i < N; i++){
            st = new StringTokenizer(br.readLine()," ");
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            map[p].add(new int[]{q, r});
            map[q].add(new int[]{p, r});
        }

        for(int i = 0; i < Q; i++){
            st = new StringTokenizer(br.readLine()," ");
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            sb.append(solve(k, v)).append("\n");
        }

        System.out.println(sb);
    }
}
