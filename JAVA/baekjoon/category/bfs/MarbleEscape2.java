package category.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MarbleEscape2 {
    static int m;
    static int n;
    static int[] dx = new int[]{0, 1, 0, -1};
    static int[] dy = new int[]{1, 0, -1, 0};

    static int rx;
    static int ry;
    static int bx;
    static int by;

    static boolean[][][][] visited;
    static char[][] mtx;
    static Queue<Pos> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        mtx = new char[m][n];
        visited = new boolean[m][n][m][n];


        for(int i = 0; i < m; i++){
            String line = br.readLine();
            for(int j = 0; j < n; j++){
                char v = line.charAt(j);
                if(v == 'R'){
                    rx = i;
                    ry = j;
                    mtx[i][j] = '.';
                } else if(v == 'B'){
                    bx = i;
                    by = j;
                    mtx[i][j] = '.';
                } else {
                    mtx[i][j] = v;
                }
            }
        }
        setVisited();
        q = new LinkedList<>();
        addQ(0);
        BFS();
    }

    static boolean checkVisited(){
        return visited[rx][ry][bx][by];
    }

    static void setVisited(){
        visited[rx][ry][bx][by] = true;
    }

    static void addQ(int v){
        q.add(new Pos(rx, ry, bx, by, v));
    }
    static void BFS(){

        while(!q.isEmpty()){
            Pos qv = q.poll();

            for(int i = 0; i < 4; i++){
                int bCount = move(qv.bx, qv.by, i, true);
                int rCount = move(qv.rx, qv.ry, i, false);
                if(mtx[bx][by] == 'O') continue;
                if(qv.move == 10) continue;
                if(mtx[rx][ry] == 'O') {
                    System.out.println(qv.move+1);
                    return;
                }
                if(bx == rx && by == ry){
                    int nd = i == 0 ? 2 : i == 2 ? 0 : i == 1 ? 3 : 1;
                    if(bCount > rCount){
                        bx += dx[nd];
                        by += dy[nd];
                    } else if(bCount < rCount){
                        rx += dx[nd];
                        ry += dy[nd];
                    }
                }

                if(!checkVisited()){
                    setVisited();
                    addQ(qv.move+1);
                }
            }
        }
        System.out.println(-1);
    }


    static int move(int x, int y, int d, boolean isBlue){
        if(isBlue){
            bx = x;
            by = y;
        } else {
            rx = x;
            ry = y;
        }
        int count = 0;
        char v;
        while((v = mtx[x+dx[d]*(count+1)][y+dy[d]*(count+1)]) != '#'){
            count++;
            if(isBlue){
                bx = x+dx[d]*(count);
                by = y+dy[d]*(count);
            } else {
                rx = x+dx[d]*(count);
                ry = y+dy[d]*(count);
            }

            if(v == 'O') break;
        }

        return count;
    }


    private static class Pos{
        int bx;
        int by;
        int rx;
        int ry;
        int move = 0;
        public Pos(int redX, int redY, int blueX, int blueY, int move){
            this.bx = blueX;
            this.by = blueY;
            this.rx = redX;
            this.ry = redY;
            this.move = move;
        }
    }
}