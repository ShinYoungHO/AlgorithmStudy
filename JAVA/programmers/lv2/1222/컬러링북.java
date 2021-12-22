package category.ns.programmersTMP;
import java.util.*;

public class 컬러링북 {

    class Solution {
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        public int[] solution(int m, int n, int[][] picture) {
            int numberOfArea = 0;
            int maxSizeOfOneArea = 0;

            boolean[][] vis = new boolean[m][n];
            for(int i = 0; i < m; i++){
                for(int j = 0; j < n; j++){
                    if(vis[i][j] || picture[i][j] == 0) continue;
                    int v = BFS(i, j, picture, vis, m, n);
                    maxSizeOfOneArea = Math.max(v, maxSizeOfOneArea);
                    numberOfArea++;
                }
            }

            int[] answer = new int[2];
            answer[0] = numberOfArea;
            answer[1] = maxSizeOfOneArea;
            return answer;
        }

        int BFS(int x1, int y1, int[][] pic, boolean[][] vis, int m, int n){
            int ans = 1;
            int target = pic[x1][y1];
            Queue<int[]> q = new LinkedList<>();
            q.add(new int[]{x1, y1});
            vis[x1][y1] = true;

            int x,y,nx,ny;
            while(!q.isEmpty()){
                int[] qv = q.poll();
                x = qv[0];
                y = qv[1];
                for(int i = 0; i < 4; i++){
                    nx = x+dx[i];
                    ny = y+dy[i];
                    if(nx<0||ny<0||nx>=m||ny>=n||vis[nx][ny]||pic[nx][ny] != target) continue;
                    vis[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                    ans++;
                }
            }
            return ans;
        }
    }
}
