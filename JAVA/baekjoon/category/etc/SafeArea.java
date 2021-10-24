package category.etc;

import java.io.*;
import java.util.*;

public class SafeArea {
    static int n;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int[][] mtx;
    static int res;

    static void solve(int l){
        boolean[][] visited = new boolean[n][n];
        int cnt = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(mtx[i][j] > l && !visited[i][j]){
                    BFS(visited, i, j, l);
                    cnt++;
                }
            }
        }
        if(cnt > res) res = cnt;
    }

    static void BFS(boolean[][] visited, int r, int c, int l){
        int x,y,nx,ny;
        visited[r][c] = true;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r, c});
        while(!q.isEmpty()){
            int[] qv = q.poll();
            x = qv[0];
            y = qv[1];
            for(int i = 0; i < 4; i++){
                nx = x+dx[i];
                ny = y+dy[i];
                if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                if(visited[nx][ny]) continue;
                if(mtx[nx][ny] <= l) continue;
                q.add(new int[]{nx,ny});
                visited[nx][ny] = true;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        mtx = new int[n][n];
        res = 1;
        int max = 0;
        int min = 101;

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < n; j++){
                mtx[i][j] = Integer.parseInt(st.nextToken());
                if(max < mtx[i][j]) max = mtx[i][j];
                if(mtx[i][j] < min) min = mtx[i][j];
            }
        }

        for(int i = min; i < max; i++){
            solve(i);
        }
        System.out.println(res);
    }
}
