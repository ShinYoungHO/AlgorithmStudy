package category.implementation2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Fire {
    static int r,c;
    static char[][] mtx;
    static boolean[][] visited;
    static Queue<Coord> fire;
    static Queue<Coord> path;
    static int[] dx = new int[]{0, 1, 0, -1};
    static int[] dy = new int[]{1, 0, -1, 0};
    static String imp = "IMPOSSIBLE\n";

    // 다음 값 기준으로 답을 도출해 내고 있는 데 현재 값이 답이 될 수 있었음.
    static void solve(StringBuilder sb){
        int x,y,d;
        int nx,ny;
        Coord cur;

        int cnt;
        while(!path.isEmpty()){
            // fire
            cnt = fire.size();
            for(int i = 0; i < cnt; i++){
                cur = fire.poll();
                x = cur.x; y = cur.y; d = cur.d;

                for(int j = 0; j < 4; j++){
                    nx = x + dx[j];
                    ny = y + dy[j];
                    if(nx < 0 || ny < 0 || nx >= r || ny >= c) continue;
                    if(mtx[nx][ny] == '#' || mtx[nx][ny] == '*') continue;
                    fire.add(new Coord(nx, ny, d+1));
                    mtx[nx][ny] = '*';
                }
            }
            // path
            cnt = path.size();
            for(int i = 0; i < cnt; i++){
                cur = path.poll();
                x = cur.x; y = cur.y; d = cur.d;
                if(x == 0 || y == 0 || x == r-1 || y == c-1) {
                    sb.append(d+1).append("\n");
                    return;
                }
                for(int j = 0; j < 4; j++){
                    nx = x + dx[j];
                    ny = y + dy[j];
                    if(nx < 0 || ny < 0 || nx >= r || ny >= c) continue;
                    if(mtx[nx][ny] == '#' || mtx[nx][ny] == '*') continue;
                    if(visited[nx][ny]) continue;

                    path.add(new Coord(nx, ny,d+1));
                    visited[nx][ny] = true;
                }

            }
        }

        sb.append(imp);
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());
        String input;

        for(int i = 0; i < tc; i++){
            st = new StringTokenizer(br.readLine(), " ");
            c = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());

            mtx = new char[r][c];
            visited = new boolean[r][c];
            fire = new LinkedList<>();
            path = new LinkedList<>();

            for(int j = 0; j < r; j++){
                input = br.readLine();
                for(int k = 0; k < c; k++){
                    char v = input.charAt(k);
                    mtx[j][k] = v;
                    if(v == '*') fire.add(new Coord(j,k,0));
                    else if(v == '@') {
                        path.add(new Coord(j, k,0));
                        visited[j][k] = true;
                    }
                }
            }

            solve(sb);
        }
        System.out.println(sb);
    }

    private static class Coord{
        int x,y,d;
        private Coord(int x, int y, int d){
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
}
