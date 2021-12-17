package category.ns.programmersTMP._lv3;

public class 등굣길 {
    class Solution {

        public int solution(int m, int n, int[][] puddles) {
            int answer = 0;
            int di = 1000000007;
            int[][] dp = new int[n][m];
            boolean[][] pud = new boolean[n][m];

            for(int i = 0; i < puddles.length; i++){
                int[] p = puddles[i];
                pud[p[1]-1][p[0]-1] = true;
            }

            for(int i = 0; i < n; i++){
                if(pud[i][0]) break;
                dp[i][0] = 1;
            }


            for(int i = 0; i < m; i++){
                if(pud[0][i]) break;
                dp[0][i] = 1;
            }



            for(int i = 1; i < n; i++)
                for(int j = 1; j < m; j++)
                    if(!pud[i][j]) dp[i][j] = (dp[i-1][j]+dp[i][j-1])%di;


            return dp[n-1][m-1]%di;
        }
    }
}
