package mocktest.powerCoding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 군사이동_11085{
    static Map<Integer, ArrayList<군사이동_11085_timeout.Path>> map;
    static int INF = Integer.MAX_VALUE;
    static int res = 0;

    static void solve(){

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        map = new HashMap<>();
        int p,w,c,v;
        p = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        c = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());

        for(int i = 0; i < p; i++){
            map.put(i, new ArrayList<>());
        }

        for(int i = 1; i <= w; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            map.get(from).add(new 군사이동_11085_timeout.Path(weight, to));
            map.get(to).add(new 군사이동_11085_timeout.Path(weight, from));
        }

        System.out.println(res);
    }

    static class Path{
        int w,to;
        Path(int w, int to){
            this.w = w;
            this.to = to;
        }
    }
}


class 군사이동_11085_timeout {
    static Map<Integer, ArrayList<Path>> map;
    static int INF = Integer.MAX_VALUE;
    static int res = 0;
    static void solve(int b, int c, boolean[] visited, int min){
        if(b == c) {
            if(min > res) res = min;
            return;
        }
        int node = b;
        ArrayList<Path> arr = map.get(node);
        for(int i = 0; i < arr.size(); i++){
            Path route = arr.get(i);
            if(visited[route.to]) continue;
            visited[route.to] = true;
            solve(route.to, c, visited,Math.min(route.w, min));
            visited[route.to] = false;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        map = new HashMap<>();
        int p,w,c,v;
        p = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        c = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());

        for(int i = 0; i < p; i++){
            map.put(i, new ArrayList<>());
        }

        for(int i = 1; i <= w; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            map.get(from).add(new Path(weight, to));
            map.get(to).add(new Path(weight, from));
        }
        boolean[] visited = new boolean[p];
        visited[c] = true;
        solve(c, v, visited, INF);
        System.out.println(res);
    }

    static class Path{
        int w,to;
        Path(int w, int to){
            this.w = w;
            this.to = to;
        }
    }
}
