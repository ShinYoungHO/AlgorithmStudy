package category.ns.implementation1202;

import java.io.*;
import java.util.*;

public class WhatsUpGravity_5827 {
    static int r,c;
    static int INF = Integer.MAX_VALUE;
    static int[] start = null;
    static int[] end = null;
    static int[] dy = {1, -1};

    static int solve(char[][] mtx){
        Queue<int[]> q = new PriorityQueue<>((n1, n2) -> n1[2] - n2[2]);
        int[][] vis = new int[r][c];
        for(int i = 0; i < r; i++){
            Arrays.fill(vis[i], INF);
        }
        int x,y,nx,ny,cnt,flip;

        x = upDown(start[0],start[1],0, mtx);
        y = start[1];
        if(x == -1) return -1;
        if(mtx[x][y] == 'D') return 0;

        q.add(new int[]{x, y, 0, 0});
        vis[x][y] = 0;

        while(!q.isEmpty()){
            x = q.peek()[0];
            y = q.peek()[1];
            cnt = q.peek()[2];
            flip = q.poll()[3];

            // 좌우
            for(int i = 0; i < 2; i++){
                nx = x;
                ny = y+dy[i];
                if(nx < 0 || ny < 0 || nx >= r || ny >= c || mtx[nx][ny] == '#') continue;
                if(mtx[nx][ny] == 'D' && vis[nx][ny] > cnt) vis[nx][ny] = cnt;
                addQ(nx, ny, cnt, flip, q, mtx, vis);
            }

            // 뒤집기
            addQ(x, y, cnt+1, flip == 0 ? 1 : 0, q, mtx, vis);
        }
//        print(vis);
        return vis[end[0]][end[1]] == INF ? -1 : vis[end[0]][end[1]];
    }

    static void addQ(int nx, int ny, int cnt, int flip, Queue<int[]> q, char[][] mtx, int[][] vis){
        nx = upDown(nx, ny, flip, mtx);

        if(nx == -1) return;
        if(vis[nx][ny] <= cnt) return;
        vis[nx][ny] = cnt;
        q.add(new int[]{nx, ny, cnt, flip});
//        print(vis);
    }

    static int upDown(int x, int y, int flip, char[][] mtx){
        int add;
        int nx = x;

        if(flip == 0) add = 1;
        else add = -1;

        while(nx+add < r && nx+add >= 0){
            if(mtx[nx+add][y] == 'D') return nx+add;
            if(mtx[nx+add][y] == '#') return nx;
            nx+=add;
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());


        char[][] mtx = new char[r][c];

        for(int i = 0; i < r; i++){
            String s = br.readLine();
            for(int j = 0; j < c; j++){
                mtx[i][j] = s.charAt(j);
                if(mtx[i][j] == 'C'){
                    start = new int[]{i, j};
                } else if(mtx[i][j] == 'D'){
                    end = new int[]{i, j};
                }
            }
        }
        if(start==null||end==null) {
            System.out.println(-1);
            return;
        }
        System.out.println(solve(mtx));
    }

    static void print(int[][] mtx){
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                System.out.print(mtx[i][j]==INF?"m ":mtx[i][j]+" ");
            }
            System.out.println("");
        }
        System.out.println("=================");
    }
}



/*
6 10
##########
#..#....##
#.......D#
#C.......#
###.#....#
##########


6 10
##########
#..#....##
#........#
#C......D#
###.#....#
##########


6 10
##########
#.##....##
#........#
#C...D...#
###.#....#
##########

6 10
##########
#D.#....##
##.......#
#........#
#C#.#....#
##########


6 10
##########
#..#....##
#........#
#C...D...#
###.#....#
###.######


6 10
##########
#..#....##
#........#
#C.......#
###.#..D.#
##########


10 10
######.###
#.##....##
#...###..#
#........#
#..D#....#
########.#
#....#...#
#C.......#
###.#....#
###.######



10 10
######.###
#.##....##
#...###..#
#........#
#...#....#
########.#
#....#...#
#CD......#
##..#....#
###.######

10 10
##########
#.#......#
#...###..#
#.......D#
#....#...#
#######.##
#....#...#
#C.......#
##..#....#
###.######



6 10
#########D
#.##..#...
#.......##
#C.#.....#
###.#....#
##########

6 4
##.D
#...
#C..
##..
##..
####

6 4
####
#..#
#D.#
#..#
#C.#
##.#


4 4
####
#..#
#CD#
##.#

4 4
####
#..#
#C.#
D..#

4 4
#D##
#..#
#C.#
.#.#

4 4
####
#..#
#C.#
.##D

4 4
###.
#..#
#C.#
.D#.



10 10
######.###
#.##....##
#...###..#
#........#
#........#
####D###.#
#..#.#...#
#C.......#
##..#....#
##########

10 10
######.###
#.##....##
#...###..#
#...D....#
#........#
####.###.#
#..#.#...#
#C.......#
##..#....#
##########




6 5
##.##
#CD.#
#....
#....
##...
##.##
 */