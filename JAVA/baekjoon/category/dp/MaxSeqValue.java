package category.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MaxSeqValue {
    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");

        int[][] dp = new int [n+1][2];
        for(int i = 1; i <= n; i++){
            dp[i][0] = Integer.parseInt(st.nextToken());
            dp[i][1] = dp[i][0] + Math.max(dp[i-1][1], dp[i-1][0]);
            if(max < dp[i][0]) max = dp[i][0];
            if(max < dp[i][1]) max = dp[i][1];
        }

//        /* less memory */
//        int e = 0;
//        int s = 0;
//
//        for(int i = 1; i <= n; i++){
//            int v1 = Integer.parseInt(st.nextToken());
//            int v2 = v1 + Math.max(e, s);
//            if(max < v1) max = v1;
//            if(max < v2) max = v2;
//            e = v1;
//            s = v2;
//        }


        System.out.println(max);
    }

}
// i 번째 인덱스의
//  -> 0번째 인덱스에는 해당 지점에서 시작했을 때의 값을 저장
//  -> 1번째 인덱스에는 연속해서 저장하는 경우 :::  ( 이전에 카운팅을 시작한 경우의 값 or 그 전부터 시작한 경우의 값 )중에 최대인 경우에 해당 지점의 값을 저장.