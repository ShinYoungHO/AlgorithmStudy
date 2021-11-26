package category.ns;
// timeout
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class MooPath6 {
    static int n,r,k;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean[][][] path;
    static boolean[][] moo;
    static ArrayList<int[]> arr;
    static void solve(){
        int ans = 0;
        for(int i = 0; i < arr.size(); i++){
            int[] arrV = arr.get(i);
            Queue<int[]>q = new LinkedList<>();
            boolean[][][] vis = new boolean[n][n][2];
            q.add(new int[]{arrV[0], arrV[1], 0});
            vis[arrV[0]][arrV[1]] = new boolean[]{ true, true };
            while(!q.isEmpty()){
                int[] qv = q.poll();
                int x = qv[0];
                int y = qv[1];
                int passed = qv[2];
                for(int j = 0; j < 4; j++){
                    int nx = x+dx[j];
                    int ny = y+dy[j];
                    if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                    if(passed == 0 && vis[nx][ny][0]) continue;
                    if(passed == 1 && vis[nx][ny][1]) continue;

                    if(path[x][y][j] || passed == 1){
                        if(moo[nx][ny]) ans++;
                        vis[nx][ny][1] = true;
                        q.add(new int[]{nx, ny, 1});
                    } else {
                        if(moo[nx][ny]) ans--;
                        vis[nx][ny][0] = true;
                        q.add(new int[]{nx, ny, 0});
                    }
                }
            }
        }
        System.out.println(ans);
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        arr = new ArrayList<>();

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
                    path[r1][c1][j] = true;
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
            arr.add(new int[]{x, y});
        }

        solve();
    }

    static int parseInt(StringTokenizer st){
        return Integer.parseInt(st.nextToken());
    }
}
