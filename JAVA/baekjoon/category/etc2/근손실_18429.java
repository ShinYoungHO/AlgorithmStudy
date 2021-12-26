package category.ns.baekjoonSol;

import java.io.*;
import java.util.*;

public class 근손실_18429 {
    static int n,k,ans,ll,kits[];

    static void solve(int delta, int vis){
        if(vis == ll-1){
            ans++;
            return;
        }
        for(int i = 1; i <= n; i++){
            if(((ll>>i)&vis)==(ll>>i)) continue;
            if(delta-k+kits[i-1]<0) continue;
            solve(delta-k+kits[i-1], vis|(ll>>i));
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        ans = 0;
        ll = 1<<n;
        kits = new int[n];
        st = new StringTokenizer(br.readLine()," ");

        for(int i = 0; i < n; i++)
            kits[i] = Integer.parseInt(st.nextToken());
        solve(0, 0);
        System.out.println(ans);
    }
}
