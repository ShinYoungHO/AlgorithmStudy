package category.dp3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class PalindromeFactory {
    static int[][] dp;
    static int solve(int l, int r, char[] c){
        // -1인 경우 메모한 값 리턴
        if(dp[l][r] != -1) return dp[l][r];

        // 같은 경우는 펠린드롬이므로 0 리턴
        if(l >= r) return 0;

        int x,y,z;
        if(c[l] == c[r]){
            // 앞 뒤가 같은 경우 왼쪽 오른쪽 중앙으로 한칸씩
            return dp[l][r] = solve(l+1, r-1, c);
        } else {
            // 앞 뒤가 다른 경우

            // 가장 왼쪽 값을 펠린드롬에 만족하도록 연산 (오른쪽에 왼쪽과 같은 값을 추가하거나 왼쪽을 뻄)
            x = solve(l+1, r, c) + 1;

            // 가장 오른쪽 값을 펠린드롬에 만족하도록 연산 (위와 동일)
            y = solve(l, r-1, c) + 1;

            // 왼쪽 오른쪽을 일치시키고 중앙으로 한칸 이동
            z = solve(l+1, r-1, c) + 1;

            return dp[l][r] = min(x, y, z);
        }
    }

    static int min(int x, int y, int z){
        return Math.min(x, Math.min(y, z));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int mIdx = s.length()-1;
        dp = new int[mIdx+1][mIdx+1];

        // dp배열 -1로 채움. 이미 펠린드롬인 경우 최솟값이 0이기 때문에, 초기 값이 -1이 아닌 경우 해당 dp 값이 계산을 한 경우인지 안한 경우인지 구별 할 수가 없다.
        fill(mIdx);

        // 교환연산 안한 상태의 값
        int ans = solve(0, mIdx,s.toCharArray());

        // 모든 i, j 교환 후 연산
        for(int i = 0; i <= mIdx; i++){
            for(int j = i+1; j <= mIdx; j++){
                char[] c = s.toCharArray();
                char tmp = c[i];
                c[i] = c[j];
                c[j] = tmp;

                fill(mIdx);
                ans = Math.min(ans, solve(0, mIdx, c)+1);
            }
        }
        System.out.println(ans);
    }

    static void fill(int mIdx){
        for(int i = 0; i <= mIdx; i++){
            Arrays.fill(dp[i], -1);
        }
    }
}
