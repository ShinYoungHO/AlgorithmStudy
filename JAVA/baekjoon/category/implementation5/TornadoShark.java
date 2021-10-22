package category.implementation5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TornadoShark {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static int[] percent = {1,7,10,1,7,10,2,2,5};
    static int[][] dxSand = {
            {-1,-1,-1,1,1,1,-2,2,0,0},
            {-1,0,1,-1,0,1,0,0,2,1},
            {1,1,1,-1,-1,-1,2,-2,0,0},
            {1,0,-1,1,0,-1,0,0,-2,-1}
    };
    static int[][] dySand = {
            {1,0,-1,1,0,-1,0,0,-2,-1},
            {-1,-1,-1,1,1,1,-2,2,0,0},
            {-1,0,1,-1,0,1,0,0,2,1},
            {1,1,1,-1,-1,-1,2,-2,0,0}
    };
    static int res;

    static void solve(int[][] mtx, int n){
        int x, y;
        int dir = 0;
        int l = 1;
        x = y = (n)/2;
        while(true){
            for(int i = 0; i < 2; i++){
                for(int j = 0; j < l; j++){
                    x += dx[dir];
                    y += dy[dir];
                    tornado(x, y, dir, mtx, n);
                    if(x == 0 && y == 0) return;
                }
                dir = (dir+1)%4;
            }
            l++;
        }
    }

    static void tornado(int x, int y, int dir, int[][] mtx, int n){
        int sand = mtx[x][y];
        int rest = sand;
        int nx,ny;
        for(int i = 0; i <= 9; i++){
            nx = x+dxSand[dir][i];
            ny = y+dySand[dir][i];
            if(i == 9){
                if(nx >= 0 && ny >= 0 && nx < n && ny < n) mtx[nx][ny] += rest;
                else res+=rest;
                break;
            }
            int m = sand*percent[i]/100;
            rest-=m;
            if(nx < 0 || ny < 0 || nx >= n || ny >= n) {
                res+=m;
                continue;
            }

            mtx[nx][ny]+=m;
        }
        mtx[x][y] = 0;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[][] mtx = new int[n][n];
        res = 0;

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < n; j++){
                mtx[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve(mtx, n);
        System.out.println(res);
    }
}
