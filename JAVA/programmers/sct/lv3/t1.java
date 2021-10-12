package test.lv3;
import java.util.*;

public class t1{
}


class SolutionT1_1 {
    Map<String, ArrayList<String>> paths;
    String[] answer;
    Map<String, boolean[]> visited;
    public String[] solution(String[][] tickets) {
        paths = new HashMap<>();
        visited = new HashMap<>();
        answer = new String[tickets.length+1];

        for(int i = 0; i < tickets.length; i++){
            String[] ticket = tickets[i];
            if(!paths.containsKey(ticket[0])) paths.put(ticket[0], new ArrayList<>());
            paths.get(ticket[0]).add(ticket[1]);
        }

        for(String key : paths.keySet()){
            ArrayList<String> arr = paths.get(key);
            Collections.sort(arr);
            visited.put(key, new boolean[arr.size()]);
        }

        answer[0] = "ICN";
        bt("ICN", 1);

        return answer;
    }

    boolean bt(String start, int idx){
        ArrayList<String> arr = paths.get(start);
        boolean[] vis = visited.get(start);
        if(idx == answer.length) return true;
        if(arr == null) return false;

        for(int i = 0 ; i < arr.size(); i++){
            if(!vis[i]){

                vis[i] = true;
                String to = arr.get(i);
                answer[idx] = to;
                if(bt(to, idx+1)){
                    return true;
                }
                vis[i] = false;
            }
        }
        return false;
    }
}

// fail
class SolutionT1_2 {
    int r,c;
    int[] dx,dy;
    ArrayList<int[][]> blocks = new ArrayList<>();
    public int solution(int[][] game_board, int[][] table) {
        dx = new int[]{-1, 0, 1, 0};
        dy = new int[]{0, 1, 0, -1};
        r = table.length;
        c = table[0].length;

        boolean[][] visited = new boolean[r][c];


        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                if(table[i][j] != 0 && !visited[i][j]){
                    BFS(visited, table, i, j);
                }
            }
        }


        ret(0, 0);

        int answer = -1;
        return answer;
    }

    void ret(int idx, int cnt){

    }

    int[][] spin (int[][] block){
        int xl = block.length;
        int yl = block[0].length;
        int[][] res = new int[yl][xl];
        for(int i = 0; i < xl; i++){
            for(int j = 0; j < yl; j++){
                res[j][xl-i-1] = block[i][j];
            }
        }

        return res;
    }

    void BFS(boolean[][] visited, int[][] table, int x1, int y1){
        Queue<int[]> q = new LinkedList<>();
        ArrayList<int[]> crds = new ArrayList<>();
        crds.add(new int[]{x1, y1});
        q.add(new int[]{x1, y1});

        visited[x1][y1] = true;

        int x,y,nx,ny;
        while(!q.isEmpty()){
            int[] qv = q.poll();
            x = qv[0];
            y = qv[1];
            for(int i = 0; i < 4; i++){
                nx = x + dx[i];
                ny = y + dy[i];
                if(nx < 0 || ny < 0 | nx >= r || ny >= c) continue;
                if(visited[nx][ny]) continue;
                if(table[nx][ny] == 0) continue;

                int[] res = new int[]{nx,ny};
                q.add(res);
                crds.add(res);
                visited[nx][ny] = true;
            }
        }

        // addBlock();
        int mix = Integer.MAX_VALUE;
        int miy = Integer.MAX_VALUE;
        int max = 0, may = 0;
        for(int i = 0; i < crds.size(); i++){
            int[] coord = crds.get(i);
            x = coord[0];
            y = coord[1];
            if(x < mix) mix = x;
            if(y < miy) miy = y;
            if(x > max) max = x;
            if(y > may) may = y;
        }

        int[][] block = new int[max-mix+1][may-miy+1];
        for(int i = 0; i < crds.size(); i++){
            int[] coord = crds.get(i);
            x = coord[0]-mix;
            y = coord[1]-miy;
            block[x][y] = 1;
        }
        blocks.add(block);
    }
}