package category.ns.implementation1129;

import java.io.*;
import java.util.*;

public class 빙산_2573 {
    static int r,c;
    static int cnt;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int k;
    static int solve(int[][] map, List<int[]> ice){
        k = 0;
        if(r==1&&c==1) return k;
        while(true){
            int[] tmp = null;
            for(int i = 0; i < ice.size(); i++){
                int[] t = ice.get(i);
                int x = t[0];
                int y = t[1];
                if(map[x][y] != 0) {
                    tmp = t;
                    break;
                }
            }
            if(tmp == null) return 0;
            boolean t = BFS(tmp[0], tmp[1], map);

            if(!t) return k;
        }

    }
    static boolean BFS(int sx, int sy, int[][] map){
        boolean[][] vis = new boolean[r][c];
        int[] sub = new int[cnt];

        List<int[]> arr = new ArrayList<>();
        arr.add(new int[]{sx, sy});
        vis[sx][sy] = true;

        int x,y,nx,ny;

        for(int j = 0; j < arr.size(); j++){
            int[] t = arr.get(j);
            x = t[0];
            y = t[1];
            for(int i = 0; i < 4; i++){
                nx = x+dx[i];
                ny = y+dy[i];
                if(nx<0||ny<0||nx>=r||ny>=c||vis[nx][ny]) continue;
                if(map[nx][ny]==0) {
                    sub[j]++;
                    continue;
                }
                vis[nx][ny] = true;
                arr.add(new int[]{nx, ny});
            }
        }

        if(arr.size() != cnt) return false;

        k++;
        for(int i = 0; i < arr.size(); i++){
            int[] t = arr.get(i);
            x = t[0];
            y = t[1];
            int v = map[x][y]-sub[i];
            map[x][y] = Math.max(v, 0);
            if(map[x][y]==0) cnt--;
        }


        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        cnt = 0;

        int[][] map = new int[r][c];
        List<int[]> ice = new ArrayList<>();
        for(int i = 0; i <  r; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < c; j++){
                int v = Integer.parseInt(st.nextToken());
                if(v != 0) {
                    cnt++;
                    ice.add(new int[]{i, j});
                }
                map[i][j] = v;
            }
        }
        System.out.println(solve(map, ice));
    }
}
