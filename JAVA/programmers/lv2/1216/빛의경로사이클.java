package category.ns.programmersTMP;
import java.util.*;

public class 빛의경로사이클 {
    class Solution {
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        int n,m,l;
        public int[] solution(String[] grid) {
            n = grid.length;
            m = grid[0].length();
            l = Math.max(n, m);

            int[][] vis = new int[n][m];
            List<Integer> ans = new ArrayList<>();

            for(int i = 0; i < n; i++){
                for(int j = 0; j < m; j++){
                    for(int k = 0; k < 4; k++){
                        if(isVisited(i, j, k, vis)) continue;
                        shot(i, j, k, grid, vis, ans);
                    }
                }
            }

            Collections.sort(ans);
            return ans.stream().mapToInt(i->i).toArray();
        }

        void shot(int x, int y, int d, String[] grid, int[][] vis, List<Integer> ans){
            Map<Integer, int[]> map = new HashMap<>();
            int[] tmp = new int[4];
            tmp[d] = 1;
            map.put(x*l+y, tmp);
            int cnt = 1;

            int nx, ny, nd;
            char c;
            while(true){
                nx = (x+dx[d]+n)%n;
                ny = (y+dy[d]+m)%m;
                c = grid[nx].charAt(ny);
                nd = c == 'L' ? (d+3)%4 : c == 'R' ? (d+1)%4 : d;
                int key = nx*l+ny;
                if(!map.containsKey(key)) map.put(key, new int[4]);
                if(map.get(key)[nd] == 0){
                    if(isVisited(nx, ny, nd, vis)) return;
                    map.get(key)[nd] = ++cnt;
                } else {
                    int v = cnt-map.get(key)[nd]+1;
                    ans.add(v);
                    return;
                }
                x = nx;
                y = ny;
                d = nd;
            }

        }


        boolean isVisited(int x, int y, int d, int[][] vis){
            if((vis[x][y]&(1<<d))==(1<<d)) return true;
            vis[x][y]|=(1<<d);
            return false;
        }
    }
}
