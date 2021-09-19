package category.dp2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class KnapSack {
    public static void main(String[] args) throws IOException {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] weights = new int[n+1];
        int[] values = new int[n+1];
        int[][] dp = new int[n+1][k+1];

        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            weights[i] = Integer.parseInt(st.nextToken());
            values[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i <= n; i++){ // 보석 iterate
            for(int j = 1; j <= k; j++){ // 가방무게 iterate
                if(j >= weights[i]){
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-weights[i]]+values[i]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }

        }
        System.out.println(dp[n][k]);
    }
}

// 해당 가방 무게에 담을 수 있는 최대 가치를 갱신해가며 메모
// 보석 하나에 대해 1부터 가방 무게까지 for문을 돌며, 해당 무게의 가방에 담을 수 있는 가치의 최댓값은
//                                        현재까지 구해온 해당 무게에서의 가치와
//                                        현재 보석을 추가했을 때와 비교.
//                                        현재 보석을 추가했을 때의 가치는 현재 보석무게 만큼 뺀 가방 무게의 가치 최댓값에서 현재 보석의 가치를 더한 값.
