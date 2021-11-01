package category.dp3;
// 2565
import java.io.*;
import java.util.*;

public class Cable {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());
        int[][] link = new int[t+1][2];
        int[] dp = new int[t+1];

        int from,to;
        for(int i = 0; i < t; i++){
            st = new StringTokenizer(br.readLine(), " ");
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            link[i][0] = from;
            link[i][1] = to;
        }

        Arrays.sort(link, (l1, l2) -> l1[0] - l2[0]);

        int ans = 1;

        // 전깃줄을 하나하나 놓으며 이미 놓은 전깃줄들과 비교

        for(int i = 1; i <= t; i++){
            // dp[i]에는 i번째 까지의 전깃줄을 선택했을 때 놓을 수 있는 최대 전깃줄 갯수

            // i 번쨰 전깃줄을 놓는다고 할 때, j번째 까지의 전깃줄을 놓는 경우 중에서 i번째 전깃줄을 놓을 수 있을 때,
            // 해당 값에 +1 한 경우(전깃줄을 놓는 것)가 가장 큰 경우가 i번째 전깃줄 까지 선택했을 때 놓을 수 있는 경우 중 최댓값
            dp[i] = 1;
            for(int j = 1; j < i; j++){
                if(link[i][1] > link[j][1]){
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            ans = Math.max(dp[i], ans);
        }
        System.out.println(t-ans);
    }

}

// next 11053, 11055 LIS