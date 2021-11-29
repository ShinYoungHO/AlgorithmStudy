package category.implementation6;

import java.io.*;
import java.util.*;

public class Lab {
    static int r,c,min,wall;
    static int[][] mtx;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static ArrayList<int[]> candi, virus;

    static void solve(){
        int v;
        candi = new ArrayList<>();
        virus = new ArrayList<>();
        min = Integer.MAX_VALUE;
        wall = 0;
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                v = mtx[i][j];
                if(v == 2) virus.add(new int[]{i, j});
                else if(v == 0) candi.add(new int[]{i, j});
                else wall++;
            }
        }

        ret(0, candi.size(), 3);
    }

    static void ret(int st, int end, int cnt){

        if(cnt == 0){
            Queue<int[]> q = new LinkedList<>();
            boolean[][] visited = new boolean[r][c];
            for(int i = 0; i < virus.size(); i++){
                int[] k = virus.get(i);
                q.add(k);
                visited[k[0]][k[1]] = true;
            }

            int x,y,nx,ny;
            int res = virus.size();
            while(!q.isEmpty()){
                int[] qv = q.poll();
                x = qv[0];
                y = qv[1];
                for(int i = 0; i < 4; i++){
                    nx = x+dx[i];
                    ny = y+dy[i];
                    if(OOB(nx,ny) || visited[nx][ny] || mtx[nx][ny] == 1) continue;
                    visited[nx][ny] = true;
                    res++;
                    if(res > min) return;
                    q.add(new int[]{nx,ny});
                }
            }
            min = res;
            return;
        }
        for(int i = st; i < end; i++){
            int[] crd = candi.get(i);
            mtx[crd[0]][crd[1]] = 1;
            ret(i+1, end, cnt-1);
            mtx[crd[0]][crd[1]] = 3;
        }
    }

    static boolean OOB(int x, int y){
        if(x < 0 || y < 0 || x >= r || y >= c) return true;
        return false;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        mtx = new int[r][c];
        for(int i = 0; i < r; i++){
            st = new StringTokenizer(br.readLine()," ");
            for(int j = 0; j < c; j++){
                mtx[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        solve();
        System.out.println(r*c - min - wall - 3);
    }
}
