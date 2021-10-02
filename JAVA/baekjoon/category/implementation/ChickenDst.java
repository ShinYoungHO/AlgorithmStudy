package category.implementation;

// 15686

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ChickenDst {
    static int INF = Integer.MAX_VALUE;
    static int m, n, h, c;
    static int[] target;
    static int[][] dp;
    static Coord[] house;
    static Coord[] chicken;
    static int s = INF;
    static void solve(int idx, int start){
        if(idx == m){
            int v = getPath();
            if(v < s) s = v;
        }

        for(int i = start; i < c; i++){
            target[idx] = i;
            solve(idx+1, i+1);
        }
    }

    static int getPath(){
        int result = 0;
        int tmp,dst;
        for(int i = 0; i < h; i++){
            tmp = INF;
            for(int j = 0; j < m; j++){
                int k = target[j];
                dst = dp[i][k];
                if(dst < tmp) tmp = dst;
            }
            result += tmp;
            if(result > s) return INF;
        }
        return result;
    }

    static void setDP(){
        Coord p1,p2;
        for(int i = 0; i < h; i++){
            p1 = house[i];
            for(int j = 0; j < c; j++){
                p2 = chicken[j];
                dp[i][j] = Math.abs(p1.x-p2.x) + Math.abs(p1.y-p2.y);
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");


        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        house = new Coord[2*n];
        chicken = new Coord[13];

        h = 0;
        c = 0;

        for(int i = 0 ; i < n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < n; j++){
                String v = st.nextToken();
                if(v.equals("1")){
                    house[h++] = new Coord(i, j);
                } else if(v.equals("2")){
                    chicken[c++] = new Coord(i, j);
                }
            }
        }

        target = new int[c];
        dp = new int[h][c];
        setDP();
        solve(0, 0);

        System.out.println(s);
    }

    private static class Coord{
        int x,y;
        public Coord(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}

