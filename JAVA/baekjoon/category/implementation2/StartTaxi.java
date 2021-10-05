package category.implementation2;
// 19238 Fail
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class StartTaxi {
    static int n,m,f;
    static int r,c;
    static int[][][] mtx;
    static int[] dx = new int[]{0, 1, 0, -1};
    static int[] dy = new int[]{1, 0, -1, 0};

    static int solve(){
        Queue<int[]> q;

        int x,y,d,nx,ny;

        while(m != 0){
            boolean[][] visited = new boolean[n][n];
            q = new LinkedList<>();
            q.add(new int[]{r, c, 0});
            visited[r][c] = true;
            int[] target = new int[]{401,0, 0, 0};
            int[] qv;

            while(!q.isEmpty()){
                int len = q.size();
                for(int i = 0; i < len; i++){
                    qv = q.poll();
                    x = qv[0]; y = qv[1]; d = qv[2];

                    for(int j = 0; j < 4; j++){
                        nx = x + dx[j];
                        ny = y + dy[j];
                        if(nx<0||ny<0||nx>=n||ny>=n) continue;
                        if(visited[nx][ny] || mtx[nx][ny][0] == 1) continue;

                        if(mtx[nx][ny][0] > 1 && mtx[nx][ny][0] < target[0] && mtx[nx][ny][1] == 0) {
                            target = new int[]{mtx[nx][ny][0], nx, ny, d+1};
                            r = nx;
                            c = ny;
                            continue;
                        }
                        if(target[0] != 401) continue;
                        q.add(new int[]{nx, ny, d+1});
                        visited[nx][ny] = true;
                    }
                }
            }

            if(target[0] <= 400){
                f-=target[3];
                if(f <= 0) return -1;
                mtx[target[1]][target[2]] = new int[]{0, 0};
                q = new LinkedList<>();
                q.add(new int[]{r, c, 0});
                visited = new boolean[n][n];
                visited[r][c] = true;

                while(!q.isEmpty()){
                    qv = q.poll();
                    x = qv[0];
                    y = qv[1];
                    d = qv[2];

                    if(target[0] == mtx[x][y][0]) {
                        f-=d;

                        if(f >= 0) f+=d*2;
                        else return -1;


                        m--;
                        r = x;
                        c = y;
                        mtx[r][c] = new int[]{0, 0};
                        break;
                    }

                    for(int j = 0; j < 4; j++){
                        nx = x + dx[j];
                        ny = y + dy[j];
                        if(nx<0||ny<0||nx>=n||ny>=n) continue;
                        if(visited[nx][ny] || mtx[nx][ny][0] == 1) continue;
                        q.add(new int[]{nx, ny, d+1});
                        visited[nx][ny] = true;
                    }

                }
            } else return -1;

        }
        return f >= 0 ? f : -1;
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
                mtx[i][j] = new int[]{Integer.parseInt(st.nextToken()), 0};
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        r = Integer.parseInt(st.nextToken())-1;
        c = Integer.parseInt(st.nextToken())-1;

        int x1,y1,x2,y2,n=2;
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine(), " ");
            x1 = Integer.parseInt(st.nextToken())-1;
            y1 = Integer.parseInt(st.nextToken())-1;
            x2 = Integer.parseInt(st.nextToken())-1;
            y2 = Integer.parseInt(st.nextToken())-1;

            mtx[x1][y1] = new int[]{n, 0};
            mtx[x2][y2] = new int[]{n, 1};
            n++;
        }

        System.out.println(solve());
    }
}
