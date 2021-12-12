package category.ns.solved;

import java.io.*;
import java.util.*;

public class 피자판매_2632 {
    static int k,m,n;

    static void solve(int[] ma, int[] na){
        int ans = 0;
        if(ma[m]-ma[0]==k) ans++;
        if(na[n]-na[0]==k) ans++;
        Map<Integer, Integer> map = new HashMap<>();

        map.put(ma[m]-ma[0], 1);

        for(int i = 0; i < m; i++){
            for(int j = i+1; j < i+m; j++){
                int v;
                if(j <= m)v = ma[j] - ma[i];
                else v = ma[m] - ma[i] + ma[j%m] - ma[0];

//                System.out.print(v+" ");
                if(v > k) continue;
                if(v == k) ans++;
                if(!map.containsKey(v)) map.put(v, 0);
                map.put(v, map.get(v) + 1);
            }
        }

//        System.out.println("");
        for(int i = 0; i < n; i++){
            for(int j = i+1; j < i+n; j++){
                int v;
                if(j <= n) v = na[j] - na[i];
                else v = na[n] - na[i] + na[j%n] - na[0];
                if(v == k) ans++;
                ans+=check(v, map);
            }
        }
        ans+=check(na[n]-na[0], map);

        System.out.println("");
        System.out.println(ans);
    }

    static int check(int v, Map<Integer, Integer> map){
        System.out.print(v+" ");
        if(v > k) return 0;
        int t = k - v;
        if(t < 0) return 0;
        if(map.containsKey(t)) return map.get(t);
        return 0;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        k = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        int[] ma = new int[m+1];
        int[] na = new int[n+1];

        for(int i = 1; i <= m; i++) ma[i] = ma[i-1] + Integer.parseInt(br.readLine());
        for(int i = 1; i <= n; i++) na[i] = na[i-1] + Integer.parseInt(br.readLine());

        solve(ma, na);
    }
}
