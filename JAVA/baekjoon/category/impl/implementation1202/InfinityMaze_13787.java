package category.ns.implementation1202;

import java.io.*;
import java.util.*;

public class InfinityMaze_13787 {
    static Map<Character, Integer> map = Map.of('E',0,'S',1,'W',2,'N',3);
    static char[] t = {'E','S','W','N'};
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    static String solve(int x, int y, int d, int r, int c, long l, char[][] mtx){
        String ans = "";
        long[][][] vis = new long[r][c][4];
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                Arrays.fill(vis[i][j], -1);
            }
        }
        int nx,ny;
        long cur = 0;
        boolean k = false;
        while(true){
            if(vis[x][y][d]!=-1 && !k){
                int nd = rotate(d);
                long add = vis[x][y][d];
                while((vis[x][y][d]!=add||vis[x][y][nd]!=cur)&&nd!=d){
                    nd=rotate(nd);
                }
                d=nd;
                long sub = cur-add;
                k=true;
                l=(l-add)%sub+add;
                cur=add;
            }

            if(cur == l){
                ans=(x+1)+" "+(y+1)+" "+t[d];
                break;
            }
            vis[x][y][d] = cur;
            nx=x+dx[d];
            ny=y+dy[d];
            if(nx<0||ny<0||nx>=r||ny>=c||mtx[nx][ny]=='#'){
                d=rotate(d);
                continue;
            }
            cur++;
            x=nx;
            y=ny;
        }


        return ans;
    }

    static int rotate(int d){
        return (d+1)%4;
    }



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int r, c;
        long l;
        char[][] mtx;
        String s;
        while(true){
            st = new StringTokenizer(br.readLine(), " ");
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            l = Long.parseLong(st.nextToken());
            if(r==0&&c==0&&l==0l) break;
            mtx = new char[r][c];

            Integer sx = null;
            Integer sy = null;
            Integer d = null;

            for(int i = 0; i < r; i++){
                s = br.readLine();
                for(int j = 0; j < c; j++){
                    char v = s.charAt(j);

                    if(v != '.' && v != '#'){
                        sx = i;
                        sy = j;
                        d = map.get(v);
                        v = '.';
                    }
                    mtx[i][j] = v;
                }
            }

            if(sx == null || sy == null || d == null) continue;
            sb.append(solve(sx, sy, d, r, c, l, mtx)).append("\n");

        }


        System.out.println(sb);
    }

    static void print(long[][][] vis, int r, int c){
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                System.out.print(Arrays.toString(vis[i][j])+" ");
            }
            System.out.println("");
            ;
        }
        System.out.println("+++++++++++=");
    }
}

/*
1 2 10000
.S
1 2 10000
.W
0 0 0
 */