package category.implementation;

// 15683
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class CCTV {
    static int r,c,len=0;
    static int[][] mtx;
    static int[] dx = new int[]{0, 1, 0, -1}; // 3 6 9 12
    static int[] dy = new int[]{1, 0, -1, 0};
    static int[][][] dir;
    static ArrayList<Coord> cctv;
    static int result = Integer.MAX_VALUE;
    static Stack<int[]> stack;

    static void solve(int idx){
        if(idx == len){
            int cnt = 0;
            for(int i = 0; i < r; i++){
                for(int j = 0; j < c; j++){
                    if(mtx[i][j] == 0) cnt++;
                }
            }
            if(result > cnt) result = cnt;
            return;
        }
        Coord crd = cctv.get(idx);
        int x,y;
        int nx, ny;
        x = crd.x;
        y = crd.y;
        int type = mtx[x][y];
        int[][] dirs = dir[type];

        for(int i = 0; i < dirs.length; i++){
            int t = 0;
            for(int j = 0; j < dirs[i].length; j++){
                nx = x;
                ny = y;
                int d = dirs[i][j];
                while(true){
                    nx += dx[d];
                    ny += dy[d];
                    if(nx >= r || ny >= c || nx < 0 || ny < 0) break;
                    if(mtx[nx][ny] == 6) break;
                    if(mtx[nx][ny] > 0) continue;
                    mtx[nx][ny]--;
                    stack.push(new int[]{nx, ny});
                    t++;
                }
            }
            solve(idx+1);
            for(int j = 0; j < t; j++){
                int[] tmp = stack.pop();
                mtx[tmp[0]][tmp[1]]++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        mtx = new int[r][c];
        cctv = new ArrayList<>();
        stack = new Stack<>();
        dir = new int[][][]{
                new int[][]{},
                new int[][]{new int[]{0},new int[]{1},new int[]{2},new int[]{3}},
                new int[][]{new int[]{0, 2},new int[]{1, 3}},
                new int[][]{new int[]{0, 1},new int[]{1, 2},new int[]{2, 3},new int[]{3, 0}},
                new int[][]{new int[]{0, 1, 2},new int[]{0, 1, 3},new int[]{0, 2, 3},new int[]{1, 2, 3}},
                new int[][]{new int[]{0, 1, 2, 3}}
        };
        for(int i = 0; i < r; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < c; j++){
                int v = Integer.parseInt(st.nextToken());
                if(v != 0 && v != 6) {
                    cctv.add(new Coord(i, j));
                    len++;
                }
                mtx[i][j] = v;
            }
        }
        solve(0);
        System.out.println(result);
    }

    private static class Coord{
        int x,y;
        private Coord(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
