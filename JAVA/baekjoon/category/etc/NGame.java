package category.etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NGame {
    static int N,K;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[][] mtx = new int[N][N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine()," ");
            for(int j = 0; j < N; j++){
                mtx[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine()," ");

        }
    }
}
