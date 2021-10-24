package category.etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Alphabet {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int r, c;
    static int max;
    static void solve(int[][] mtx, boolean[] visited, int x, int y, int cnt){
        int nx, ny;
        for(int i = 0; i < 4; i++){
            nx = x + dx[i];
            ny = y + dy[i];
            if(nx < 0 || ny < 0 || nx >= r || ny >= c) continue;
            if(visited[mtx[nx][ny]]) continue;
            visited[mtx[nx][ny]] = true;
            solve(mtx, visited, nx, ny, cnt+1);
            visited[mtx[nx][ny]] = false;
        }
        if(max < cnt) max = cnt;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        max = Integer.MIN_VALUE;
        // visited 대신 bitMask 가능
        boolean[] visited = new boolean[26];
        int[][] mtx = new int[r][c];

        for(int i = 0; i < r; i++){
            String s = br.readLine();
            for(int j = 0; j < c; j++){
                mtx[i][j] = s.charAt(j)-'0'-17;
            }
        }

        visited[mtx[0][0]] = true;

        solve(mtx, visited, 0, 0, 1);
        System.out.println(max);
    }
}
