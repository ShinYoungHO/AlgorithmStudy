package category.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

public class IntTriangle {
    static int max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n*(n+1)/2+1];

        st = new StringTokenizer(br.readLine(), " ");
        int dpIdx = 1;

        max = dp[1] = Integer.parseInt(st.nextToken());

        for(int i = 2; i <= n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 1; j <= i; j++){

                if(j == 1 ){
                    dp[++dpIdx] = Integer.parseInt(st.nextToken()) + dp[dpIdx-i+1];
                } else if(j == i) {
                    dp[++dpIdx] = Integer.parseInt(st.nextToken()) + dp[dpIdx-i];
                } else {
                    dp[++dpIdx] = Integer.parseInt(st.nextToken()) + Math.max(dp[dpIdx-i], dp[dpIdx-i+1]);
                }
                if(dp[dpIdx] > max) max = dp[dpIdx];
            }
        }
        System.out.println(Arrays.toString(dp));
        System.out.println(max);
    }
}
