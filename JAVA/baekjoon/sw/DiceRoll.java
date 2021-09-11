package sw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DiceRoll {
    static int rLen;
    static int cLen;
    static int[] dr = new int[]{0, 0, 0, -1, 1};
    static int[] dc = new int[]{0, 1, -1, 0, 0};
    static char[][] mtx;
    static char[] dice = new char[]{'0','0','0','0','0','0','0'};
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        rLen = Integer.parseInt(st.nextToken());
        cLen = Integer.parseInt(st.nextToken());

        int rs = Integer.parseInt(st.nextToken());
        int cs = Integer.parseInt(st.nextToken());

        int n = Integer.parseInt(st.nextToken());

        mtx = new char[rLen][cLen];

        for(int i = 0; i < rLen; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < cLen; j++){
                mtx[i][j] = st.nextToken().charAt(0);
            }
        }


        StringBuilder res = solve(sb, new StringTokenizer(br.readLine(), " "), rs, cs, n);
        System.out.println(res);
    }

    static StringBuilder solve(StringBuilder sb, StringTokenizer st, int r, int c,  int n){

        for(int i = 0; i < n; i++){
            int d = Integer.parseInt(st.nextToken());
            int nr = r+dr[d];
            int nc = c+dc[d];
            if(nr < 0 || nr >= rLen || nc < 0 || nc >= cLen) continue;
            roll(d);
            if(mtx[nr][nc] == '0'){
                // 주사위 복사
                mtx[nr][nc] = dice[6];
            }
            else {
                // 주사위면 = mtx[nr][nc];
                // mtx[nr][nc] = 0;
                dice[6] = mtx[nr][nc];
                mtx[nr][nc] = '0';
            }
            r = nr;
            c = nc;
            sb.append(dice[1]);
            sb.append("\n");
        }
        return sb;
    }

    static void roll(int d){
        char[] tmp = dice.clone();
        switch(d){
            case 1:
                dice[1] = tmp[4];
                dice[3] = tmp[1];
                dice[6] = tmp[3];
                dice[4] = tmp[6];
                break;
            case 2:
                dice[1] = tmp[3];
                dice[3] = tmp[6];
                dice[6] = tmp[4];
                dice[4] = tmp[1];
                break;
            case 3:
                dice[1] = tmp[5];
                dice[5] = tmp[6];
                dice[6] = tmp[2];
                dice[2] = tmp[1];
                break;
            case 4:
                dice[1] = tmp[2];
                dice[5] = tmp[1];
                dice[6] = tmp[5];
                dice[2] = tmp[6];
                break;
        }
    }
}

/**
 *   2
 * 4 1 3
 *   5
 *   6
 *
 *   1
 * 2   5
 *   6
 */
