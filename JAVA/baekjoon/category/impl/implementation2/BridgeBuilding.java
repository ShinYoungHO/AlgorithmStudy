package category.implementation2;
// 17472  - Fail

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BridgeBuilding {
    static int INF = 200;
    static int r,c;
    static int[][] mtx, graph;
    static boolean[][] visited;
    static int[] dx = new int[]{0, 1, 0, -1};
    static int[] dy = new int[]{1, 0, -1, 0};

    // E logE
    static int kruskal(){
        int res = 0;
        int n = findNodes();
        graph = generateGraph(n);

        int from, to, v;
        boolean[][] paths = new boolean[n][n];
        boolean[] nodes = new boolean[n];

        int[] qv;
        int[] parents = new int[n];
        for(int i = 0; i < n; i++) parents[i] = i;

        PriorityQueue<int[]> pq = new PriorityQueue<>((int[]n1, int[]n2) -> n1[2]-n2[2]);

        // 간선 모두 pq에 add;

        for(int i = 1; i < n; i++){
            for(int j = 1; j < n; j++){
                if(i==j||paths[i][j]) continue;
                paths[i][j] = true;
                pq.add(new int[]{i, j, graph[i][j]});
            }
        }

        // pq에서 뽑아와서 해당 간선의 부모가 같은지 확인
        while(!pq.isEmpty()){
            qv = pq.poll();
            from = qv[0]; to = qv[1]; v = qv[2];
            if(graph[from][to] == INF) continue;
            if(find(from,parents) != find(to, parents)) {
                union(parents, from, to);
                res += v;
                nodes[from] = true;
                nodes[to] = true;
            }
        }
        if(isFinished(nodes, n)) return res;
        return -1;
    }

    static boolean isFinished(boolean[] nodes, int n){
        for(int i = 1; i < n; i++){
            if(!nodes[i]) return false;
        }
        return true;
    }

    static int find(int u, int[] parent){
        if (u == parent[u]) return u;
        return parent[u] = find(parent[u], parent);
    }

    static void union(int[] parent, int n1, int n2){
        // n1
        int u = find(n1, parent);
        int v = find(n2, parent);

        // 사이클 존재 여부 확인 코드
        if (u == v)
            return ;

        if(u < v) parent[v] = u;
        else parent[u] = v;

    }

    static int[][] generateGraph(int n){
        int[][] res = new int[n][n];
        for(int i = 0; i < n; i++) Arrays.fill(res[i], INF);
        int x,y,cur,next;
        int dst;
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                if(mtx[i][j] == 0) continue;
                cur = mtx[i][j];
                for(int k = 0; k < 4; k++){
                    x = i; y = j;
                    while(true){

                        x+=dx[k];
                        y+=dy[k];
                        if(x < 0 || y < 0 || x >= r || y >= c) break;
                        if(mtx[x][y] == cur) break;
                        if(mtx[x][y] == 0) continue;
                        next = mtx[x][y];
                        dst = Math.abs(i-x)+Math.abs(j-y)-1;

                        if(res[cur][next] > dst && dst >= 2) {
                            res[cur][next] = dst;
                            res[next][cur] = dst;
                        }
                        break;
                    }
                }

            }
        }
        return res;
    }
    static int findNodes(){
        int res = 1;
        visited = new boolean[r][c];
        Queue<int[]> q;
        int x,y,nx,ny;
        int[] cur;

        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                if(visited[i][j] || mtx[i][j] == 0) continue;
                q = new LinkedList<>();
                q.add(new int[]{i,j});
                mtx[i][j] = res;
                visited[i][j] = true;
                while(!q.isEmpty()){
                    cur = q.poll();
                    x = cur[0];
                    y = cur[1];
                    for(int m  = 0; m < 4; m++){
                        nx = x + dx[m];
                        ny = y + dy[m];

                        if(nx < 0 || ny < 0 || nx >= r || ny >= c) continue;
                        if(visited[nx][ny]) continue;
                        if(mtx[nx][ny] == 0) continue;

                        visited[nx][ny] = true;
                        q.add(new int[]{nx,ny});
                        mtx[nx][ny] = res;
                    }
                }
                res++;
            }
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        mtx = new int[r][c];

        for(int i = 0; i < r; i++){
            st = new StringTokenizer(br.readLine()," ");
            for(int j = 0; j < c; j++){
                mtx[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(kruskal());
    }

    static void p(int[][] res){

        for(int i = 0; i < res.length; i++){
            System.out.println(Arrays.toString(res[i]));
        }
    }
}
