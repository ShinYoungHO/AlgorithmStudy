package category.implementation2;
// 19238
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class StartTaxi {
    static int INF = Integer.MAX_VALUE;
    static int n,m,f;
    static int r,c;
    static int[][][] mtx;
    static int[] dx = new int[]{0, 1, 0, -1};
    static int[] dy = new int[]{1, 0, -1, 0};
    static int[] pad = new int[]{0, 0, 0, 0};
    static int solve(){
        Queue<int[]> q;
        int[] target, qv;
        boolean[][] visited;
        int x,y,d,nx,ny;
        while(m > 0){
            q = new LinkedList<>();
            visited = new boolean[n][n];
            q.add(new int[]{r, c, 0});
            visited[r][c] = true;
            target = new int[]{INF, INF, INF}; // 번호, 거리

            // 최단거리 손님찾기
            while(!q.isEmpty()){
                qv = q.poll();
                x = qv[0]; y = qv[1]; d = qv[2];
                if(mtx[x][y][0] == 2 && mtx[x][y][1] == 0 && d <= target[2]
                        && (x < target[0] || (x == target[0] && y < target[1]))
                ){
                    target = new int[]{ x, y, d, mtx[x][y][0]};
                    continue;
                }

                for(int i = 0; i < 4; i++){
                    nx = x + dx[i];
                    ny = y + dy[i];
                    if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                    if(visited[nx][ny] || mtx[nx][ny][0] == 1) continue;
                    visited[nx][ny] = true;

                    q.add(new int[]{nx, ny, d+1});
                }
            }

            // target : 손님
            r = target[0]; c = target[1]; f -= target[2];
            if(target[0] == INF || f <= 0) return -1;
            int v = BFS(mtx[r][c][2], mtx[r][c][3]);
            mtx[target[0]][target[1]] = pad;
            f -= v;
            if(v == -1 || f < 0) return -1;
            f+=v*2;

            m--;
        }

        return f;
    }

    static int BFS(int r1, int c1){
        int x,y,d,nx,ny;
        int[] qv;
        boolean[][] visited = new boolean[n][n];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r, c, 0});
        visited[r][c] = true;
        while(!q.isEmpty()){
            qv = q.poll();
            x = qv[0]; y = qv[1]; d = qv[2];
            if(x == r1 && y == c1) {
                r = x;
                c = y;
                return d;
            }
            for(int i = 0; i < 4; i++){
                nx = x + dx[i];
                ny = y + dy[i];
                if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                if(visited[nx][ny] || mtx[nx][ny][0] == 1) continue;

                visited[nx][ny] = true;
                q.add(new int[]{nx, ny, d+1});
            }
        }
        return -1;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        f = Integer.parseInt(st.nextToken());

        mtx = new int[n][n][2];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < n; j++){
                mtx[i][j] = new int[]{Integer.parseInt(st.nextToken()), 0, 0, 0};
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        r = Integer.parseInt(st.nextToken())-1;
        c = Integer.parseInt(st.nextToken())-1;

        int x1,y1,x2,y2;
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine(), " ");
            x1 = Integer.parseInt(st.nextToken())-1;
            y1 = Integer.parseInt(st.nextToken())-1;
            x2 = Integer.parseInt(st.nextToken())-1;
            y2 = Integer.parseInt(st.nextToken())-1;

            mtx[x1][y1] = new int[]{2, 0, x2, y2};
        }

        System.out.println(solve());
    }
}


