package category.implementation6;

import java.io.*;
import java.util.*;

public class Chess {
    static int n;
    static int k;
    static int[] dx1 = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] dy1 = {1, 2, 2, 1, -1, -2, -2, -1};
    static int[] dx2 = {1, 0, -1, 0};
    static int[] dy2 = {0, 1, 0, -1};
    static int[] dx3 = {1, 1, -1, -1};
    static int[] dy3 = {1, -1, 1, -1};
    static int ans;

    static int solve(int[][] mtx, int x1, int y1){
        ans = -1;
        k = n*n;
        Queue<int[]> q = new LinkedList<>();
        int[][][] visited = new int[n][n][n*n+1];

        for(int i = 0; i < 3; i++){
            q.add(new int[]{x1, y1, 1, i, 0});
            visited[x1][y1][0] = 1 << i;
        }
        int[] qv;
        int x,y,nx,ny,current,type,cnt;
        while(!q.isEmpty()){
            qv = q.poll();
            x = qv[0];
            y = qv[1];
            current = qv[2];
            type = qv[3];
            cnt = qv[4];
            if(type == 0){
                for(int i = 0; i < 8; i++){
                    nx = x+dx1[i];
                    ny = y+dy1[i];
                    ret(nx,ny,type,current,cnt,mtx,visited,q);
                    if(ans!=-1) return ans;
                }
            }
            else if(type == 1){
                for(int i = 0; i < 4; i++){
                    nx = x;
                    ny = y;
                    while(true){
                        nx+=dx2[i];
                        ny+=dy2[i];
                        if(OOB(nx,ny)) break;
                        ret(nx,ny,type,current,cnt,mtx,visited,q);
                        if(ans != -1) return ans;
                    }
                }
            } else {
                for(int i = 0; i < 4; i++){
                    nx = x;
                    ny = y;
                    while(true){
                        nx+=dx3[i];
                        ny+=dy3[i];
                        if(OOB(nx,ny)) break;
                        ret(nx,ny,type,current,cnt,mtx,visited,q);
                        if(ans != -1) return ans;
                    }
                }
            }
            for(int i = 0; i < 3; i++){
                if((visited[x][y][current] & (1<<i)) != (1<<i)){
                    q.add(new int[]{x,y,current,i,cnt+1});
                    visited[x][y][current] |= (1<<i);
                }
            }
        }
        return ans;
    }

    static boolean OOB(int x, int y){
        if(x < 0 || y < 0 || x >= n || y >= n) return true;
        return false;
    }

    static void ret(int nx, int ny, int type, int current, int cnt, int[][] mtx, int[][][] visited, Queue<int[]> q){
        if(OOB(nx,ny)) return;
        if((visited[nx][ny][current] & (1<<type)) == (1<<type) ) return;
        if(k == current+1 && mtx[nx][ny] == k) {
            ans = cnt+1;
            return;
        }
        if(mtx[nx][ny] == current+1){
            visited[nx][ny][current+1] |= (1<<type);
            q.add(new int[]{nx, ny, current+1, type, cnt+1});
        } else {
            visited[nx][ny][current] |= (1<<type);
            q.add(new int[]{nx, ny, current, type, cnt+1});
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        int[][] mtx = new int[n][n];
        int x=0,y=0;

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < n; j++){
                mtx[i][j] = Integer.parseInt(st.nextToken());
                if(mtx[i][j] == 1){
                    x = i;
                    y = j;
                }
            }
        }

        System.out.println(solve(mtx, x, y));
    }
}
