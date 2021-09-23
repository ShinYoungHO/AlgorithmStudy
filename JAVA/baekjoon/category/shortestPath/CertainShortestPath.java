package category.shortestPath;
// 다익스트라 다시 공부 해야될듯..
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CertainShortestPath {
    static Map<Integer, ArrayList<Node>> map;
    static int n;
    static int[] distance;
    static int INF = Integer.MAX_VALUE;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        distance = new int[n + 1];
        map = new HashMap<>();

        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(!map.containsKey(a)) map.put(a, new ArrayList<>());
            if(!map.containsKey(b)) map.put(b, new ArrayList<>());
            map.get(a).add(new Node(c, b));
            map.get(b).add(new Node(c, a));
        }

        int v1, v2;
        st = new StringTokenizer(br.readLine(), " ");
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        long answer1 = 0;
        answer1 += dijkstra(1, v1);
        answer1 += dijkstra(v1, v2);
        answer1 += dijkstra(v2, n);
        long answer2 = 0;
        answer2 += dijkstra(1, v2);
        answer2 += dijkstra(v2, v1);
        answer2 += dijkstra(v1, n);
        // Math.min(answer1, answer2) == INF  ::  err
        if (Math.min(answer1, answer2) >= INF) {
            System.out.println(-1);
            return;
        }
        System.out.println(Math.min(answer1, answer2));
    }

    private static int dijkstra(int start, int end) {
        Arrays.fill(distance, INF);
        distance[start] = 0;
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt((Node e) -> e.v));
        queue.add(new Node(0, start));
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int vtx = node.v;
            int w = node.w;
            ArrayList<Node> arr;

            if (distance[vtx] < w) continue;
            if((arr = map.get(vtx)) == null) continue;

            int len = arr.size();
            for (int i = 0; i < len; i++) {
                int vtx2 = arr.get(i).v;
                int w2 = arr.get(i).w + w;
//                if(vtx2 == end) return w2;  :: err
                if (distance[vtx2] > w2) {
                    distance[vtx2] = w2;
                    queue.add(new Node(w2, vtx2));
                }
            }
        }
        return distance[end];
    }


    private static class Node{
        int w;
        int v;
        public Node(int w, int v){
            this.w = w;
            this.v = v;
        }
    }
}
