package category.bfs;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class ThroughTheWall {
    static int[] dx = new int[]{0, 1, 0, -1};
    static int[] dy = new int[]{1, 0, -1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        char[][] mtx = new char[n][m];
        boolean[][][] visited = new boolean[n][m][2];
        visited[0][0][0] = true;

        for(int i = 0; i < n; i++){
            String str = br.readLine();
            for(int j = 0; j < m; j++){
                mtx[i][j] = str.charAt(j);
            }
        }

        System.out.println(BFS(mtx, visited, n, m));;
    }

    static int BFS(char[][]mtx,boolean[][][] visited, int r, int c){
        Queue<int[]> q = new LinkedList<>();
        if(r == 1 && c == 1) return 1;
        q.add(new int[]{0, 0, 1, 0});

        while(!q.isEmpty()){
            int[] qv = q.poll();
            int x = qv[0];
            int y = qv[1];
            int cnt = qv[2];
            int passed = qv[3];
            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                System.out.println(nx+":"+ny);
                if(nx < 0 || ny < 0 || nx >= r || ny >= c) continue;

                if(nx == r-1 && ny == c-1) return cnt+1;
                if(mtx[nx][ny] == '1'){
                    if(passed == 0 && !visited[nx][ny][1]){
                        visited[nx][ny][1] = true;
                        q.add(new int[]{nx, ny, cnt+1, passed+1});
                    }
                    else continue;
                } else {
                    if(passed == 0 && !visited[nx][ny][0]){
                        q.add(new int[]{nx, ny, cnt+1, passed});
                        visited[nx][ny][0] = true;
                    } else if(passed == 1 && !visited[nx][ny][1]){
                        q.add(new int[]{nx, ny, cnt+1, passed});
                        visited[nx][ny][1] = true;
                    }
                }
            }
        }
        return -1;
    }
}