package category.ns.programmersTMP._lv2;
import java.util.*;

public class 피로도 {

    class Solution {
        int ans;
        public int solution(int k, int[][] dungeons) {
            boolean[] visited = new boolean[dungeons.length];
            ans = 0;
            DFS(k, 0, dungeons, visited);
            return ans;
        }
        void DFS(int k, int cnt, int[][] dungeons, boolean[] visited){
            for(int i = 0; i < dungeons.length; i++){
                if(!visited[i] && k >= dungeons[i][0]){
                    visited[i] = true;
                    DFS(k-dungeons[i][1], cnt+1, dungeons, visited);
                    visited[i] = false;
                }
            }
            ans = Math.max(ans, cnt);
        }
    }
}
