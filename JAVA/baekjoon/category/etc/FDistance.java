package category.etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class FDistance {
    static int n,f,t;
    static boolean[][] map;
    static int solve(int f, int t, int n){
        boolean[] visited = new boolean[n+1];
        visited[f] = true;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{f, 0});
        while(!q.isEmpty()){
            int[] qv = q.poll();
            int v = qv[0];
            int c = qv[1];
            for(int i = 1; i <= n; i++){
                if(i == v) continue;
                if(visited[i]) continue;
                if(!map[v][i]) continue;
                if(i == t) return c+1;
                q.add(new int[]{i, c+1});
                visited[i] = true;
            }
        }
        return -1;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new boolean[101][101];

        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine()," ");
        f = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        int k = Integer.parseInt(br.readLine());

        for(int i = 0; i < k; i++){
            st = new StringTokenizer(br.readLine()," ");
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());

            map[parent][child] = true;
            map[child][parent] = true;
        }


        System.out.println(solve(f, t, n));
    }
}
