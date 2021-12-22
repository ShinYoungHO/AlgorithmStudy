package category.ns.programmersTMP;
import java.util.*;

public class 가장먼노드 {

    class Solution {
        int max, cnt;

        void solve(int start, int pathLen, List<Integer>[] li, boolean[] vis){
            List<Integer> neighbor;
            Queue<int[]> q = new LinkedList<>();
            q.add(new int[]{start, 0});
            vis[start] = true;

            while(!q.isEmpty()){
                neighbor = li[q.peek()[0]];
                int path = q.poll()[1];

                for(int i = 0; i < neighbor.size(); i++){
                    int v = neighbor.get(i);
                    if(vis[v]) continue;
                    vis[v] = true;
                    q.add(new int[]{v, path+1});
                    if(max < path+1){
                        max = path+1;
                        cnt = 1;
                    } else if(max == path+1){
                        cnt++;
                    }
                }
            }
        }
        public int solution(int n, int[][] edge) {
            List<Integer>[] li = new ArrayList[n+1];
            boolean[] vis = new boolean[n+1];
            max = cnt = 0;

            for(int i = 0; i < li.length; i++)
                li[i] = new ArrayList<>();

            for(int i = 0; i < edge.length; i++){
                int from = edge[i][0];
                int to = edge[i][1];

                li[from].add(to);
                li[to].add(from);
            }

            solve(1, 0, li, vis);
            System.out.println(max);
            return cnt;
        }
    }
}
