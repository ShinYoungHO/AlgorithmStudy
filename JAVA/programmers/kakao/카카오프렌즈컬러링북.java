package programmers.kakao;

import java.util.Queue;
import java.util.LinkedList;

public class 카카오프렌즈컬러링북 {
    public static void main(String[] args) {
//        new ColoringBook().solution();
    }
}

class ColoringBook {
    boolean[][] visited;
    int[][] pic;
    Queue<int[]> q;
    int area = 1;
    int[] dr = new int[]{0, 1, 0, -1};
    int[] dc = new int[]{1, 0, -1, 0};

    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        visited = new boolean[m][n];
        pic = picture;
        q = new LinkedList<>();

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(!visited[i][j] && pic[i][j] != 0){

                    q.add(new int[]{i, j});
                    visited[i][j] = true;
                    while(q.size() > 0) BFS(m, n);


                    if(area > maxSizeOfOneArea) maxSizeOfOneArea = area;
                    area = 1;
                    numberOfArea++;
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }


    public void BFS(int m, int n){
        int[] v = q.poll();
        int r = v[0];
        int c = v[1];
        for(int i = 0; i < 4; i++){
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
            if(visited[nr][nc]) continue;

            if(pic[r][c] == pic[nr][nc]){
                q.add(new int[]{nr, nc});
                visited[nr][nc] = true;
                area++;
            }
        }
    }
}
