package category.implementation3;
// 18809
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Gaaarden {
    static int n,m,g,r;
    static int[][] garden;
    static int[] dx = new int[]{0, 1, 0, -1};
    static int[] dy = new int[]{1, 0, -1, 0};
    static ArrayList<int[]> candidates = new ArrayList<>();
    static int ans = 0;
    static int INF = Integer.MAX_VALUE;

    static int solve(){
        Queue<int[]> q = new LinkedList<>();
        int res = 0;
        int[][][] tc = new int[n][m][2];
        int x,y,dst,col,nx,ny;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                tc[i][j][0] = garden[i][j];
                tc[i][j][1] = INF;
                if(garden[i][j] >= 3){
                    q.add(new int[]{i, j ,garden[i][j], 0});
                }
            }
        }

        while(!q.isEmpty()){
            int[] qv = q.poll();
            x = qv[0]; y = qv[1]; col = qv[2]; dst = qv[3];
            if(tc[x][y][0] == 5) continue;
            for(int i = 0; i < 4; i++){
                nx = x + dx[i];
                ny = y + dy[i];
                if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if(tc[nx][ny][1] < dst+1 ) continue;
                int nCol = tc[nx][ny][0];
                if(nCol == 0) continue;
                else if(nCol == 1 || nCol == 2){
                    // 동시에 도착하는 경우는 꽃이 폈는지 검사에서 거름
                    tc[nx][ny][0] = col;
                    tc[nx][ny][1] = dst+1;
                    q.add(new int[]{nx, ny, col, dst+1});
                } else if(nCol == 3 || nCol == 4){
                    if(nCol != col && dst+1 == tc[nx][ny][1]){
                        res++;
                        tc[nx][ny][0] = 5;
                    }
                }
            }
        }
        return res;
    }

    static void ret(int idx, int max, int g, int r){

        if(g == 0 && r == 0){
            int v = solve();
            if(v > ans) ans = v;
            return;
        }
        if(idx == max) return;

        ret(idx+1, max, g, r);

        if(g > 0){
            int[] crd = candidates.get(idx);
            garden[crd[0]][crd[1]] = 3;
            ret(idx+1,max, g-1, r);
            garden[crd[0]][crd[1]] = 2;
        }
        if(r > 0){
            int[] crd = candidates.get(idx);
            garden[crd[0]][crd[1]] = 4;
            ret(idx+1,max, g, r-1);
            garden[crd[0]][crd[1]] = 2;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        g = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        garden = new int[n][m];
        candidates = new ArrayList<>();

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < m; j++){
                int v = Integer.parseInt(st.nextToken());
                if(v == 2){
                    candidates.add(new int[]{i, j});
                }
                garden[i][j] = v;
            }
        }
        ret(0, candidates.size(), g, r);
        System.out.println(ans);
    }
}
