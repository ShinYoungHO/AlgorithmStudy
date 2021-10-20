package category.implementation5;
// 10875 - fail

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Snake2 {
    static int LEFT = 3;
    static int RIGHT = 1;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int dir;
    static int x,y;
    static long res;
    static int max;
    static int min;
    static ArrayList<Line> rLines;
    static ArrayList<Line> cLines;

    static boolean solve(int time, int dDir, ArrayList<Line> lines, int fix){
        int fx,fy,tx,ty;
        fx = x+dx[dir];
        fy = y+dy[dir];
        tx = x+dx[dir]*time;
        ty = y+dy[dir]*time;
        Line nl = new Line(fx,fy,tx,ty, fix);
        for(int i = 0; i < lines.size(); i++){
            Line l = lines.get(i);
            if(fix == 0){
                // |||
                if( l.x1 <= tx && tx <= l.x2 && l.y1 <= nl.y1 && nl.y1 <= l.y2){
                    res += nl.y1 - fy;
//                    res+=getLen(nl.y1, fy);
                    return false;
                }
                if(nl.y2 > max) {
//                    res += max - fy;
                    res+= getLen(max, fy);
                    return false;
                }
                if(nl.y1 < min){
//                    res += ty - min;
                    res+=getLen(ty, min);
                    return false;
                }
            } else {
                // ---
                if(l.y1 <= ty && ty <= l.y2 && nl.x1 <= l.x1 && l.x1 <= nl.x2){
                    res += l.x1 - fx;
//                    getLen(l.x1, fx);
                    return false;
                }
                if(nl.x1 < min){
//                    res += max - fx;
                    res+=getLen(min, fx);
                    return false;
                }
                if(nl.x2 > max){
//                    res += tx - min;
                    res+=getLen(tx, max);
                    return false;
                }
            }
        }
        if(fix == 0) rLines.add(nl);
        else cLines.add(nl);

        res += time;
        dir = (dir+dDir) % 4;
        x = tx;
        y = ty;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        rLines = new ArrayList<>();
        cLines = new ArrayList<>();

        max = Integer.parseInt(br.readLine());
        min = -1*max;
        int n = Integer.parseInt(br.readLine());


        res = 1;
        x = 0;
        y = -1;
        dir = 0;

        StringTokenizer st;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int t = Integer.parseInt(st.nextToken());
            String d = st.nextToken();

            boolean result;
            if(dir == 0 || dir == 2) result = solve(t, d.equals("L") ? LEFT : RIGHT, cLines, 0);
            else result = solve(t, d.equals("L") ? LEFT : RIGHT, rLines, 1);
            if(!result) break;
        }
        System.out.println(res);
    }

    static int getLen(int r, int c){
        r = Math.abs(r);
        c = Math.abs(c);
        return r > c ? r-c+1 : c-r+1;
    }

    static class Line{
        int x1,y1,x2,y2;

        public Line(int x1, int y1, int x2, int y2, int fix) {
            if((fix == 0 && y1 > y2) || (fix == 1 && x1 > x2)){
                this.x1 = x2;
                this.x2 = x1;
                this.y1 = y2;
                this.y2 = y1;
                return;
            }
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }
}






class Snake {
    static int LEFT = -1;
    static int RIGHT = 1;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int dir;
    static int x,y;
    static int max;
    static boolean solve(int time, int dDir, Set<Node> set){
        for(int i = 0; i < time; i++){
            x+=dx[dir];
            y+=dy[dir];
            if(x < 0 || y < 0 || x >= max || y >= max) return false;
            Node n = new Node(x, y);
            if(set.contains(n)) return false;
            set.add(n);
        }
        dir +=dDir;
        if(dir == -1) dir = 3;
        else if(dir == 4) dir = 0;
        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int l = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        max = 2*l+1;
        x = y = l;
        dir = 0;

        StringTokenizer st;
        Set<Node> set = new HashSet<>();
        set.add(new Node(l, l));
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int t = Integer.parseInt(st.nextToken());
            String d = st.nextToken();

            if(!solve(t, d.equals("L") ? LEFT : RIGHT, set)) break;
        }
        System.out.println(set.size());
    }
    static class Node {
        int x, y;
        Node(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return x+y;
        }

        @Override
        public boolean equals(Object n){
            if(n instanceof Node) {
                Node k = (Node) n;
                if (k.x == this.x && k.y == this.y) return true;
            }
            return false;
        }
    }
}