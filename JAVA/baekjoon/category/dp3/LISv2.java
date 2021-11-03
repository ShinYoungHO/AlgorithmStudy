package category.dp3;

import java.io.*;
import java.util.*;

public class LISv2 {
    static int n;
    static int[] dp, p;
    static int INF = Integer.MAX_VALUE;
    static int binarySearch(int left, int right, int target){
        int mid;
        int ans = INF;
        while (left <= right) {
            mid = (left + right) / 2;
            if (dp[mid] < target) {
                left = mid + 1;
            } else {
                if(mid < ans) ans = mid;
                right = mid - 1;
            }
        }
        return ans;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        p = new int[n];
        dp = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        for(int i = 0; i < n; i++) p[i] = Integer.parseInt(st.nextToken());

        dp[0] = p[0];
        int j = 0; // 0번째 까지 업데이트 됨.
        for(int i = 1; i < n; i++){
            if (dp[j] < p[i]) {
                dp[j + 1] = p[i];
                j += 1;
            } else {
                int idx = binarySearch(0, j, p[i]);
                dp[idx] = p[i];
            }
        }
        System.out.println(j+1);
    }
}
