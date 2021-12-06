package category.ns.solved;

import java.io.*;
import java.util.*;

public class 전개도_1917 {
    static String YES = "yes";
    static String NO = "no";
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int[][] d = {{7,2,2,2}, {6,3,2,2}, {6,2,3,2}, {6,2,2,3}, {2,7,2,2}, {2,6,3,2}, {4,6,3,1}, {6,2,3,1}, {1,7,2,2}, {2,3,6,4}};
    static int[][] dm = {{0,1},{0,1},{1,1},{1,0},{1,0}};
    static int MAX = Integer.MAX_VALUE;
    static int MIN = Integer.MIN_VALUE;
    static int x1,x2,y1,y2;
    static List<int[][]> targets = generate();

    static String solve(int[][] mtx){
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++){
                if(mtx[i][j] == 0) continue;
                x1 = MAX;
                y1 = MAX;
                x2 = MIN;
                y2 = MIN;
                BFS(i, j, mtx);
                for(int k = 0; k < targets.size(); k++){
                    int[][] target = targets.get(k);
                    int r = x2-x1+1;
                    int c = y2-y1+1;
                    if(target.length != r || target[0].length != c) continue;
                    boolean valid = true;
                    for(int m = 0; m < target.length; m++){
                        for(int n = 0; n < target[0].length; n++){
                            if(target[m][n] != mtx[x1+m][y1+n]) {
                                valid = false;
                                break;
                            }
                        }
                        if(!valid) break;
                    }
                    if(valid) return YES;
                }
                break;
            }
        }
        return NO;
    }
    static void BFS(int x1, int y1, int[][] mtx){
        updateMinMax(x1, y1);
        Queue<int[]> q = new LinkedList<>();
        boolean[][] vis = new boolean[6][6];
        q.add(new int[]{x1, y1});
        vis[x1][y1] = true;

        int x,y,nx,ny;
        while(!q.isEmpty()){
            int[] tm = q.poll();
            x = tm[0];
            y = tm[1];
            for(int j = 0; j < 4; j++){
                nx = x+dx[j];
                ny = y+dy[j];
                if(nx<0||ny<0||nx>=6||ny>=6||vis[nx][ny]||mtx[nx][ny]==0) continue;
                vis[nx][ny] = true;
                updateMinMax(nx,ny);
                q.add(new int[]{nx, ny});
            }
        }
    }

    static List<int[][]> generate(){
        List<int[][]> ans  = new ArrayList<>();
        for(int i = 0; i < d.length; i++){
            int[] t = d[i];
            int[][] map = new int[3][4];
            for(int j = 0; j < 4; j++){
                int v = t[j];
                int k = 0;
                while(k < 3){
                    int c = 1 << k;
                    if((c&v)==c) map[k][j] = 1;
                    k++;
                }
            }
            addTo(ans, map);
            addTo(ans, flip(map));
        }
        addTo(ans, dm);
        addTo(ans, flip(dm));
        return ans;
    }
    static void addTo(List<int[][]> ans, int[][] map){
        for(int i = 0; i < 4; i++){
            ans.add(map);
            map=rotate(map);
        }
    }
    static int[][] flip(int[][] mtx){
        int r = mtx.length;
        int c = mtx[0].length;
        int[][] ans = new int[r][c];
        for(int i = 0; i < r; i++){
            for(int j = c-1; j >= 0 ; j--){
                ans[i][c-j-1] = mtx[i][j];
            }
        }
        return ans;
    }

    static int[][] rotate(int[][] mtx){
        int r = mtx.length;
        int c  = mtx[0].length;
        int[][] ans = new int[c][r];

        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                ans[j][r-1-i] = mtx[i][j];
            }
        }
        return ans;
    }
    static void updateMinMax(int x, int y){
        if(x<x1) x1 = x;
        if(x>x2) x2 = x;
        if(y<y1) y1 = y;
        if(y>y2) y2 = y;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int[][] mtx;

        for(int k = 0; k < 3; k++){
            mtx = new int[6][6];
            for(int j = 0; j < 6; j++){
                st = new StringTokenizer(br.readLine(), " ");
                for(int i = 0; i < 6; i++){
                    int v = Integer.parseInt(st.nextToken());
                    mtx[j][i] = v;
                }
            }
            sb.append(solve(mtx)).append("\n");
        }
        System.out.println(sb);
    }
}






class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        for (int t = 0; t < 3; t++){
            int[][] board = new int[8][8];
            int[][] ps = new int[6][2];
            int pi = 0;
            int a;
            for (int r = 1; r <= 6; r++){
                for (int c = 1; c <= 6; c++){
                    a = board[r][c] = sc.nextInt();
                    if (a > 0){
                        ps[pi][0] = r;
                        ps[pi][1] = c;
                        pi++;
                    }

                }
            }
            // ps 블록좌표

            boolean yes = true;

            // 각 블록 좌표들에 대해서
            for (var e:ps){
                int count = 0;
                int r0 = e[0], c0 = e[1];
                for (var d:new int[][]{{-1,0},{1,0},{0,-1},{0,1}}){
                    // 사방 확인
                    int rc = r0 + d[0];
                    int cc = c0 + d[1];
                    if (board[rc][cc] == 0)
                        continue;
                    // 사방향중 블록이 있는 경우에 대해

                    for (;board[rc][cc] > 0;){
                        rc += d[1];
                        cc += d[0];
                    }
                    for (;board[rc -= d[1]][cc -= d[0]] > 0;){
                        count += board[rc+d[0]][cc + d[1]];
                    }
                }
                yes &= count == 1;
            }
            System.out.println(yes?"yes":"no");

        }

    }
}