package category.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RecurToDP {
    static int[][][] dp;
    static boolean[][][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int a,b,c;
        dp = new int[21][21][21];
        visited = new boolean[21][21][21];

        while(true){
            st = new StringTokenizer(br.readLine(), " ");
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            if(a == b && b == c && c == -1) break;

            sb.append("w(").append(a).append(", ").append(b).append(", ").append(c).append(") = ").append(solve(a, b, c)).append("\n");
        }
        System.out.println(sb);
        br.close();
    }
    static int solve(int a, int b, int c){
        if(a <= 0 || b <= 0 || c <= 0) return 1;
        if(a > 20 || b > 20 || c > 20) return solve(20, 20, 20);
        if(visited[a][b][c]) return dp[a][b][c];
        visited[a][b][c] = true;
        return dp[a][b][c] = a < b && b < c ? solve(a, b, c-1) + solve(a, b-1, c-1) - solve(a, b-1, c)
                : solve(a-1, b, c) + solve(a-1, b-1, c) + solve(a-1, b, c-1) - solve(a-1, b-1, c-1);
    }
}
