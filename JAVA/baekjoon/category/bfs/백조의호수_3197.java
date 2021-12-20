package category.ns.solved;

import java.io.*;
import java.util.*;


public class 백조의호수_3197 {
    static int n,m;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int k;
    static int[] from, to;

    static void solve(boolean[][] mtx){
        k = 0;
        Queue<int[]> wq = new LinkedList<>();
        Queue<int[]> lq = new LinkedList<>();
        boolean[][] sv = new boolean[n][m];
        boolean[][] wv = new boolean[n][m];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(!mtx[i][j] && !wv[i][j]) {
                    Queue<int[]> tmp = new LinkedList<>();
                    tmp.add(new int[]{i, j});
                    BFS(tmp, wv, mtx, wq, false);
                }

            }
        }

        Queue<int[]> tmp = new LinkedList<>();
        tmp.add(new int[]{from[0], from[1]});
        sv[from[0]][from[1]] = true;

        while(true){
            if(BFS(tmp, sv, mtx, lq, true)) return;

            Queue<int[]> nq = new LinkedList<>();
            BFS(wq, wv, mtx, nq, false);
            wq = nq;
            tmp = lq;
            lq = new LinkedList<>();
            k++;
        }
    }

    static boolean BFS(Queue<int[]> q, boolean[][] vis, boolean[][] mtx, Queue<int[]> ans, boolean isSwan){
        int x,y,nx,ny,qv[];
        while(!q.isEmpty()){
            qv = q.poll();
            x=qv[0];
            y=qv[1];
            mtx[x][y] = false;
            for(int i = 0; i < 4; i++){
                nx=x+dx[i];
                ny=y+dy[i];
                if(isOOB(nx, ny)||vis[nx][ny]) continue;
                if(isSwan){
                    if(nx==to[0]&&ny==to[1]){
                        System.out.println(k);
                        return true;
                    }
                }
                vis[nx][ny] = true;
                if(mtx[nx][ny]){
                    ans.add(new int[]{nx, ny});
                    continue;
                }
                q.add(new int[]{nx, ny});
            }
        }
        return false;
    }

    static boolean isOOB(int x, int y){
        return x<0||y<0||x>=n||y>=m;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        boolean[][] mtx = new boolean[n][m];
        from = null;
        to = null;
        for(int i = 0; i < n; i++){
            String s = br.readLine();
            for(int j = 0; j < m; j++){
                if(s.charAt(j) == 'X') mtx[i][j] = true;
                else if(s.charAt(j) == 'L') {
                    if(from == null) from = new int[]{i, j};
                    else to = new int[]{i, j};
                }
            }
        }
        if(from == null || to == null) return;
        solve(mtx);
    }
}
