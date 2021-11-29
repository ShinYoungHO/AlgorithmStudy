package category.implementation6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 11780
public class BrandNewGame {
    static int n,k;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int[][] mtx;
    static UnitResolver resolver;
    static int BLUE = 2;
    static int RED = 1;
    static int WHITE = 0;

    static int solve(){
        int time = 1;
        int x,y,nx,ny,dir;
        List<ChessUnit> arr = resolver.units;
        while(time <= 1000){
            UnitArranged[][] map = resolver.map;
            for(int i = 0; i < arr.size(); i++){
                ChessUnit u = arr.get(i);

                x = u.x;
                y = u.y;

                if(map[x][y].getBottom() != u) continue;

                dir = u.dir;
                nx = x+dx[dir];
                ny = y+dy[dir];

                if(OOB(nx, ny) || mtx[nx][ny] == BLUE){
                    u.dir = u.dir == 0 ? 1 : u.dir == 1 ? 0 : u.dir == 2 ? 3 : 2;
                    dir = u.dir;
                    int tx = x+dx[dir];
                    int ty = y+dy[dir];
                    if(OOB(tx, ty) || mtx[tx][ty] == BLUE) continue;
                    nx = tx;
                    ny = ty;
                }

                if(mtx[nx][ny] == RED){
                    map[x][y].isFlipped = !map[x][y].isFlipped;
                }

                for(int j = 0; j < map[x][y].li.size(); j++){
                    ChessUnit uu = map[x][y].getWithIdx(j);
                    uu.x = nx;
                    uu.y = ny;
                    if(map[nx][ny].addUnit(uu) == 4) return time;
                }
                map[x][y].li = new LinkedList<>();
            }
            time++;
        }
        return -1;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        mtx = new int[n][n];
        resolver = new UnitResolver();

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine()," ");
            for(int j = 0; j < n; j++){
                mtx[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int r,c,dst;
        for(int i = 0; i < k; i++){
            st = new StringTokenizer(br.readLine()," ");
            r = Integer.parseInt(st.nextToken())-1;
            c = Integer.parseInt(st.nextToken())-1;
            dst = Integer.parseInt(st.nextToken())-1;
            resolver.addUnit(new ChessUnit(r, c, dst), r, c);
        }

        System.out.println(solve());
    }


    static class UnitResolver{
        List<ChessUnit> units = new ArrayList<>();
        UnitArranged[][] map = new UnitArranged[n][n];

        UnitResolver(){
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    map[i][j] = new UnitArranged();
                }
            }
        }
        void addUnit(ChessUnit u, int x, int y){
            units.add(u);
            map[x][y].addUnit(u);
        }

    }

    static class UnitArranged{
        LinkedList<ChessUnit> li = new LinkedList<>();
        boolean isFlipped = false;

        ChessUnit getBottom(){
            return isFlipped ? li.peekLast() : li.peekFirst();
        }
        int addUnit(ChessUnit u){
            if (isFlipped) {
                li.addFirst(u);
            } else {
                li.addLast(u);
            }
            return li.size();
        }

        ChessUnit getWithIdx(int i){
            int len = li.size();

            if(isFlipped) return li.get(len-1-i);
            return li.get(i);
        }
    }

    static class ChessUnit{
        int x, y, dir;

        public ChessUnit(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    static boolean OOB(int x, int y){
        return x<0||y<0||x>=n||y>=n;
    }
}

// 12500