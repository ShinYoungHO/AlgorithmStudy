package category.ns.programmersTMP._lv3;
import java.util.*;

public class 아이템줍기 {

    class Solution {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        int[] ddx = {-1, -1, -1, 0, 1, 1, 1, 0};
        int[] ddy = {-1, 0, 1, 1, 1, 0, -1, -1};
        int ll = 102;
        boolean[][] tMap;

        public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
            int answer = -1;
            tMap = resolveMap(rectangle);

            characterX*=2;
            characterY*=2;
            itemX*=2;
            itemY*=2;


            Queue<int[]> q = new LinkedList<>();
            q.add(new int[]{characterX, characterY, 0});
            while(!q.isEmpty()){
                int[] qv = q.poll();
                int x = qv[0];
                int y = qv[1];
                int c = qv[2];
                for(int i = 0; i < 4; i++){
                    int nx = x+dx[i];
                    int ny = y+dy[i];

                    if(tMap[nx][ny]){
                        q.add(new int[]{nx, ny, c+1});
                        tMap[nx][ny] = false;
                        if(nx==itemX&&ny==itemY) return (c+1)/2;
                    }
                }
            }

            return answer;
        }


        boolean[][] resolveMap(int[][] rectangle){
            boolean[][] map = new boolean[ll][ll];
            Stack<int[]> st = new Stack<>();
            int x1,y1,x2,y2;
            for(int i = 0; i < rectangle.length; i++){
                int[] rec = rectangle[i];
                x1 = rec[0]*2;
                y1 = rec[1]*2;
                x2 = rec[2]*2;
                y2 = rec[3]*2;

                for(int j = x1; j <= x2; j++){
                    for(int k = y1; k <= y2; k++){
                        map[j][k] = true;
                    }
                }
            }

            for(int i = 0; i < ll; i++){
                for(int j = 0; j < ll; j++){
                    if(map[i][j]){
                        boolean bool = true;
                        for(int k = 0; k < 8; k++){
                            int nx = i+ddx[k];
                            int ny = j+ddy[k];
                            if(!map[nx][ny]) {
                                bool = false;
                                break;
                            }
                        }
                        if(bool) st.push(new int[]{i, j});
                    }
                }
            }

            while(!st.isEmpty()){
                map[st.peek()[0]][st.pop()[1]] = false;
            }

            return map;
        }
    }
}
