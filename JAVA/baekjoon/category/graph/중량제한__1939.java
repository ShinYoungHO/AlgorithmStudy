package category.ns.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 중량제한__1939 {
    static int n,m;
    static List<int[]>[] graph;
    static int from, to, nw;
    static int[] tmp;
    static A target, nxt;
    static boolean[] vis;
    static Queue<A> q;
    static A[] wrap;

    static void solve(int max){
        int l = 1;
        int r = max;
        int mid;
        int ans = 0;
        vis = new boolean[n];

        while(l <= r){
            mid = (l+r)>>1;
            if(BFS(mid, wrap[from], wrap[to])){
                ans = Math.max(ans, mid);
                l = mid+1;
            } else {
                r = mid-1;
            }
        }
        System.out.println(ans);
    }

    static boolean BFS(int w, A from, A to){
        Arrays.fill(vis, false);
        q = new LinkedList<>();

        q.add(from);

        while(!q.isEmpty()){
            target = q.poll();
            List<int[]> arr = graph[target.v];
            for(int i = 0; i < arr.size(); i++){
                tmp = arr.get(i);
                nxt = wrap[tmp[0]];
                nw = tmp[1];

                if(w > nw || vis[nxt.v]) continue;
                if(nxt.v == to.v) return true;
                vis[nxt.v] = true;
                q.add(nxt);
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n];
        wrap = new A[n];
        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            wrap[i] = new A(i);
        }

        int f,t,w;
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine(), " ");
            f = Integer.parseInt(st.nextToken())-1;
            t = Integer.parseInt(st.nextToken())-1;
            w = Integer.parseInt(st.nextToken());

            graph[f].add(new int[]{t, w});
            graph[t].add(new int[]{f, w});
        }

        st = new StringTokenizer(br.readLine(), " ");

        from = Integer.parseInt(st.nextToken())-1;
        to = Integer.parseInt(st.nextToken())-1;

        int maxT = 0;
        for(int i = 0; i < graph[to].size(); i++) maxT = Math.max(maxT, graph[to].get(i)[1]);

        int maxF = 0;
        for(int i = 0; i < graph[from].size(); i++) maxF = Math.max(maxF, graph[from].get(i)[1]);

        solve(Math.min(maxT, maxF));

    }
    static class A{
        int v;

        public A(int v) {
            this.v = v;
        }
    }
}

/*
6 9
1 2 7
1 3 8
1 4 7
1 6 9
2 3 7
3 4 7
3 5 7
4 5 7
4 6 7
6 3

4 5
1 2 9
2 3 3
2 4 4
1 3 7
3 4 7
1 4


4 5
1 2 9
2 3 8
2 4 4
1 3 7
3 4 7
1 4
 */