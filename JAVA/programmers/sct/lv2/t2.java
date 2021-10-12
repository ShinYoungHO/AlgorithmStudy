package test.lv2;
import java.util.*;

public class t2 {
}

class SolutionT2_1 {
    public int solution(int[][] maps) {
        int r = maps.length;
        int c = maps[0].length;
        int x,y,nx,ny;

        int[] dx = new int[]{0, 1, 0, -1};
        int[] dy = new int[]{1, 0, -1, 0};

        int[][] visited = new int[r][c];

        Queue<int[]> q = new LinkedList<>();
        visited[0][0] = 1;
        q.add(new int[]{0, 0});
        while(!q.isEmpty()){
            int[] qv = q.poll();
            x = qv[0];
            y = qv[1];
            for(int i = 0; i < 4; i++){
                nx = x + dx[i];
                ny = y + dy[i];
                if(nx < 0 || ny < 0 || nx >= r || ny >= c) continue;
                if(visited[nx][ny] > 0) continue;
                if(maps[nx][ny] == 0) continue;
                visited[nx][ny] = visited[x][y]+1;
                q.add(new int[]{nx, ny});
                if(nx == r-1 && ny == c-1) return visited[nx][ny];
            }
        }
        return -1;
    }
}

class SolutionT2_2 {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] solve = getSolve(progresses, speeds);
        ArrayList<Integer> result = new ArrayList<>();

        int day = solve[0];
        int cnt = 1;
        for(int i = 1; i < solve.length; i++){
            if(day >= solve[i]) cnt++;
            else {
                result.add(cnt);
                cnt = 1;
                day = solve[i];
            }

            if(i == solve.length-1) result.add(cnt);
        }


        int[] res = new int[result.size()];
        for(int i = 0; i < result.size(); i++){
            res[i] = result.get(i);
        }


        return res;
    }

    int[] getSolve(int[] progresses, int[] speeds){
        int[] solve = new int[progresses.length];
        for(int i = 0; i < progresses.length; i++){
            int pr = 100 - progresses[i];
            int sp = speeds[i];

            solve[i] = (int) Math.ceil((double)pr/(double)sp);
        }
        return solve;
    }
}