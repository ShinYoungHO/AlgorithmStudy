package category.ns.solved;
// 1113 fail
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 수영장만들기_1113 {
    static int r,c;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    static void solve(int[][] mtx){
        int ans = 0;
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                ans += BFS(i, j, mtx);
            }
        }
        System.out.println(ans);
    }

    static int BFS(int x1, int y1, int[][] mtx){
        boolean[][] vis = new boolean[r][c];
        List<int[]> arr = new ArrayList<>();
        arr.add(new int[]{x1, y1});
        vis[x1][y1] = true;

        int x,y,nx,ny;
        int min = Integer.MAX_VALUE;
        int start = mtx[x1][y1]+1;
        for(int j = 0; j < arr.size(); j++){
            int[] qv = arr.get(j);
            x = qv[0];
            y = qv[1];
            for(int i = 0; i < 4; i++){
                nx = x+dx[i];
                ny = y+dy[i];
                if(nx<0||ny<0||nx>=r||ny>=c) return 0;
                if(vis[nx][ny]) continue;
                if(mtx[nx][ny]>=start) {
                    min = Math.min(min, mtx[nx][ny]);
                    continue;
                }
                arr.add(new int[]{nx, ny});
                vis[nx][ny] = true;
            }
        }
        int ans = 0;

        for(int i = 0; i < arr.size(); i++){
            x = arr.get(i)[0];
            y = arr.get(i)[1];
            ans+=min-mtx[x][y];
            mtx[x][y] = min;
        }
        return ans;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        int[][] mtx = new int[r][c];

        for(int i = 0; i < r; i++){
            String l = br.readLine();
            for(int j = 0; j < c; j++){
                mtx[i][j] = l.charAt(j)-'0';
            }
        }

        solve(mtx);
    }
}

