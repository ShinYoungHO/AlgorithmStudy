package category.implementation4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MonoDomino {
    static int[][] g,b;
    static int[] dt = {0, 2, 3};
    static int[] dx = {3, 2, 1, 0};
    static int R = 6;
    static int C = 4;
    static int[][][] dPos = {
            { {0, 0} },
            { {0, 0}, {1, 0} },
            { {0, 0}, {0, 1} },
            { {0, 0}, {-1, 0} },
    };
    static int score;

    static void solve(int t, int x, int y){
        push(g, t, y);
        push(b, dt[t], dx[x]);
        g = delete(g);
        b = delete(b);

    }

    static int[][] delete(int[][] col){
        int[][] result = new int[R][C];
        int rIdx = 5;
        for(int i = 5; i >= 0; i--){
            int[] tmp = col[i];
            int cnt = 0;
            for(int j = 0; j < 4; j++){
                if(tmp[j] != 0) cnt++;
            }
            if(cnt >= 1 && cnt <= 3) result[rIdx--] = tmp;
            else if(cnt == 4) score++;
            else break;
        }
        if(rIdx == -1) move(result, 2);
        else if(rIdx == 0) move(result, 1);
        return result;
    }

    static void move(int[][] c, int k){
        for(int j = 5; j > k-1; j--){
            c[j] = c[j-k];
        }
        for(int i = 0; i < k; i++){
            c[i] = new int[4];
        }
    }

    static void push(int[][] g, int t, int vIdx){
        int idx = -1;
        int x,y;
        int mx = R;
        int my = C;
        while(true){
            boolean isValid = true;
            for(int i = 0; i < dPos[t].length; i++){
                int[] td = dPos[t][i];
                x = idx + 1 + td[1];
                y = vIdx + td[0];
                if(x < 0 || y < 0 || x >= mx || y >= my) {
                    isValid = false;
                    break;
                }

                if(g[x][y] != 0 ) {
                    isValid = false;
                    break;
                }

            }
            if(!isValid) break;
            idx++;
        }
        for(int i = 0; i < dPos[t].length; i++){
            int[] td = dPos[t][i];
            g[idx+td[1]][vIdx+td[0]] = 1;
        }
    }

    static int score(int[][] c){
        int res = 0;
        for(int i = 0; i < c.length; i++) {
            for (int j = 0; j < c[0].length; j++) {
                if (c[i][j] != 0) res++;
            }
        }
        return res;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        score = 0;
        int t,x,y;
        g = new int[6][4];
        b = new int[6][4];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine()," ");
            t = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            solve(t-1, x, y);
        }
        int s = score(g)+score(b);
        System.out.println(score+"\n"+s);
    }
}
