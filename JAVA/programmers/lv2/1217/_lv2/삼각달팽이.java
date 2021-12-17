package category.ns.programmersTMP._lv2;
import java.util.*;

public class 삼각달팽이 {

    class Solution {
        int[] dx = {1, 0, -1};
        int[] dy = {0, 1, -1};
        public int[] solution(int n) {
            int[][] mtx = new int[n][n];
            int[] answer = new int[n*(n+1)/2];
            int cnt = 0;
            int x = -1;
            int y = 0;
            int tm = n;
            while(tm > 0){
                for(int i = 0; i < 3; i++){
                    for(int j = 0; j < tm; j++){
                        int nv = ++cnt;
                        x+=dx[i];
                        y+=dy[i];
                        mtx[x][y] = nv;
                    }
                    tm--;
                }
            }
            int idx = 0;

            for(int i = 0; i < n; i++)
                for(int j = 0; j < n; j++)
                    if(mtx[i][j]!=0) answer[idx++] = mtx[i][j];

            return answer;
        }
    }
}
