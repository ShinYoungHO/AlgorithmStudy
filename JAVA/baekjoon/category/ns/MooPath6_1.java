package category.ns;
// timeout

import java.io.*;
import java.util.*;

public class MooPath6_1 {
    static int n,r,k;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean[][][] path;
    static boolean[][] moo;

    static void solve(){
        int ans = 0;
        boolean[][] vis = new boolean[n][n];
        ArrayList<Integer> nodes = new ArrayList<>();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(vis[i][j]) continue;
                int tmp = 0;
                if(moo[i][j]) tmp++;
                vis[i][j] = true;
                Queue<int[]> q = new LinkedList<>();
                q.add(new int[]{i, j});
                while(!q.isEmpty()){
                    int[] qv = q.poll();
                    int x = qv[0];
                    int y = qv[1];
                    for(int k = 0; k < 4; k++){
                        int nx = x+dx[k];
                        int ny = y+dy[k];
                        if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                        if(vis[nx][ny]) continue;
                        if(path[x][y][k]) continue;
                        q.add(new int[]{nx, ny});
                        vis[nx][ny] = true;
                        if(moo[nx][ny]) tmp++;
                    }
                }
                nodes.add(tmp);
            }
        }

        for(int i = 0; i < nodes.size()-1; i++){
            for(int j = i+1; j < nodes.size(); j++){
                ans += nodes.get(i)*nodes.get(j);
            }
        }
        System.out.println(ans);
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = parseInt(st);
        r = parseInt(st);
        k = parseInt(st);

        path = new boolean[n][n][4];
        moo = new boolean[n][n];

        int r1,c1,r2,c2;
        for(int i = 0; i < r; i++){
            st = new StringTokenizer(br.readLine(), " ");
            r1 = parseInt(st)-1;
            c1 = parseInt(st)-1;
            r2 = parseInt(st)-1;
            c2 = parseInt(st)-1;

            for(int j = 0; j < 4; j++){
                int nr = r1+dx[j];
                int nc = c1+dy[j];
                if(nr == r2 && nc == c2){
                    path[r1][c1][j] = true;
                    break;
                }
            }
            for(int j = 0; j < 4; j++){
                int nr = r2+dx[j];
                int nc = c2+dy[j];
                if(nr == r1 && nc == c1){
                    path[r2][c2][j] = true;
                    break;
                }
            }
        }

        int x,y;
        for(int i = 0; i < k; i++){
            st = new StringTokenizer(br.readLine(), " ");
            x = parseInt(st)-1;
            y = parseInt(st)-1;
            moo[x][y] = true;
        }

        solve();
    }

    static int parseInt(StringTokenizer st){
        return Integer.parseInt(st.nextToken());
    }
}
