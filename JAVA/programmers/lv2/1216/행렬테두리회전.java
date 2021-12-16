package category.ns.programmersTMP;
import java.util.*;

public class 행렬테두리회전 {

    class Solution {
        public int[] solution(int rows, int columns, int[][] queries) {
            int[][] mtx = new int[rows][columns];
            List<Integer> li = new ArrayList<>();

            for(int i = 0; i < rows; i++){
                for(int j = 0; j < columns; j++){
                    mtx[i][j] = (i)*columns+j+1;
                }
            }

            for(int i = 0; i < queries.length; i++){
                int[] q = queries[i];

                Controller c = new Controller(--q[0], --q[1], --q[2], --q[3]);
                int x = q[0];
                int y = q[1];
                int tmp = mtx[x][y];
                int min = tmp;

                while(true){
                    c.next();
                    int nx = c.curX;
                    int ny = c.curY;
                    int tt = mtx[nx][ny];

                    mtx[nx][ny] = tmp;
                    tmp = tt;
                    min = Math.min(mtx[nx][ny], min);
                    if(nx == x && ny == y) break;
                }
                li.add(min);
            }
            return solve(li);
        }

        int[] solve(List<Integer> li){
            int[] ans = new int[li.size()];
            for(int i = 0; i < li.size(); i++){
                ans[i] = li.get(i);
            }
            return ans;
        }
    }

    class Controller{
        int x1,y1,x2,y2;
        int curX, curY;
        Controller(int x1,int y1, int x2, int y2){
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.curX = x1;
            this.curY = y1;
        }

        void next(){
            if(curX == x1){
                if(curY != y2) curY++;
                else curX++;
            } else if(curX == x2){
                if(curY != y1) curY--;
                else curX--;
            } else if(curY == y1){
                if(curX != x1) curX--;
                else curY++;
            } else if(curY == y2){
                if(curX != x2) curX++;
                else curY--;
            }
        }
    }
}
