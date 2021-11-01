package category.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Cheese {
    static int r,c;
    static int[][] mtx;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    static void solve(){
        int cnt = 0;
        int res = 0;
        while(true){
            int v = 0;
            int mv = 0;
            for(int i = 0; i < r; i++){
                for(int j = 0; j < c; j++){
                    if((mtx[i][j] & 1) == 1) v++;
                    if(i > 0 && i < r-1 && j > 0 && j < c-1) continue;
                    if((mtx[i][j] & 4) != 4) BFS(i, j, 4, 1, 2);
                }
            }
            if(v == 0) break;
            for(int i = 0; i < r; i++){
                for(int j = 0; j < c; j++){
                    int k = mtx[i][j];
                    if((k&2) == 2) {
                        mtx[i][j] = 0;
                        mv++;
                    }
                    else mtx[i][j] &= (~4);
                }
            }

            cnt++;
            res = v;
            if(v == mv) break;
        }
        System.out.println(cnt+"\n"+res);
    }

    static int BFS(int x1, int y1, int vis, int target, int tm){
        Queue<int[]> q = new LinkedList<>();
        int x,y,nx,ny;
        q.add(new int[]{x1, y1});
        mtx[x1][y1] |= vis;

        while(!q.isEmpty()){
            int[] qv = q.poll();
            x = qv[0];
            y = qv[1];
            for(int i = 0; i < 4; i++){
                nx = x+dx[i];
                ny = y+dy[i];
                if(nx < 0 || ny < 0 || nx >= r || ny >= c) continue;
                if((mtx[nx][ny] & target) == target) {
                    mtx[nx][ny] |= tm;
                    continue;
                }
                if((mtx[nx][ny] & vis) != 0) continue;
                mtx[nx][ny] |= vis;
                q.add(new int[]{nx, ny});
            }
        }
        return 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        mtx = new int[r][c];

        for(int i = 0; i < r; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < c; j++){
                mtx[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        solve();
    }
}
