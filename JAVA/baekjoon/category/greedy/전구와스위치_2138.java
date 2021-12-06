package category.ns.solved;

import java.io.*;

public class 전구와스위치_2138 {

    static int solve(int s, int n, boolean[] c, boolean[] t){
        int a = s;
        for(int i = 1; i < n; i++){
            if(c[i-1] == t[i-1]) continue;
            a+=push(c, i, n);
        }
        if(c[n-1]!=t[n-1]) return -1;
        return a;
    }

    static int push(boolean[] t, int idx, int n){
        for(int i = idx-1; i <= idx+1; i++){
            if(i<0||i>=n) continue;
            t[i] = !t[i];
        }
        return 1;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        boolean[] cur = inp(br.readLine(), n);
        boolean[] target = inp(br.readLine(), n);

        int n1 = solve(0, n, cur.clone(), target);
        push(cur, 0, n);
        int n2 = solve(1, n, cur.clone(), target);
        System.out.println(n1 == -1 && n2 == -1 ? -1 :
                n1 != -1 && n2 != -1 ? Math.min(n1, n2) : Math.max(n1, n2));
    }

    static boolean[] inp(String s, int n){
        boolean[] ans = new boolean[n];
        for(int i = 0; i < n; i++){
            if(s.charAt(i)=='1') ans[i] = true;
        }
        return ans;
    }
}
