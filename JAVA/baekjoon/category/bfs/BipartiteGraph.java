package category.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BipartiteGraph {
    static String solve2(Map<Integer, ArrayList<Integer>> map, int v){

        int[] visited = new int[v+1];
        Queue<Integer> q;
        for(Integer i: map.keySet()){

            if(visited[i] != 0) continue;
            q = new LinkedList<>();
            q.add(i);
            visited[i] = 1;
            while(!q.isEmpty()){
                int qv = q.poll();
                int b = visited[qv];
                //
                ArrayList<Integer> arr = map.get(qv);

                for(int j = 0; j < arr.size(); j++){
                    int k = arr.get(j);

                    if(visited[k] == b && visited[k] != 0) return "NO";
                    if (visited[k] == 0) {
                        q.add(k);
                        int c = b == 1 ? 2 : 1;
                        visited[k] = c;

                    }
                }

            }
        }

        return "YES";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Map<Integer, ArrayList<Integer>> map;
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine()," ");
            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            map = new HashMap<>();

            for(int j = 0; j < e; j++){
                st = new StringTokenizer(br.readLine(), " ");
                int first = Integer.parseInt(st.nextToken());
                int second = Integer.parseInt(st.nextToken());
                addToMap(first, second, map);
            }
            sb.append(solve2(map, v)).append("\n");
        }
        System.out.println(sb);
    }

    static void addToMap(int a, int b, Map<Integer, ArrayList<Integer>> map){

        if(!map.containsKey(a)) map.put(a, new ArrayList<>());
        if(!map.containsKey(b)) map.put(b, new ArrayList<>());

        map.get(a).add(b);
        map.get(b).add(a);
    }
}

/**
 1-3-2
 */