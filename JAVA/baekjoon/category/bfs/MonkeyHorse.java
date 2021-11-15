package category.bfs;

import java.io.*;
import java.util.*;

public class MonkeyHorse {
    static int r,c;
    static int[] dx = {0, 1, 0, -1, -2, -1, 1, 2, 2, 1, -1, -2};
    static int[] dy = {1, 0, -1, 0, 1, 2, 2, 1, -1, -2, -2, -1};
    static int solve(boolean[][] mtx, int k){
        if(r == c && r == 1) return 0;
        boolean[][][] visited = new boolean[r][c][k+1];
        Queue<Mk> q = new LinkedList<>();
        q.add(new Mk(0, 0, k, 0));
        visited[0][0][k] = true;
        Mk prev,next;
        int nx,ny;
        while(!q.isEmpty()){
            prev = q.poll();
            for(int i = 0; i < 12; i++){
                nx = prev.x+dx[i];
                ny = prev.y+dy[i];
                if(nx < 0 || ny < 0 || nx >= r || ny >= c) continue;
                if(prev.rest == 0 && i >= 4) break;
                if(mtx[nx][ny]) continue;
                if(nx == r-1 && ny == c-1) return prev.cnt+1;
                if(visited[nx][ny][i < 4 ? prev.rest : prev.rest-1]) continue;
                if(i < 4){
                    next = new Mk(nx, ny, prev.rest,prev.cnt+1);
                } else {
                    next = new Mk(nx, ny, prev.rest-1, prev.cnt+1);
                }
                visited[nx][ny][next.rest] = true;
                q.add(next);
            }
        }
        return -1;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int k = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine()," ");
        c = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        boolean[][] mtx = new boolean[r][c];

        for(int i = 0; i < r; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < c; j++){
                int v = Integer.parseInt(st.nextToken());
                if(v == 1) mtx[i][j] = true;
            }
        }
        System.out.println(solve(mtx, k));
    }

    static class Mk{
        int x,y,rest,cnt;
        public Mk(int x, int y, int rest, int cnt) {
            this.x = x;
            this.y = y;
            this.rest = rest;
            this.cnt = cnt;
        }
    }
}
