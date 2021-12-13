package category.ns.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 새로운게임_17837 {
    static int n,k;
    static Obj[] objs;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    static void solve(Coord[][] mtx){
        int ans = 1;
        while(ans < 1000){
            for(int i = 0; i < k; i++){
                Obj o = objs[i];
                Coord c = mtx[o.x][o.y];

                int nx = o.x+dx[o.dir];
                int ny = o.y+dy[o.dir];
                if(nx<0||ny<0||nx>=n||ny>=n||mtx[nx][ny].color==2){
                    int d = o.dir;
                    o.dir = d == 0 ? 1 : d == 1 ? 0 : d == 2 ? 3 : 2;

                    nx = o.x+dx[o.dir];
                    ny = o.y+dy[o.dir];
                    if(nx<0||ny<0||nx>=n||ny>=n||mtx[nx][ny].color == 2) continue;
                }
                boolean r = c.updateCoordinate(mtx[nx][ny], o);
                if(!r) {
                    System.out.println(ans);
                    return;
                }
            }
            ans++;
        }
        System.out.println(-1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        Coord[][] mtx = new Coord[n][k];
        objs = new Obj[k];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < n; j++){
                mtx[i][j] = new Coord(Integer.parseInt(st.nextToken()), i, j);
            }
        }

        for(int i = 0; i < k; i++){
            st = new StringTokenizer(br.readLine()," ");
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
            int d = Integer.parseInt(st.nextToken())-1;
            objs[i] = new Obj(d,x,y);
            mtx[x][y].addToList(objs[i]);
        }

        solve(mtx);
    }

}

class Obj{
    int dir;
    int x,y;

    public Obj(int dir, int x, int y) {
        this.dir = dir;
        this.x = x;
        this.y = y;
    }
    void update(int x, int y){
        this.x = x;
        this.y = y;
    }
}

class Coord {
    boolean flip = false;
    LinkedList<Obj> lists = new LinkedList<>();
    int color,x,y;

    public Coord(int color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }

    void addToList(Obj o){
        if(flip) lists.addFirst(o);
        else lists.addLast(o);
    }

    boolean updateCoordinate(Coord nCoord, Obj o){
        int nCol = nCoord.color;
        int nx = nCoord.x;
        int ny = nCoord.y;
        LinkedList<Obj> li = new LinkedList<>();
        while(lists.size() != 0){
            Obj tmp;
            if(flip){
                tmp = lists.pollFirst();
            } else {
                tmp = lists.pollLast();
            }
            li.add(tmp);
            tmp.update(nx, ny);
            if(tmp == o) break;
        }

        while(!li.isEmpty()){
            if(nCol == 1){
                nCoord.addToList(li.pollFirst());
            } else {
                nCoord.addToList(li.pollLast());
            }
            if(nCoord.lists.size() >= 4) return false;
        }

        return true;
    }

}
