package category.implementation4;

import java.io.*;
import java.util.*;

public class BlizzardShark {
    static int[] res;
    static int[] dx = new int[]{-1, 1, 0, 0};
    static int[] dy = new int[]{0, 0, -1, 1};
    static int[] DIR = new int[]{2, 1, 3, 0};
    static int tx, ty, maxCnt, n;
    static int[][] mtx;
    static ArrayList<Integer> marbles;


    static void blizzard(int d, int s){
        int nx, ny;
        for(int i = 1; i <= s; i++){
            nx = tx+dx[d]*i;
            ny = ty+dy[d]*i;
            int v = mtx[nx][ny];
            if(v == 0) continue;
            maxCnt--;
            mtx[nx][ny] = 0;
        }
    }

    static void getMarble(){
        marbles = new ArrayList<>();
        int dir = 0, idx = 0, len = 1;
        int x = tx, y = ty;

        while(idx != maxCnt){
            for(int i = 0; i < 2; i++){
                for(int j = 0; j < len; j++){
                    x += dx[DIR[dir]];
                    y += dy[DIR[dir]];
                    if(mtx[x][y] != 0) {
                        marbles.add(mtx[x][y]);
                        idx++;
                    }
                    if(idx == maxCnt) return;
                }
                dir = (dir + 1)%4;
            }
            len++;
        }
    }

    static void explode(){
        boolean flag = true;
        while (flag) {
            ArrayList<Integer> mList = new ArrayList<>();
            flag = false;
            for (int s = 0; s < marbles.size(); s++) {
                int e = s;
                while (e < marbles.size() && marbles.get(e) == marbles.get(s))
                    e++;

                if (e - s >= 4) {
                    res[marbles.get(s)] += (e - s);
                    flag = true;
                } else {
                    for (int j = s; j < e; j++) {
                        mList.add(marbles.get(j));
                    }
                }
                s = e - 1;
            }
            marbles = mList;
        }
    }

    static void change(){
        ArrayList<Integer> mList = new ArrayList<>();
        for(int s = 0; s < marbles.size(); s++){
            if(mList.size() >= n*n) break;
            int e = s;
            while(e < marbles.size() && marbles.get(s) == marbles.get(e)) e++;

            mList.add(e-s);
            mList.add(marbles.get(s));

            s = e-1;
        }

        for(int i = mList.size(); i >= n*n; i--){
            mList.remove(i-1);
        }

        marbles = mList;
        maxCnt = marbles.size();
    }

    static void reset(){
        int[][] newMtx = new int[n][n];
        int dir = 0, idx = 0, len = 1;
        int x = tx, y = ty;
        while(idx != maxCnt){
            for(int i = 0; i < 2; i++){
                for(int j = 0; j < len; j++){
                    x += dx[DIR[dir]];
                    y += dy[DIR[dir]];
                    newMtx[x][y] = marbles.get(idx++);
                    if(idx == maxCnt) {
                        mtx = newMtx;
                        return;
                    }
                }
                dir = (dir + 1)%4;
            }
            len++;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        res = new int[4];
        tx = ty = (n+1)/2-1;
        mtx = new int[n][n];
        maxCnt = 0;

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < n; j++){
                mtx[i][j] = Integer.parseInt(st.nextToken());
                if(mtx[i][j] != 0) maxCnt++;
            }
        }


        // query
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int d = Integer.parseInt(st.nextToken())-1;
            int s = Integer.parseInt(st.nextToken());

            blizzard(d, s);
            getMarble();
            explode();
            change();
            reset();
        }


        int ans = 0;
        for(int i = 1; i < 4; i++){
            ans+=res[i]*i;
        }
        System.out.println(ans);
    }

    static void pr(){
        for(int i = 0; i < n; i++){
            System.out.println(Arrays.toString(mtx[i]));
        }
        System.out.println("-----------------------");
    }
}
