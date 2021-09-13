package sw;
// 2000ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Fisher {
    static int R;
    static int C;
    static int[][][] mtx1;
    static int[][][] mtx2;
    static boolean headToOne = false;
    static int ans;
    static int[] dr = new int[]{0, -1, 1, 0, 0};
    static int[] dc = new int[]{0, 0, 0, 1, -1};
    static int[] pad = new int[3];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), ", ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        mtx1 = new int[R][C][3];
        mtx2 = new int[R][C][3];

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine(), ", ");
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            mtx1[r-1][c-1] = new int[]{s, d, z};
        }

        ans = 0;

        solve();
        System.out.println(ans);
    }

    static void solve(){
        int time = -1;
        int[][][] from;
        int[][][] to;

        while(time+1 < C){
            if(headToOne){
                from = mtx2;
                to = mtx1;
            } else {
                from = mtx1;
                to = mtx2;
            }
            time ++;
            for(int i = 0; i < R; i++){
                if(from[i][time][2] != 0){
                    ans += from[i][time][2];
                    from[i][time] = pad;
                    break;
                }
            }
            moveShark(from, to);
            headToOne = !headToOne;
        }
    }
    static void moveShark(int[][][] from, int[][][] to){

        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                if(from[i][j][2] != 0){

                    int s = from[i][j][0]; // 속력
                    int d = from[i][j][1]; // 이동방향
                    int z = from[i][j][2]; // 상어 크기


                    int[] n = getNextPos(i, j, from[i][j]);


                    if(to[n[0]][n[1]][2] != 0){
                        if(to[n[0]][n[1]][2] < z){
                            to[n[0]][n[1]] = from[i][j];
                        }
                    } else {
                        to[n[0]][n[1]] = from[i][j];
                    }
                    from[i][j] = pad;
                }
            }
        }
    }

    static int[] getNextPos(int r, int c, int[] shark){
        int[] res = new int[2];
        int s = shark[0];
        int d = shark[1];
        int i = 1;
        while(s > 0){
            int nr = r + dr[d]*i;
            int nc = c + dc[d]*i;
            if(nr < 0 || nc < 0 || nr >= R || nc >= C) {
                r += dr[d]*(i-1);
                c += dc[d]*(i-1);
                i = 1;
                d = d == 1 ? 2 : d == 2 ? 1 : d == 3 ? 4 : 3;
                continue;
            }
            s--;
            i++;
        }
        res[0] = r+dr[d]*(i-1);
        res[1] = c+dc[d]*(i-1);
        shark[1] = d;
        return res;
    }
}
