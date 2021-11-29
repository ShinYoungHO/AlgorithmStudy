package category.ns.implementation1129;

import java.io.*;
import java.util.*;

public class 미네랄2_18500 {
    static int r,c;
    static boolean[][] mtx;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int MAX = Integer.MAX_VALUE;
    static int cnt;
    static boolean[][] vis;
    static void solve(int h, boolean isLeft){
        for(int i = 0; i < c; i++){
            int y = isLeft ? i : c-1-i;
            if(mtx[h][y]){
                mtx[h][y] = false;
                cnt--;
                vis = new boolean[r][c];
                int v = setVis();
                if(v == cnt) return;
                List<int[]> cluster = find();
                if(cluster.size() != 0) drop(cluster);
                return;
            }
        }
    }

    static List<int[]> find(){
        List<int[]> tmp = new ArrayList<>();
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                if(!vis[i][j] && mtx[i][j]) {
                    tmp.add(new int[]{i, j});
                }
            }
        }
        return tmp;
    }

    static void drop(List<int[]> query){

        int min = MAX;
        for(int i = 0; i < query.size(); i++){
            int[] q = query.get(i);
            int x = q[0];
            int y = q[1];
            while(x+1 < r && (!vis[x+1][y])) x++;
            int sub = x-q[0];
            if(sub < min) min = sub;
        }

        if(min == MAX || min <= 0) return;

        for(int i = 0; i < query.size(); i++){
            int[] tmp = query.get(i);
            int x = tmp[0];
            int y = tmp[1];
            vis[x+min][y] = true;
        }
        mtx = vis;
    }

    static int setVis(){
        int ans = 0;
        for(int i = 0; i < c; i++){
            int x = r-1;
            int y = i;
            if(vis[x][y] || !mtx[x][y]) continue;
            ans += DFS(x, y);
        }
        return ans;
    }

    static int DFS(int x, int y){
        int ans = 1;
        vis[x][y] = true;
        for(int i = 0; i < 4; i++){
            int nx = x+dx[i];
            int ny = y+dy[i];
            if(OOB(nx, ny)||vis[nx][ny]||!mtx[nx][ny]) continue;

            ans += DFS(nx, ny);
        }
        return ans;
    }



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        cnt = 0;
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        mtx = new boolean[r][c];
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
            solve(r-h, i % 2 == 0);
        }

        print(mtx);
    }

    static boolean OOB(int x, int y){
        return x<0||y<0||x>=r||y>=c;
    }

    static void print(boolean[][] mtx){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                if(mtx[i][j]) sb.append("x");
                else sb.append(".");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}