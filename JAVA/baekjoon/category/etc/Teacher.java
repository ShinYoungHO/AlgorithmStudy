package category.etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Teacher {
    static int n,k;
    static int[] words;
    static int res;
    static void solve(int start, int cnt, int vis, int max){
        if(cnt == 0){
            int r = 0;
            for(int i = 0; i < n; i++){
                // 가능한 문자열에 s가 모두 포함되느냐
                int s = words[i];
                if(((vis^s)&s) == 0) r++;
            }
            if(res < r) res = r;
            return;
        }
        for(int i = start; i < max; i++){
            if(((1 << i) & vis) == 1) continue;
            solve(i+1, cnt-1, vis|(1<<i), max);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");


        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        words = new int[n];
        res = 0;

        if(k < 5) {
            System.out.println(0);
            return;
        }

        for(int i = 0; i < n; i++){
            String s = br.readLine();
            words[i] = getVis(s);
        }

        int vis = getVis("antic");

        solve(0, k-5, vis, 26);
        System.out.println(res);
    }

    static int getVis(String s){
        int res = 0;
        for(int i = 0; i < s.length(); i++){
            res|=(1<<(s.charAt(i)-'a'));
        }
        return res;
    }
}

// acnit
