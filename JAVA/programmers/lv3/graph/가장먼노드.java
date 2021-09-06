import java.util.*;

class Solution {
    Map<Integer, ArrayList<Integer>> map; 
    ArrayList<Integer> arr;
    public int solution(int n, int[][] edge) {
        map = new HashMap<>();
        for(int i = 0; i < edge.length; i++){
            add(edge[i]);
        }
        return BFS(n);
    }
    
    int BFS(int n){
        ArrayList<Integer> arr;
        Queue<int[]> q = new LinkedList<>();
        boolean[] visited = new boolean[n+1];
        int result = 0;
        int max = 0;
        
        visited[1] = true;
        q.add(new int[]{1, 0});
        while(!q.isEmpty()){
            int[] qv = q.poll();
            arr = map.get(qv[0]);
            if(arr == null) continue;
            for(int i = 0; i < arr.size(); i++){
                int nxt = arr.get(i);
                if(!visited[nxt]){
                    if(qv[1]+1 > max){ 
                        max = qv[1]+1;
                        result = 0;
                    }
                    
                    visited[nxt] = true;
                    q.add(new int[]{nxt, qv[1]+1});
                    result++;
                }
            }
        }
        return result;
    }
    
    void add(int [] vs){
        for(int i = 0; i < 2; i++){
            int v = vs[i];
            arr = map.get(v);
            if(arr == null){
                arr = new ArrayList<Integer>();
                map.put(v, arr);
            }
            arr.add(i == 0 ? vs[1] : vs[0]);
        }
        
    }
}