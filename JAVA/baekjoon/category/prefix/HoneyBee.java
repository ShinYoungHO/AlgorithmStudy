package category.prefix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class HoneyBee {
    static int[] inp, minus, plus;
    static int n;
    static int max;

    static void solve(){
        // 왼
//        System.out.println("left");
        for(int i = 1; i < n-1; i++){
            int ll = plus[n]-inp[0] - inp[i] + plus[n]-plus[i+1];
//            System.out.println((plus[n]-inp[0] - inp[i])+"+"+(plus[n]-plus[i+1])+"="+ll);
            if(ll > max) max = ll;
        }
        // 오
//        System.out.println("right");
        for(int i = n-2; i >= 1; i--){
            int rr = minus[0]-inp[n-1] - inp[i] + minus[0]-minus[i];
//            System.out.println(minus[0]-inp[n-1] - inp[i]+"+"+(minus[0]-minus[i]) + "="+rr);
            if(rr > max) max = rr;
        }
        // 중간
//        System.out.println("mid");
        for(int i = 1; i < n-1; i++){
            int lr = plus[i+1]-inp[0]+minus[i]-minus[n-1];
//            System.out.println((plus[i+1]-inp[0])+"+"+(minus[i]-minus[n-1]) +"="+lr);
            if(max < lr) max = lr;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        inp = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < n; i++){
            inp[i] = Integer.parseInt(st.nextToken());
        }
        plus = new int[n+1];
        minus = new int[n+1];
        max = 0;
        for(int i = 1; i <= n; i++){
            plus[i] = plus[i-1]+inp[i-1];
        }

        for(int i = n-1; i >= 0; i--){
            minus[i] = minus[i+1]+inp[i];
        }

//        System.out.println(Arrays.toString(plus));
//        System.out.println(Arrays.toString(minus));
        solve();
        System.out.println(max);
    }
}

/**
 * 8
 * 1 9 100 20 25 7 9 1
 */