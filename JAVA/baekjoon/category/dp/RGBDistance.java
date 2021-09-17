package category.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RGBDistance {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        int[][] dp = new int[n][3];
        st = new StringTokenizer(br.readLine(), " ");
        dp[0][0] = Integer.parseInt(st.nextToken());
        dp[0][1] = Integer.parseInt(st.nextToken());
        dp[0][2] = Integer.parseInt(st.nextToken());

        for(int i = 1; i < n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < 3; j++){
                int min = j == 0 ? Math.min(dp[i-1][1], dp[i-1][2]) :
                        j == 1 ? Math.min(dp[i-1][0], dp[i-1][2]) :
                                Math.min(dp[i-1][0], dp[i-1][1]);
                dp[i][j] = min + Integer.parseInt(st.nextToken());
            }
        }
        int v = Math.min(dp[n-1][0], Math.min(dp[n-1][1], dp[n-1][2]));


        System.out.println(v);
    }

}
// 0,1,2 idx j => RGB
// N번째에서 해당 칸을 j index 로 칠했을 때의 최소비용 = N-1에서 j를 제외한 다른 인덱스에서 칠한 경우의 최소값+해당 인덱스의 값