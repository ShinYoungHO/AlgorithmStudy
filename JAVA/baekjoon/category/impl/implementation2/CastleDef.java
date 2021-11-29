package category.implementation2;
// 17135
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CastleDef {
    static int[] dx = new int[]{0, -1, 0};
    static int[] dy = new int[]{-1, 0, 1};
    static int n,m,d,res;
    static int[][] mtx,tc;
    static boolean[][] visited;
    static boolean[] archers;
    static Stack<int[]> stack = new Stack<>();

    static int solve(){
        int result = 0;
        int row = n;

        tc = slice();


        while(row > 0){
            for(int i = 0; i < m; i++){
                if(archers[i]) {
                    result += BFS(row, i);
                }
            }
            row--;
            for(int i = 0; i < m; i++){
                tc[row][i] = 0;
            }
            while(!stack.isEmpty()) {
                int[] crd = stack.pop();
                tc[crd[0]][crd[1]] = 0;
            }
        }
        return result;
    }

    static int BFS(int row, int col){
        int x, y, nx, ny, c;
        Queue<int[]>q = new LinkedList<>();
        q.add(new int[]{row, col, 0});
        visited = new boolean[n+1][m];
        visited[row][col] = true;
        while(!q.isEmpty()){
            int[] qv = q.poll();
            x = qv[0];
            y = qv[1];
            c = qv[2];
            if(tc[x][y] >= 1) {
                tc[x][y]++;
                stack.push(new int[]{x, y});
                // 더한 값이 2면 살아있는 적을 죽인 경우 : 카운트를 위해 +1
                // 그 이상이면 이미 죽은 적을 죽인 경우 : 카운트하면 안되므로 0
                return tc[x][y] == 2 ? 1 : 0;
            }
            for(int j = 0; j < 3; j++){
                nx = x + dx[j];
                ny = y + dy[j];
                if(nx < 0 || ny < 0 || nx >= row || ny >= m) continue;
                if(visited[nx][ny] || c == d) continue;

                q.add(new int[]{nx, ny, c+1});
                visited[nx][ny] = true;
            }
        }
        return 0;
    }

    static void DFS(int c, int n){
        if(n == 0){
            int k = solve();
            if(k > res) res = k;
            return;
        }

        for(int i = c; i < m; i++){
            archers[i] = true;
            DFS(i+1, n-1);
            archers[i] = false;
        }
    }

    static int[][] slice(){
        int[][] res = new int[n+1][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                res[i][j] = mtx[i][j];
            }
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        archers = new boolean[m];
        res = 0;
        mtx = new int[n][m];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < m; j++){
                mtx[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        DFS(0, 3);
        System.out.println(res);
    }
}
/**
 6 5 2
 1 0 1 0 1
 0 1 0 1 0
 1 1 0 0 0
 0 0 0 1 1
 1 1 0 1 1
 0 0 1 0 0





 6 0 1 0 1
 5 1 0 1 0
 4 1 0 0 0
 3 0 0 1 1
 2 3 0 1 1
 1 2 3 0 0
 x 1 2 3 4
 */