package category.dp2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Scale {
    static int N;
    static int[] sinkers;
    static boolean[][] dp;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        sinkers = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<N; i++)
            sinkers[i] = Integer.parseInt(st.nextToken());

        int M = Integer.parseInt(br.readLine());
        int[] beads = new int[M];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<M; i++)
            beads[i] = Integer.parseInt(st.nextToken());

        dp = new boolean[N+1][55001];         //추의 최대 무게합(30*500(g)) + 구슬의 최대 무게(40000)

        dfs(0, 0);

        for(int i=0; i<M; i++) {
            if(dp[N][beads[i]]) {
                sb.append("Y ");
            } else {
                sb.append("N ");
            }
        }
        System.out.println(sb);
    }

    public static void dfs(int cnt, int weight) {

        if(dp[cnt][weight]) return;

        dp[cnt][weight] = true;

        if(cnt==N) return;

        dfs(cnt+1, weight+sinkers[cnt]);
        dfs(cnt+1, weight);
        dfs(cnt+1, Math.abs(weight-sinkers[cnt]));

    }
}
