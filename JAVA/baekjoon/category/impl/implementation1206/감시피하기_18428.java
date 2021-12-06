package category.ns.solved;

import java.io.*;
import java.util.*;

public class 감시피하기_18428 {
    static int n;
    static char[][] mtx;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static char STUDENT = 'S';
    static char WALL = 'O';
    static char TEACHER = 'T';
    static char EMPTY = 'X';
    static Set<Integer> set;
    static List<int[]> em, te;

    static boolean valid(){
        for(int i = 0; i < te.size(); i++){
            int x = te.get(i)[0];
            int y = te.get(i)[1];
            for(int j = 0; j < 4; j++){
                int nx = x;
                int ny = y;
                while(true){
                    nx+=dx[j];
                    ny+=dy[j];
                    if(nx<0||ny<0||nx>=n||ny>=n||mtx[nx][ny]==WALL||mtx[nx][ny]==TEACHER) break;
                    if(mtx[nx][ny] == STUDENT) return false;
                }
            }
        }
        return true;
    }

    static boolean solve(int idx, int cnt, int max){
        if(cnt>3) return false;
        if(idx == max){
            if(valid()) return true;
            return false;
        }

        int x = em.get(idx)[0];
        int y = em.get(idx)[1];
        if(set.contains(x*7+y)){
            mtx[x][y] = WALL;
            if(solve(idx+1, cnt+1, max)) return true;
            mtx[x][y] = EMPTY;
        }
        if(solve(idx+1, cnt, max)) return true;
        return false;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        mtx = new char[n][n];
        em  = new ArrayList<>();
        te = new ArrayList<>();
        set = new HashSet<>();

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < n; j++){
                mtx[i][j] = st.nextToken().charAt(0);
                if(mtx[i][j] == EMPTY) em.add(new int[]{i, j});
                else if(mtx[i][j] == TEACHER) te.add(new int[]{i, j});
            }
        }

        for(int i = 0; i < te.size(); i++){
            int[] teacher = te.get(i);
            int x1 = teacher[0];
            int y1 = teacher[1];
            for(int j = 0; j < 4; j++){
                for(int k = 1; k < 6; k++){
                    int x = x1+dx[j]*k;
                    int y = y1+dy[j]*k;
                    if(x<0||y<0||x>=n||y>=n||mtx[x][y]==WALL||mtx[x][y]==TEACHER) break;
                    if(mtx[x][y] == EMPTY) continue;
                    for(int m = 1; m < k; m++){
                        int nx = x1+dx[j]*m;
                        int ny = y1+dy[j]*m;
                        System.out.println(nx+":"+ny);
                        set.add(nx*7+ny);
                    }
                    break;
                }
            }
        }

        System.out.println(solve(0, 0, em.size()) ? "YES" : "NO");;
    }

    static void print(){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                System.out.print(mtx[i][j]+" ");
            }
            System.out.println("");
        }
        System.out.println("============");
    }
}
