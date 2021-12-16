package category.ns.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 배달__1175 {
    static int n,m, INF = Integer.MAX_VALUE;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static char[][] mtx;
    static char start='S',target='C',wall='#',empty='.';
    static int sx,sy;
    static List<int[]> targets;

    static int solve(){
        Queue<int[]> q = new LinkedList<>();
        boolean[][][][] visited = new boolean[n][m][4][4];

        q.add(new int[]{sx, sy, 0, -1, 0});

        int x,y,nx,ny,c,d,status;
        while(!q.isEmpty()){
            x = q.peek()[0];
            y = q.peek()[1];
            c = q.peek()[2];
            d = q.peek()[3]; // 이전 방향
            status = q.poll()[4];

            for(int i = 0; i < 4; i++){
                if(i == d) continue;
                nx = x+dx[i];
                ny = y+dy[i];
                int ns = status;
                if(isOOB(nx, ny) || isVisited(x, y, i, status, visited) || mtx[nx][ny] == wall) continue;
                if(nx==targets.get(0)[0]&&ny==targets.get(0)[1]){
                    ns |= 1;
                } else if(nx==targets.get(1)[0]&&ny==targets.get(1)[1]){
                    ns |= 2;
                }
                if(ns == 3) return c+1;
                q.add(new int[]{nx, ny, c+1, i, ns});
            }
        }
        return -1;
    }



    static boolean isVisited(int x, int y, int dir, int status, boolean[][][][] visited){
        if(visited[x][y][dir][status]) return true;
        visited[x][y][dir][status] = true;
        return false;
    }

    static boolean isOOB(int x, int y){
        return x<0||y<0||x>=n||y>=m;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        mtx = new char[n][m];
        targets = new ArrayList<>();
        for(int i = 0; i < n; i++){
            String s = br.readLine();
            for(int j = 0; j < m; j++){
                mtx[i][j] = s.charAt(j);
                if(mtx[i][j] == target) targets.add(new int[]{i, j});
                if(mtx[i][j] == start) {
                    sx = i;
                    sy = j;
                }
            }
        }
        System.out.println(solve());
    }
}
