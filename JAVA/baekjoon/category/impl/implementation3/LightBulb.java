package category.implementation3;
// 11967
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class LightBulb {
    static ArrayList<int[]>[][] mtx;
    static int n;
    static int[] dx = new int[]{0, 1, 0, -1};
    static int[] dy = new int[]{1, 0, -1, 0};

    static int solve(int x1, int y1){
        int res = 1,x,y,nx,ny;
        boolean[][] isOn = new boolean[n][n];
        boolean[][] candidates = new boolean[n][n];
        boolean[][] visited = new boolean[n][n];

        Queue<int[]> q = new LinkedList<>(); //
        q.add(new int[]{x1, y1});
        visited[x1][y1] = true;
        isOn[x1][y1] = true;

        while(!q.isEmpty()){
            int[] qv = q.poll();
            x = qv[0]; y = qv[1];
            // 이동 가능한 방 업데이트
            for(int i = 0; i < 4; i++){
                nx = x + dx[i];
                ny = y + dy[i];
                if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                candidates[nx][ny] = true;
            }

            // 현재 방의 스위치 모두 키고, 방문한 적이 없으면서 이동 가능한 방인 경우 q에 추가.

            for(int[] crd : mtx[x][y]){
                nx = crd[0]; ny = crd[1];
                if(!isOn[nx][ny]){
                    isOn[nx][ny] = true;
                    res++;

                    if(candidates[nx][ny] && !visited[nx][ny]){
                        q.add(new int[]{nx,ny});
                        visited[nx][ny] = true;
                    }
                }
            }

            // 사방 탐색 및 추가
            for(int i = 0; i < 4; i++){
                nx = x + dx[i];
                ny = y + dy[i];
                if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                if(visited[nx][ny] || !candidates[nx][ny] || !isOn[nx][ny]) continue;
                q.add(new int[]{nx, ny});
                visited[nx][ny] = true;
            }

        }
        return res;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        mtx = new ArrayList[n][n];
        int c = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                mtx[i][j] = new ArrayList<>();
            }
        }

        for(int i = 0; i < c; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int x1 = Integer.parseInt(st.nextToken())-1;
            int y1 = Integer.parseInt(st.nextToken())-1;
            int x2 = Integer.parseInt(st.nextToken())-1;
            int y2 = Integer.parseInt(st.nextToken())-1;

            mtx[x1][y1].add(new int[]{x2, y2});
        }

        System.out.println(solve(0, 0));;
    }
}
