package category.dp3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class LISv5_1 {
    static int n;
    static int[] p, dp, dpi;
    static int[] res;
    static int INF = Integer.MAX_VALUE;

    static int binarySearch(int l, int r, int target){
        int ans = INF;
        while(l <= r){
            int m = (l+r) >> 1;
            if(dp[m] >= target){
                if(ans > m) ans = m;
                r = m - 1;
            } else{
                l = m + 1;
            }
        }
        return ans;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        p = new int[n];
        dp = new int[n];
        dpi = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            p[i] = Integer.parseInt(st.nextToken());
        }

        int j = 0;
        dp[0] = p[0];
        dpi[0] = 0;


        // 로직은 이분탐색과 동일하나 dpi에 인덱스를 저장하는 것의 차이가 있음.
        /*
            i번째 숫자를 고려할 때, i번째가 몇번째 순서의 인덱스를 갱신시켰는지 저장할 필요가 있음.
            -> i번째 숫자가 dp Array의 두번째 값을 갱신 시켰다면, 길이 2까지의 LIS의 맨 마지막 숫자의 최솟값은
               dpi Array를 거꾸로 돌면서 처음 나오는 인덱스의 인덱스에 해당하는 숫자가 LIS의 처음 나오는 인덱스의 값이라 할 수 있음.

            해당 풀이 이전엔 dp 배열을 출력하거나, dp 에 해당 길이의 배열 모두 저장하는 방식, 길이가 길어진 경우의 dp 배열을 저장하는 방식 등 으로 진행했는데,
            dp 배열은 인덱스에 해당하는 길이의 LIS의 맨 뒷 숫자만 저장하기 때문에 올바른 결과가 나오지 않았고, dp에 해당길이의 배열을 모두 저장하는 경우 인풋 값에 따라
            그 크기가 매우 커졌기 때문에 올바르지 않았다.
        */
        for(int i = 1; i < n; i++){
            if(dp[j] < p[i]){
                j++;
                dp[j] = p[i];
                dpi[i] = j;
            } else {
                int idx = binarySearch(0, j, p[i]);
                dp[idx] = p[i];
                dpi[i] = idx;
            }
        }
        sb.append(j+1).append("\n");
        res = new int[j+1];
        int idx = j;
        for(int i = n-1; i >= 0; i--){
            if(idx == dpi[i]){
                res[idx] = p[i];
                idx--;
            }
        }
        for(int i = 0; i < res.length; i++){
            sb.append(res[i]).append(" ");
        }
        System.out.println(sb);
    }
}
