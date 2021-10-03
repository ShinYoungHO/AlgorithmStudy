package category.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Population {
    static int n,l,r;
    static int[][] mtx1;
    static int[][] mtx2;
    static boolean flipped = false;
    static int[] dx = new int[]{0, 1, 0, -1};
    static int[] dy = new int[]{1, 0, -1, 0};

    static int solve(){
        int res = 0;
        while(true){
            boolean changed = false;
            boolean[][] visited = new boolean[n][n];
            int[][] from, to;


            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(visited[i][j]) continue;

                    if(!flipped){
                        from = mtx1;
                        to = mtx2;
                    } else {
                        from = mtx2;
                        to = mtx1;
                    }
                    if(!changed) changed = BFS(i, j, visited, from, to);
                    else BFS(i, j, visited, from, to);

                }
            }
            flipped = !flipped;
            if(!changed) break;
            res++;
        }
        return res;
    }

    static boolean BFS(int r1, int c1, boolean[][] visited, int[][] from, int[][] to){
        Queue<Node> q = new LinkedList<>();
        ArrayList<Node> arr = new ArrayList<>();
        boolean isChanged = false;
        int x, y, nx, ny;
        int diff;
        int total=from[r1][c1], cnt=1;

        Node cur, next;

        cur = new Node(r1, c1);
        q.add(cur);
        arr.add(cur);
        visited[r1][c1] = true;

        while(!q.isEmpty()){
            cur = q.poll();
            x = cur.x;
            y = cur.y;

            for(int i = 0; i < 4; i++){
                nx = x + dx[i];
                ny = y + dy[i];
                if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                if(visited[nx][ny]) continue;
                diff = Math.abs(from[x][y]-from[nx][ny]);

                if(diff < l || diff > r) continue;
                next = new Node(nx, ny);
                total+=from[nx][ny];
                cnt++;
                q.add(next);
                visited[nx][ny] = true;
                arr.add(next);
            }
        }

        if(cnt > 1) {
            isChanged = true;
        }
        int avg = total/cnt;
        for(int i = 0; i < arr.size(); i++){
            cur = arr.get(i);
            to[cur.x][cur.y] = avg;
        }
        return isChanged;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        mtx1 = new int[n][n];
        mtx2 = new int[n][n];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < n; j++){
                int v = Integer.parseInt(st.nextToken());
                mtx1[i][j] = v;
            }
        }

        System.out.println(solve());
    }

    private static class Node{
        int x,y;
        private Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

}
