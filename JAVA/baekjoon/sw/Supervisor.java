package sw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Supervisor {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        String[] bc = br.readLine().split(" ");
        int b = Integer.parseInt(bc[0]);
        int c = Integer.parseInt(bc[1]);
        long ans = 0;
        for(int i = 0; i < n; i++){
            int k = Integer.parseInt(st.nextToken());
            long res = solve(k, b, c);
            ans += res;

        }


        System.out.println(ans);
    }

    static long solve(int k, int b, int c){
        long target = k - b;
        if(target <= 0) return 1;
        long left = 0;
        long right = target;
        long ans = Integer.MAX_VALUE;

        while(left <= right){
            long mid = (left+right) >> 1;
            if(mid * c >= target){
                right = mid-1;
                if(ans >= mid) ans = mid;
            } else {
                left = mid+1;
            }
        }
        return ans+1;
    }
}
