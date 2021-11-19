package hackerRank.kit.week3;
import java.util.*;

public class BomberMan {
}

class Result {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static List<String> bomberMan(int n, List<String> grid) {
        if(n <= 1) return grid;

        int r,c;
        r = grid.size();
        c = grid.get(0).length();
        List<String> ans;
        if(n%2==0) {
            StringBuilder sb = new StringBuilder();
            ans = new ArrayList<>();
            for(int i = 0; i < c; i++) sb.append("O");

            for(int i = 0; i < grid.size(); i++) ans.add(sb.toString());

            return ans;
        } else {
            ans = BFS(r, c, grid, 'O', '.');
            if(n%4==3) return (BFS(r, c, flip(grid, r, c), '.', 'O'));
            return BFS(r, c, ans, '.', 'O');
        }
    }

    static List<String> BFS(int r, int c, List<String> grid, char target, char src){
        boolean[][] mtx = new boolean[r][c];
        Queue<int[]> q = new LinkedList<>();
        List<String> ans = new ArrayList<>();

        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                if(grid.get(i).charAt(j) == target){
                    mtx[i][j] = true;
                    q.add(new int[]{i, j});
                }
            }
        }

        while(!q.isEmpty()){
            int[] qv = q.poll();
            int x = qv[0];
            int y = qv[1];
            for(int i = 0; i < 4; i++){
                int nx = x+dx[i];
                int ny = y+dy[i];
                if(nx < 0 || ny < 0 || nx >= r || ny >= c) continue;
                mtx[nx][ny] = true;
            }
        }

        for(int i = 0; i < r; i++){
            StringBuilder sb = new StringBuilder();

            for(int j = 0; j < c; j++){
                if(mtx[i][j]) sb.append(target);
                else sb.append(src);
            }
            ans.add(sb.toString());
        }
        return ans;
    }

    static List<String> flip(List<String> grid, int r, int c){
        List<String> ans = new ArrayList<>();
        for(int i = 0; i < r; i++){
            StringBuilder sb = new StringBuilder();

            String line = grid.get(i);
            for(int j = 0; j < c; j++){
                if(line.charAt(j) == 'O') sb.append(".");
                else sb.append("O");
            }

            ans.add(sb.toString());
        }
        return ans;
    }
}