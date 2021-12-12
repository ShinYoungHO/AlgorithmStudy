package category.ns.solved;

import jdk.swing.interop.SwingInterOpUtils;

import java.io.*;
import java.util.*;

public class 빵집_3109 {
    static int r,c;
    static int[] dx = {-1, 0, 1};
    static int[] dy = {1, 1, 1};

    static int solve(boolean[][] mtx){
        int ans = 0;
        for(int i = 0; i < r; i++){
            mtx[i][0] = true;
            boolean k = DFS(i, 0, mtx);
            if(k) ans++;
        }
        return ans;
    }

    static boolean DFS(int x, int y, boolean[][] mtx){
        for(int i = 0; i < 3; i++){
            int nx = x+dx[i];
            int ny = y+dy[i];
            if(nx<0||ny<0||nx>=r||ny>=c||mtx[nx][ny]) continue;
            if(ny==c-1) return mtx[nx][ny] = true;
            mtx[nx][ny] = true;
            if(DFS(nx, ny, mtx)) return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        boolean[][] mtx = new boolean[r][c];

        for(int i = 0; i < r; i++){
            String s = br.readLine();
            for(int j = 0; j < c; j++){
                if(s.charAt(j)=='x') mtx[i][j] = true;
            }
        }
        System.out.println(solve(mtx));
    }
}
