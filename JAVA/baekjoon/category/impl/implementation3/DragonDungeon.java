package category.implementation3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 16434
public class DragonDungeon {
    static int atk;
    static int[][] tc;

    static long solve(){
        long l = 0;
        long r = Long.MAX_VALUE;
        long ans = r;

        while(l <= r){
            long mid = (l+r)/2;
            long h = ret(mid);
            System.out.println(h+":"+mid);
            if(h > 0) {
                if(ans > h) ans = mid;
                r = mid-1;
            } else {
                l = mid+1;
            }
        }
        return ans;
    }

    static long ret(long hp){
        int t,a,h;
        int at = atk;
        long maxH = hp;
        for(int i = 0; i < tc.length; i++){
            t = tc[i][0];
            a = tc[i][1];
            h = tc[i][2];

            if(t == 1){
                hp-= (long) (Math.ceil((double) h / (double) at) * (double)a);
            } else {
                hp+=h;
                at+=a;
                if(hp > maxH) hp = maxH;
            }
            if(hp <= 0) return hp;
        }
        return hp;
        //999999000001
        //1000000000001
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int n = Integer.parseInt(st.nextToken());
        atk = Integer.parseInt(st.nextToken());
        tc = new int[n][3];
        int t,a,h;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            t = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            tc[i] = new int[]{t,a,h};
        }
        System.out.println(solve());
    }
}
