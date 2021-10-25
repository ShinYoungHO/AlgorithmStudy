package category.etc2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class HideNSeek3 {
    static int m = 100001;
    static int INF = Integer.MAX_VALUE;
    static int solve(int f, int t){
        if(f == t) return 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((v1,v2)->v1[1]-v2[1]);
        int[] visited = new int[m];

        int res = INF;
        Arrays.fill(visited, INF);
        pq.add(new int[]{f, 0});
        visited[f] = 0;
        int x,d,nx;

        while(!pq.isEmpty()){
            int[] qv = pq.poll();
            x = qv[0];
            d = qv[1];
            while(true){
                nx = next(2, x);
                if(nx == t) {
                    int v = d;
                    if(v < res) res = v;
                }
                if(nx >= m || x == 0) break;
                if(visited[nx] <= d) break;
                pq.add(new int[]{nx, d});
                visited[nx] = d;
                x = nx;
            }
            x = qv[0];
            for(int i = 0; i < 2; i++){
                nx = next(i,x);
                if(nx == t) {
                    int v = d + 1;
                    if(v < res) res = v;
                    continue;
                }
                if(nx < 0 || nx >= m || visited[nx] <= d+1) continue;
                visited[nx] = d+1;
                pq.add(new int[]{nx, qv[1]+1});
            }
        }
        return res;
    }

    static int next(int idx, int k){
        switch(idx){
            case 0:
                return k-1;
            case 1:
                return k+1;
            case 2:
                return 2*k;
            default:
                return 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n,k ;
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        System.out.println(solve(n, k));;
    }
}
