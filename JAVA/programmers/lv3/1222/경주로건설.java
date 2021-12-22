package category.ns.programmersTMP;
import java.util.*;

public class 경주로건설 {

    class Solution {
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        int n;
        int INF = Integer.MAX_VALUE;

        public int solution(int[][] board) {
            PriorityQueue<int[]> pq = new PriorityQueue<>((n1, n2) -> n1[3]-n2[3]);
            n = board.length;
            int[][][] vis = new int[n][n][4];

            for(int i = 0; i < n; i++)
                for(int j = 0; j < n; j++)
                    Arrays.fill(vis[i][j], INF);

            for(int i = 0 ; i < 4; i++)
                pq.add(new int[]{0, 0, i, 0});

            int x,y,d,c,nx,ny,nc,qv[];
            while(!pq.isEmpty()){
                qv = pq.poll();
                x = qv[0];
                y = qv[1];
                d = qv[2];
                c = qv[3];

                for(int i = 0; i < 4; i++){
                    nx = x+dx[i];
                    ny = y+dy[i];
                    nc = c;
                    nc += i != d ? 6 : 1;
                    if(nx<0||ny<0||nx>=n||ny>=n||board[nx][ny]==1) continue;
                    if(vis[nx][ny][i] <= nc) continue;

                    vis[nx][ny][i] = nc;
                    if(nx==n-1&&ny==n-1) continue;
                    pq.add(new int[]{nx, ny, i, nc});
                }
            }

            int ans = INF;
            for(int i = 0; i < 4; i++)
                ans = Math.min(ans, vis[n-1][n-1][i]);

            return ans*100;
        }
    }
}
