package category.dp3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LISmaxSum {
    static int n;
    static int[] p;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        p = new int[n+1];
        int[] dp = new int[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        for(int i = 1; i <= n; i++){
            p[i] = Integer.parseInt(st.nextToken());
        }

        int ans = 1;
        for(int i = 1; i <= n; i++){
            dp[i] = p[i];
            for(int j = 1; j < i; j++){
                if(p[i] > p[j]){
                    dp[i] = Math.max(dp[i], dp[j] + p[i]);
                }
            }
            if(ans < dp[i]) ans = dp[i];
        }

        System.out.println(ans);
    }
}
