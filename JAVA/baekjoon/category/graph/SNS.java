package category.graph;
// 2533
import java.io.*;
import java.util.*;

public class SNS {
    static ArrayList<Integer>[] nodes;
    static boolean[] visited;
    static int[][] dp;

    static void solve(int x){
        visited[x] = true;
        dp[x][0] = 1;
        for(int i = 0; i < nodes[x].size(); i++){
            int child = nodes[x].get(i);
            if(visited[child]) continue;
            solve(child);
            //  얼리 아답터가 아닌 사람들은 자신의 모든 친구들이 얼리 아답터일 때만 이 아이디어를 받아들임.
            dp[x][1] += dp[child][0]; // x가 일반인인 경우 x의 서브트리가 얼리어답터인 경우의 수들의 총 합.
            dp[x][0] += Math.min(dp[child][1], dp[child][0]); // x가 얼리어답터인 경우
            // (x의 자식노드가 얼리어답터인 경우의 서브트리 얼리어답터 수  //  얼리어답터가 아닌 경우의 서브트리 얼리어답터 수) 두 수중 작은 값.
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int f,t;
        nodes = new ArrayList[n+1];
        visited = new boolean[n+1];
        dp = new int[n+1][2];

        for(int i = 1; i < n; i++){
            st = new StringTokenizer(br.readLine()," ");
            f = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());
            if(nodes[f] == null) nodes[f] = new ArrayList<>();
            if(nodes[t] == null) nodes[t] = new ArrayList<>();
            nodes[f].add(t);
            nodes[t].add(f);
        }
        solve(1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }
}
