package category.dp2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PelindromeQuery {
    public static void main(String[] args) throws IOException {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n+1];
        boolean[][] dp = new boolean[n+1][n+1];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 1; i <= n; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        for(int i = 2; i < n; i++){
            if(arr[i-1] == arr[i+1]) memo(i, true, dp, arr, 1);
        }

        for(int i = 2; i <= n; i++){
            if(arr[i] == arr[i-1]) memo(i, false, dp, arr, 1);
        }


        int k = Integer.parseInt(br.readLine());
        for(int i = 0; i < k; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            if(s == e) sb.append("1").append("\n");
            else {
                if(dp[s][e]) sb.append("1").append("\n");
                else sb.append("0").append("\n");
            }
        }
        System.out.println(sb);
        br.close();
    }

    static void memo(int i, boolean isABA, boolean[][] dp, int[] arr, int di ){
        if(isABA){
            if(i+di >= arr.length || i-di < 0 ) return;
            if(arr[i-di] == arr[i+di]) {
                dp[i-di][i+di] = true;
                memo(i, isABA, dp, arr, di+1);
            }
        } else {
            if(i-di < 0 || i+di-1 >= arr.length) return;
            if(arr[i-di] == arr[i+di-1]) {
                dp[i-di][i+di-1] = true;
                memo(i, isABA, dp, arr, di+1);
            }
        }
    }
}
