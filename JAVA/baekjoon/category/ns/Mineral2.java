package category.ns;

import java.io.*;
import java.util.*;

public class Mineral2 {
    static int r,c;
    static boolean[][] mtx;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int MAX = Integer.MAX_VALUE;
    static int MIN = Integer.MIN_VALUE;
    static int cnt;
    static void ret(int h, boolean isLeft){
        for(int i = 0; i < c; i++){
            int y = isLeft ? i : c-1-i;

            if(mtx[h][y]){
                mtx[h][y] = false;
                cnt--;
                int tmp = 0;
                boolean[][] vis = new boolean[r][c];
                for(int j = 0; j < c; j++){
                    if(mtx[r-1][j] && !vis[r-1][j]) tmp += BFS(r-1, j, vis);
                }
                if(tmp != cnt) fall(vis);
                return;
            }
        }
    }

    static void fall(boolean[][] vis){
        ArrayList<ArrayList<int[]>> arr = new ArrayList<>();
        for(int i = 0; i < c; i++){
            for(int j = r-1; j >= 0; j--){
                if(mtx[j][i] == vis[j][i]) continue;
                ArrayList<int[]> tmp = new ArrayList<>();
                Queue<int[]> q = new LinkedList<>();

                int[] crd = new int[]{j, i};
                tmp.add(crd);
                q.add(crd);
                vis[j][i] = true;
                int x,y,nx,ny;
                while(!q.isEmpty()){
                    int[] qv = q.poll();
                    x = qv[0];
                    y = qv[1];
                    for(int k = 0; k < 4; k++){
                        nx = x+dx[k];
                        ny = y+dy[k];
                        if(nx < 0 || ny < 0 || nx >= r || ny >= c) continue;
                        if(vis[nx][ny]) continue;
                        if(!mtx[nx][ny]) continue;
                        int[] next = new int[]{nx, ny};
                        vis[nx][ny] = true;
                        q.add(next);
                        tmp.add(next);
                    }
                }

                arr.add(tmp);
            }
        }
        for(int i = 0; i < arr.size(); i++){
            ArrayList<int[]> query = arr.get(i);
            int[][] ft = new int[c][2];
            for(int j = 0; j < c; j++){
                ft[j][0] = MAX;
                ft[j][1] = MIN;
            }

            for(int j = 0; j < query.size(); j++){
                int[] q = query.get(j);
                int x = q[0];
                int y = q[1];
                if(ft[y][0] > x) ft[y][0] = x;
                if(ft[y][1] < x) ft[y][1] = x;
            }

            int min = MAX;

            for(int j = 0; j < ft.length; j++){
                int h = ft[j][1];
                if(h == MIN) continue;
                while(h+1 < r && !mtx[h+1][j]) h++;
                int m = h-ft[j][1];
                if(m < min && m >= 0) min = m;
            }

            for(int j = 0; j < ft.length; j++){
                int f = ft[j][0];
                int t = ft[j][1];
                if(f == MAX || t == MIN) continue;
                for(int k = t; k >= f; k--){
                    mtx[k+min][j] = mtx[k][j];
                }
                for(int k = f+min-1; k >= f; k--){
                    mtx[k][j] = false;
                }
            }
        }
    }

    static int BFS(int sx, int sy, boolean[][] vis){
        int x,y,nx,ny;
        int ans = 1;
        vis[sx][sy] = true;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{sx, sy});
        while(!q.isEmpty()){
            int[] qv = q.poll();
            x = qv[0];
            y = qv[1];
            for(int i = 0; i < 4; i++){
                nx = x+dx[i];
                ny = y+dy[i];
                if(nx < 0 || ny < 0 || nx >= r || ny >= c) continue;
                if(!mtx[nx][ny]) continue;
                if(vis[nx][ny]) continue;
                vis[nx][ny] = true;
                q.add(new int[]{nx, ny});
                ans++;
            }
        }
        return ans;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        mtx = new boolean[r][c];
        cnt = 0;
        for(int i = 0; i < r; i++){
            String s = br.readLine();
            for(int j = 0; j < c; j++){
                if(s.charAt(j) == 'x') {
                    mtx[i][j] = true;
                    cnt++;
                }
            }
        }

        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");


        for(int i = 0; i < n; i++){
            int h = Integer.parseInt(st.nextToken());
//            print(mtx);
            ret(r-h, i % 2 == 0);
//            print(mtx);
//            System.out.println("===========");
        }

        print(mtx);
    }

    static void print(boolean[][] mtx){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < r; i++){
            StringBuilder nsb = new StringBuilder();
            for(int j = 0; j < c; j++){
                if(mtx[i][j]) nsb.append("x");
                else nsb.append(".");
            }
            sb.append(nsb).append("\n");
        }

        System.out.println(sb);
    }
}
/*
8 8
.....xxx
.....x..
...x.xx.
...xxx..
..xx.x..
..x.xxx.
..x...x.
.xxx....
2
8 5



8 8
.xxxxxxx
.x....x.
.xx..xx.
xx......
.x....x.
xxx...xx
..x.xx.x
..x.xx.x
2
8 5



8 8
xxxxxxxx
.x....x.
.xx..xx.
xx......
.x....x.
xxx...xx
..x.xx.x
..x.xx.x
2
8 5

 */