package category.ns.programmersTMP;
import java.util.*;

public class GPS {

    class Solution {
        int INF = 987654321;

        public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
            int ans;
            boolean[][] graph = getGraph(edge_list, n, m);
            int[][] dp = getDpArr(n, k);
            dp[0][gps_log[0]] = 0;

            for(int i = 1; i < k; i++){
                for(int j = 1; j < n+1; j++){
                    dp[i][j] = Math.min(dp[i][j], dp[i-1][j]);
                    for(int node = 1; node < n+1; node++){
                        if(graph[j][node]){
                            // i번째 인덱스를 j로 바꿨을 때
                            // 1번노드부터 n번까지 돌며,
                            // j와 연결된 노드들 중에서 출발한 경우의 기저조건들과 j -> j 로 가만히 있는 경우의 기저 조건들 중 최소 변경횟수를 dp에 할당
                            dp[i][j] = Math.min(dp[i][j], dp[i-1][node]);
                        }
                    }
                    // 최소로 변경한 이전조건을 찾았는데, 현재 갱신할 값이 gps로그와 다른 경우 변경해야하므로 +1;
                    if(j != gps_log[i]){
                        dp[i][j]++;
                    }
                }
            }
            ans = dp[k-1][gps_log[k-1]];
            return ans == INF ? -1 : ans;
        }

        int[][] getDpArr(int n, int k){
            int[][] dp = new int[k][n+1];
            for(int i = 0; i < k; i++)
                Arrays.fill(dp[i], INF);
            return dp;
        }

        boolean[][] getGraph(int[][] edge_list, int n, int m){
            boolean[][] ans = new boolean[n+1][n+1];

            for(int i = 0; i < m; i++){
                int n1 = edge_list[i][0];
                int n2 = edge_list[i][1];
                ans[n1][n2] = ans[n2][n1] = true;
            }
            return ans;
        }
    }
}
