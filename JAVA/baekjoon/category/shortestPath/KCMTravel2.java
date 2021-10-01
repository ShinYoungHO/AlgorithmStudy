package category.shortestPath;

import jdk.swing.interop.SwingInterOpUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class KCMTravel2 {
    static Map<Integer, ArrayList<Node>> map;
    static int[][] visited;
    static int INF = Integer.MAX_VALUE;
    static String solve(int n, int m){
        PriorityQueue<Node> pq = new PriorityQueue<>((Node n1, Node n2) ->
                n1.d < n2.d ? -1 : n1.d == n2.d ? n1.c < n2.c ? -1 : 0 : 1
        );
        pq.add(new Node(1,0,0));
        visited[1][0] = 0;

        int pNext, pCost, pTime;
        int next;

        int timeSum, costSum;
        while(!pq.isEmpty()){
            Node qv = pq.poll();
            pNext = qv.v;
            pCost = qv.c;
            pTime = qv.d;

            if(pNext == n) return String.valueOf(pTime);
            if(pTime > visited[pNext][pCost]) continue;

            ArrayList<Node> arr = map.get(pNext);
            if(arr == null) continue;
            for(int i = 0; i < arr.size(); i++){
                Node nq = arr.get(i);
                next = nq.v;
                timeSum = pTime+nq.d;
                costSum = pCost+nq.c;

                if(costSum > m) continue;
                if(timeSum < visited[next][costSum]){
                    for(int j = costSum; j <= m; j++){
                        if(visited[next][j] > timeSum) visited[next][j] = timeSum;
                    }
                    pq.add(new Node(next, costSum, timeSum));
                }
            }
        }
        return "Poor KCM";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();
        int T = parseInt(st.nextToken());

        for(int i = 1; i <= T; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int n = parseInt(st.nextToken());
            int m = parseInt(st.nextToken());
            int k = parseInt(st.nextToken());

            map = new HashMap<>();
            visited = new int[n+1][];
            for(int j = 0; j <= n; j++){
                int[] tmp = new int[1000001];
                Arrays.fill(tmp,INF);
                visited[j] = tmp;
            }

            for(int j = 0; j < k; j++){
                st = new StringTokenizer(br.readLine(), " ");
                int u = parseInt(st.nextToken()); // 출발
                int v = parseInt(st.nextToken()); // 도착
                int c = parseInt(st.nextToken()); // 비용
                int d = parseInt(st.nextToken()); // 소요시간

                if(!map.containsKey(u)) map.put(u, new ArrayList<>());
                map.get(u).add(new Node(v, c, d));
            }

            sb.append(solve(n, m)).append("\n");
        }
        System.out.println(sb);
    }

    private static class Node{
        int v;
        int c;
        int d;
        private Node(int v, int c, int d){
            this.v = v;
            this.c = c;
            this.d = d;
        }
    }

    static int parseInt(String s){
        return Integer.parseInt(s);
    }
}
