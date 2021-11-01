package category.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Present {
    static String impossible = "IMPOSSIBLE";
    static int INF = Integer.MAX_VALUE;
    static String solve(int m, int k, int total, int[][] ms){
        Arrays.sort(ms, (m1, m2) -> m1[0] != m2[0] ? m1[0] - m2[0] : m2[1] - m1[1]);
        if(total < m) return impossible;
        StringBuilder sb = new StringBuilder();
        if(ms[0][0]*k >= m){
            int v = m-m/k*k;
            int d = m/k;
            for(int i = k-1; i >= 0; i--){
                if(v > 0) {
                    ms[i][0] = d + 1;
                    v--;
                } else ms[i][0] = d;
            }
            Arrays.sort(ms, (m1, m2) -> m1[1] - m2[1]);
            for(int i = 0; i < k; i++){
                sb.append(ms[i][0]).append(" ");
            }
            return sb.toString();
        }
        int pv = 0;
        for(int i = 0; i < k; i++){
            int v = m - pv - ms[i][0]*(k-i);
            if(v <= 0){
                int l = ms[i-1][0];
                int r = ms[i][0];
                int c = k-i;
                int target = m-pv;
                int res = INF;

                while(l <= r){
                    int mid = (l+r)>>1;
                    if(mid*c >= target){
                        if(mid < res) res = mid;
                        r = mid-1;
                    } else {
                        l = mid+1;
                    }
                }
                int t = res*c-target;
                for(int j = 0; j < c; j++){
                    if(t > 0){
                        ms[k-c+j][0] = res-1;
                        t--;
                    } else {
                        ms[k-c+j][0] = res;
                    }
                }

                break;
            }
            pv += ms[i][0];
        }

        Arrays.sort(ms, (m1, m2) -> m1[1] - m2[1]);
        for(int i = 0; i < k; i++){
            sb.append(ms[i][0]).append(" ");
        }

        return sb.toString();
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());

        for(int i = 0; i < t; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int m = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine(), " ");
            int[][] inp = new int[k][2];
            int total = 0;
            for(int j = 0; j < k; j++){
                inp[j][0] = Integer.parseInt(st.nextToken());
                total += inp[j][0];
                inp[j][1] = j;
            }
            sb.append(solve(m, k, total, inp)).append("\n");
        }
        System.out.println(sb);
    }
}
