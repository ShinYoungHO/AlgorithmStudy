package category.dp3;

import java.io.*;
import java.util.*;

public class DiamondMine {
    static int r,c;
    static int[][] dy1,dy2;

    static void solve(int[][] mtx){
        for(int i = 1; i <= r; i++){
            for(int j = 1; j <= c; j++){
                if(mtx[i][j] == 0){
                    dy1[i][j] = dy2[i][j] = 0;
                } else {
                    dy1[i][j] = dy1[i-1][j-1]+1;
                    dy2[i][j] = dy2[i-1][j+1]+1;
                }
            }
        }

        int max = 0;

        for(int i = 1; i <= r; i++){
            for(int j = 1; j <= c; j++){
                if (dy1[i][j] <= max || dy2[i][j] <= max) continue;
                int l=max;
                while(true){
                    // 크기가 벗어나는 경우
                    if (i-2*l <= 0 || j-l <= 0 || j+l > c) break;

                    // 각 꼭짓점의 길이가 맞게 세팅되어있는 경우
                    if (dy1[i][j] > l
                            && dy2[i][j] > l
                            && dy2[i-l][j-l] > l
                            && dy1[i-l][j+l] > l
                    ){
                        max = Math.max(max, l+1);
                    } else break;
                    l++;
                }
            }
        }

        System.out.println(max);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        int[][] mtx = new int[r+2][c+2];
        dy1 = new int[r+2][c+2];
        dy2 = new int[r+2][c+2];

        for(int i = 0; i < r; i++){
            String s = br.readLine();
            for(int j = 0; j < c; j++){
                mtx[i+1][j+1] = s.charAt(j)-'0';
            }
        }
        solve(mtx);
    }
}
