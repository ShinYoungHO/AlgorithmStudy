package category.ns.solved;

import java.io.*;
import java.util.*;

public class 모래성_10711 {
    static int r,c;
    static int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
    static int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};
    static Queue<int[]> q;

    static int memorySave(int[][] mtx){
        int ans = 0;
        int x,y,nx,ny;
        int[] qv;
        while(true){
            int l = q.size();
            boolean k = false;
            for(int i = 0; i < l; i++){
                qv = q.poll();
                x = qv[0];
                y = qv[1];
                for(int j = 0; j < 8; j++){
                    nx=x+dx[j];
                    ny=y+dy[j];
                    if(nx<0||ny<0||nx>=r||ny>=c) continue;
                    mtx[nx][ny]--;
                    if(mtx[nx][ny] == 0){ // 0이 된 순간에만 q에 할당, 중복 시 음수로 변하며 중복 체크 필요 x
                        q.add(new int[]{nx, ny});
                        k = true;
                    }
                }
            }
            if(!k) break;
            ans++;
        }
        return ans;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        q = new LinkedList<>();
        int[][] mtx = new int[r][c];

        for(int i = 0; i < r; i++){
            String s = br.readLine();
            for(int j = 0; j < c; j++){
                char c = s.charAt(j);
                if(c == '.') {
                    q.add(new int[]{i, j});
                    continue;
                }
                mtx[i][j] = c-'0';
            }
        }
        System.out.println(memorySave(mtx));
    }
}
