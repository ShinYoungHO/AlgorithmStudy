package category.shortestPath;

import stepByStep.If.Compare;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class ShortestPath {


    static String solve(int start, int end, Map<Integer, ArrayList<Node>> map){
        StringBuilder sb = new StringBuilder();
        int[] res = new int [end+1];
        int[] qv;
        int node, w, len;

        PriorityQueue<int[]> pq = new PriorityQueue<>((int[] e1, int[] e2) -> e1[1] - e2[1]);
        pq.add(new int[]{start, 0});
        while(!pq.isEmpty()){
            qv = pq.poll();
            node = qv[0];
            w = qv[1];
            if(res[node] != 0) continue;

            res[node] = w;
            ArrayList<Node> arr = map.get(node);
            if(arr == null) continue;
            len = arr.size();
            for(int i = 0; i < len; i++){
                Node n = arr.get(i);
                pq.add(new int[]{n.v, n.w+w});
            }
        }



        for(int i = 1; i <= end; i++){
            if(i == start) sb.append("0\n");
            else sb.append(res[i] == 0 ? "INF" : res[i]).append("\n");
        }
        return sb.toString();
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        Map<Integer, ArrayList<Node>> map = new HashMap<>();

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(br.readLine());

        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            if(!map.containsKey(u)) map.put(u, new ArrayList<>());
            map.get(u).add(new Node(w, v));
        }

        System.out.println(solve(start, V, map));;
    }

}

class Node{
    Integer w;
    Integer v;
    public Node(int w, int v){
        this.w = w;
        this.v = v;
    }
}