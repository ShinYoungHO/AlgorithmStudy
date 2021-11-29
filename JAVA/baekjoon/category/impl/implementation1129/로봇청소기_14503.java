package category.ns.implementation1129;

import java.io.*;
import java.util.*;

public class 로봇청소기_14503 {
    static int r,c;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static int solve(boolean[][] map, boolean[][] cleaned, int x, int y, int d){
        int res = 0;
        int rotated = 0;
        while(true){
            if(!cleaned[x][y]){
                cleaned[x][y] = true;
                res++;
            }

            int nd = (d+4-1)%4;
            int nx = x+dx[nd];
            int ny = y+dy[nd];

            if(cleaned[nx][ny]){
                d = nd;
                rotated++;
                if(rotated == 4){
                    int td = (d+2)%4;
                    nx = x+dx[td];
                    ny = y+dy[td];
                    if(map[nx][ny]) return res;
                    else {
                        x = nx;
                        y = ny;
                    }
                    rotated = 0;
                }
            } else {
                d = nd;
                x = nx;
                y = ny;
                rotated = 0;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        int x,y,d;
        st = new StringTokenizer(br.readLine(), " ");
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        boolean[][] map = new boolean[r][c];
        boolean[][] cleaned = new boolean[r][c];
        for(int i = 0; i < r; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < c; j++){
                int v = Integer.parseInt(st.nextToken());
                if(v == 1) {
                    map[i][j] = true;
                    cleaned[i][j] = true;
                }
            }
        }
        System.out.println(solve(map, cleaned, x, y, d));;
    }
}


