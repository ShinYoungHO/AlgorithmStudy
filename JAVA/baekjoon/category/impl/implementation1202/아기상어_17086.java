package category.ns.implementation1202;

import java.io.*;
import java.util.*;

public class 아기상어_17086 {
    static int r,c;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

    static void solve(int[][] mtx){
        int ans = 0;
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                if(mtx[i][j]==1) continue;
                int v = BFS(i, j, mtx);
                ans = Math.max(ans, v);
            }
        }
        System.out.println(ans);
    }

    static int BFS(int sx, int sy, int[][] mtx){
        int ans = 0;
        Queue<int[]> q = new LinkedList<>();
        boolean[][] vis = new boolean[r][c];
        q.add(new int[]{sx, sy, 0});
        vis[sx][sy] = true;

        int x,y,nx,ny,cnt;

        kk : while(!q.isEmpty()){
            int[] qv = q.poll();
            x = qv[0];
            y = qv[1];
            cnt = qv[2];

            for(int i = 0; i < 8; i++){
                nx = x+dx[i];
                ny = y+dy[i];
                if(nx<0||ny<0||nx>=r||ny>=c) continue;
                if(vis[nx][ny]) continue;
                if(mtx[nx][ny] == 1) {
                    ans = cnt+1;
                    break kk;
                }
                vis[nx][ny] = true;
                q.add(new int[]{nx, ny, cnt+1});
            }
        }
        return ans;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        int[][] mtx = new int[r][c];

        for(int i = 0; i < r; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < c; j++){
                int v = Integer.parseInt(st.nextToken());
                mtx[i][j] = v;
            }
        }

        solve(mtx);
    }
}
