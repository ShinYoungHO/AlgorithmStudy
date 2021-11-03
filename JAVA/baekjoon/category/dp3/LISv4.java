package category.dp3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class LISv4 {
    static int n;
    static int[] p;
    static ArrayList<Integer>[] dp;
    static int INF = Integer.MAX_VALUE;
    static ArrayList<Integer> pad = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        p = new int[n];
        dp = new ArrayList[n];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        for (int i = 0; i < n; i++) {
            p[i] = Integer.parseInt(st.nextToken());
            dp[i] = new ArrayList<>();
        }

        int ans = 1;
        ArrayList<Integer> ansArr = new ArrayList<>();

        for(int i = 0; i < n; i++){
            dp[i] = new ArrayList<>();
            dp[i].add(p[i]);
            for(int j = 0; j < i; j++){
                if(p[i] > p[j]){
                    if(dp[i].size() < dp[j].size()+1){
                        dp[i] = (ArrayList<Integer>) dp[j].clone();
                        dp[i].add(p[i]);
                    }
                }
            }
            if(ans <= dp[i].size()){
                ans = dp[i].size();
                ansArr = dp[i];
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(ans).append("\n");
        for(int i = 0; i < ansArr.size(); i++){
            sb.append(ansArr.get(i)).append(" ");
        }
        System.out.println(sb);
    }
}
