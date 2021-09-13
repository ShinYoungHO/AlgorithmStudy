package sw;
// 664ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Fisher2 {
    static int R;
    static int C;
    static int[][][] mtx1;
    static int[][][] mtx2;
    static boolean headToOne = false;
    static int ans;
    static int[] dr = new int[]{0, -1, 1, 0, 0};
    static int[] dc = new int[]{0, 0, 0, 1, -1};
    static int[] pad = new int[3];

    static int nnr = 0;
    static int nnc = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), ", ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        mtx1 = new int[R][C][3];
        mtx2 = new int[R][C][3];

        Queue<int[]> q = new LinkedList<>();
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine(), ", ");
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            mtx1[r-1][c-1] = new int[]{s, d, z};
            q.add(new int[]{r - 1, c - 1});
        }

        ans = 0;

        solve(q);
        System.out.println(ans);
    }

    static void solve(Queue<int[]> q){
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
            moveShark(from, to, q);
            headToOne = !headToOne;
        }
    }
    static void moveShark(int[][][] from, int[][][] to, Queue<int[]> q){
        for(int k = 0; k < q.size(); k++){
            int[] coord = q.poll();
            int i = coord[0];
            int j = coord[1];
            int z = from[i][j][2]; // 상어 크기

            getNextPos(i, j, from[i][j]);


            if(to[nnr][nnc][2] != 0){
                if(to[nnr][nnc][2] < z){
                    to[nnr][nnc] = from[i][j];
                }
                coord[0] = nnr;
                coord[1] = nnc;
                q.add(coord);
            } else {
                to[nnr][nnc] = from[i][j];
                coord[0] = nnr;
                coord[1] = nnc;
                q.add(coord);
            }
            from[i][j] = pad;
        }
    }

    static void getNextPos(int r, int c, int[] shark){
        int d = shark[1];
        int s = shark[0] % ((d == 1 || d == 2 ? R : C) * 2 - 2);
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
        nnr = r+dr[d]*(i-1);
        nnc = c+dc[d]*(i-1);
        shark[1] = d;
    }
}

/**
 * 최적화
 * 1. mtx 상어 옮길때마다 새로 생성하는 대신 두개를 선언하고 교차로 사용. from, to 는 headToOne 불리언 값으로 관리.
 * 2. mtx-from 에서 mtx-to 로 옮길 때 from 을 비워줘야 되는데, 이차원배열에 int[]형이 들어가야됨. 비워줄때마다 새로 생성하지 않고 빈자리를 채우는 용도의 배열 미리 선언
 * 3. 다음 좌표를 구할때 한칸씩 이동하는 대신 한번 왕복할 때 드는 이동거리로 전체 이동해야할 거리로 나눠준 나머지로 빠르게 계산. - 속력의 범위가 mtx크기보다 크므로 의미가 있음.
 */