package category.implementation5;
// 2879
import java.io.*;
import java.util.*;

public class Indent {
    static int MAX = Integer.MAX_VALUE;
    static int res;
    static void solve(int[] target, int[] match, int start, int end, int n){
        int s,e, min;
        for(s = start; s < end; s++){
            e = s;
            int t,m;
            int tf = match(target[e],match[e]);
            if(tf == 0) continue;
            while(e+1 < n && tf == match(target[e+1], match[e+1])) e++;
            min = MAX;
            for(int i = s; i <= e; i++){
                t = target[i];
                m = match[i];
                int v = t >= m ? t - m : m - t;
                if(v < min) min = v;
            }
            res+=min;
            if(tf < 0) min *=-1;
            boolean k = true;
            for(int i = s; i <= e; i++) {
                target[i] += min;
                if(target[i] != match[i]) k = false;
            }
            if(!k) solve(target, match, s, e+1, n);
            s = e;
        }
    }

    static int match(int f, int t){
        if(t-f > 0) return 1;
        else if(t-f < 0) return -1;
        return 0;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt;
        StringTokenizer stm;
        res = 0;
        int n = Integer.parseInt(br.readLine());

        int[] target = new int[n];
        int[] match = new int[n];

        stt = new StringTokenizer(br.readLine(), " ");
        stm = new StringTokenizer(br.readLine(), " ");

        for (int i = 0; i < n; i++) {
            target[i] = Integer.parseInt(stt.nextToken());
            match[i] = Integer.parseInt(stm.nextToken());
        }

        solve(target, match,0, n, n);
        System.out.println(res);
    }
}
