package category.shortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class UnconfirmedDST {
    static Map<Integer, ArrayList<Node>> map;
    static Set<Integer> set;
    static int[] visited;
    static int INF = Integer.MAX_VALUE;
    static String dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>((Node n1, Node n2) -> n1.w - n2.w);
        ArrayList<Integer> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        Node init = new Node(start, 0);
        pq.add(init);
        visited[start] = 0;

        while(!pq.isEmpty()){
            if(set.isEmpty()) break;
            Node n = pq.poll();
            ArrayList<Node> arr;
            arr = map.get(n.v);
            if(arr == null) continue;
            for(int i = 0; i < arr.size(); i++){
                Node child = arr.get(i);
                int nw = n.w + child.w;

                if(set.contains(n.v)){
                    if(n.w % 2 == 1) res.add(n.v);
                    set.remove(n.v);
                }
                if(visited[child.v] <= nw) continue;
                Node next = new Node(child.v, nw);
                visited[next.v] = nw;
                pq.add(next);
            }
        }

        Collections.sort(res);
        for(int i = 0; i < res.size(); i++){
            sb.append(res.get(i)).append(" ");
        }

        return sb.toString();
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int i = 1; i <= T; i++){
            int n,m,t,s,g,h;

            st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            visited = new int[n+1];
            Arrays.fill(visited, INF);

            set = new HashSet<>();
            map = new HashMap<>();
            for(int j = 1; j <= m; j++){
                st = new StringTokenizer(br.readLine(), " ");
                int n1 = Integer.parseInt(st.nextToken());
                int n2 = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());

                int ww = (n1 == h && n2 == g) || (n2 == h && n1 == g) ? w * 2 - 1 : w * 2;

                if(!map.containsKey(n1)) map.put(n1, new ArrayList<>());
                if(!map.containsKey(n2)) map.put(n2, new ArrayList<>());

                map.get(n1).add(new Node(n2, ww));
                map.get(n2).add(new Node(n1, ww));
            }

            for(int j = 0; j < t; j++){
                int v = Integer.parseInt(br.readLine());
                set.add(v);
            }

            sb.append(dijkstra(s)).append("\n");
        }
        System.out.println(sb);
    }

    private static class Node{
        int v;
        int w;
        public Node(int v, int w){
            this.v = v;
            this.w = w;
        }
    }
}
