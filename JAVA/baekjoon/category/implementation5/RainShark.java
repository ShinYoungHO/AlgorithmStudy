package category.implementation5;
// 21610
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class RainShark {
    static int n, k, pN;
    static int[][][] crd;
    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static Queue<int[]> q;

    static void solve(int[][] mtx,int d, int s){
        boolean[][] visited = new boolean[n][n];

        moveCloud(visited, d, s);

        rain(visited, mtx);

        duplicate(mtx, visited);

        generate(mtx, visited);
    }

    static void generate(int[][] mtx, boolean[][] visited){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(!visited[i][j] && mtx[i][j] >= 2){
                    q.add(crd[i][j]);
                    mtx[i][j]-=2;
                }
            }
        }
    }

    static void rain(boolean[][] visited ,int[][] mtx){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(visited[i][j]) mtx[i][j]++;
            }
        }
    }

    static void duplicate(int[][] mtx, boolean[][] visited){
        int x,y,nx,ny;
        Map<int[], Integer> tmp = new HashMap<>();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(visited[i][j]){
                    x = i;
                    y = j;
                    int cnt = 0;
                    for(int k = 1; k < 8; k+=2){
                        nx = x+dx[k];
                        ny = y+dy[k];
                        if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                        if(mtx[nx][ny] > 0) cnt++;
                    }
                    if(cnt > 0) tmp.put(crd[x][y], cnt);
                }
            }
        }
        tmp.keySet().forEach(k -> mtx[k[0]][k[1]]+=tmp.get(k));
    }

    static void moveCloud(boolean[][] visited,int d, int s){
        int x,y;
        while(!q.isEmpty()){
            int[] qv = q.poll();
            x = (qv[0]+dx[d]*s+pN)%n;
            y = (qv[1]+dy[d]*s+pN)%n;
            visited[x][y] = true;
        }
    }

    static void getCloud(){
        q.add(crd[n-1][0]);
        q.add(crd[n-1][1]);
        q.add(crd[n-2][0]);
        q.add(crd[n-2][1]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        int[][] mtx = new int[n][n];
        crd = new int[n][n][2];
        pN = 0;
        while(pN <= 50) pN+=n;

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                mtx[i][j] = Integer.parseInt(st.nextToken());
                crd[i][j][0] = i;
                crd[i][j][1] = j;
            }
        }

        q = new LinkedList<>();
        getCloud();

        for(int i = 0; i < k; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int d = Integer.parseInt(st.nextToken())-1;
            int s = Integer.parseInt(st.nextToken());
            solve(mtx, d, s);
        }

        int res = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                res+=mtx[i][j];
            }
        }
        System.out.println(res);
    }
}

// 35/23