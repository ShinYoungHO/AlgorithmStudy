package category.implementation5;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class FireStormShark {
    static int N,n,q;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int max;

    static void solve(int[][] mtx, int depth){
        int pLen = 1 << depth;
        int pCnt = N/pLen;
        int t = pCnt*pCnt;

        for(int i = 0; i < t; i++){
            int r = i%pCnt;
            int c = i/pCnt;
            rotate(r, c, pLen, mtx);
        }


        melt(mtx);
    }

    static void melt(int[][] mtx){
        Queue<int[]> q = new LinkedList<>();
        int x,y,nx, ny;
        int cnt;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                x = i; y = j; cnt = 0;
                if(mtx[x][y] == 0) continue;
                for(int k = 0; k < 4; k++){
                    nx = x+dx[k];
                    ny = y+dy[k];
                    if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                    if(mtx[nx][ny] > 0) cnt++;
                }
                if(cnt < 3) q.add(new int[] {x, y});
            }
        }

        while(!q.isEmpty()) {
            int[] t = q.poll();
            mtx[t[0]][t[1]]--;
        }
    }

    static void rotate(int r, int c, int l, int[][] mtx){
        int[][] tmp = new int[l][l];
        int sr = r*l;
        int sc = c*l;
        for(int i = 0; i < l; i++){
            for(int j = 0; j < l; j++){
                tmp[j][l-i-1] = mtx[sr+i][sc+j];
            }
        }
        for(int i = 0; i < l; i++){
            for(int j = 0; j < l; j++){
                mtx[sr+i][sc+j] = tmp[i][j];
            }
        }
    }

    static void getResult(int[][] mtx){
        boolean[][] visited = new boolean[N][N];
        int res = 0;

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(!visited[i][j] && mtx[i][j] != 0) {
                    res += BFS(visited, mtx, i, j);
                }
            }
        }

        System.out.println(res+"\n"+max);
    }

    static int BFS(boolean[][] visited, int[][] mtx, int r, int c){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r, c});
        int res = mtx[r][c];
        int size = 1;
        visited[r][c] = true;
        int x,y,nx,ny;

        while(!q.isEmpty()){
            int[] qv = q.poll();
            x = qv[0];
            y = qv[1];
            for(int i = 0; i < 4; i++){
                nx = x+dx[i];
                ny = y+dy[i];
                if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if(visited[nx][ny]) continue;
                if(mtx[nx][ny] == 0) continue;
                visited[nx][ny] = true;
                q.add(new int[]{nx, ny});
                res+=mtx[nx][ny];
                size++;
            }
        }
        if(max < size) max = size;
        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        N = (int) Math.pow(2, n);

        int[][] mtx = new int[N][N];
        max = 0;

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j++){
                mtx[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        while(st.hasMoreTokens()){
            int l = Integer.parseInt(st.nextToken());
            solve(mtx, l);
        }

        getResult(mtx);
    }

}
