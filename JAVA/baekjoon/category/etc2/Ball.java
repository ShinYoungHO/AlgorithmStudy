package category.etc2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ball {
    static int r,b,ans;
    static int res;
    static void solveL(char[] balls, int l, char target){
        res = 0;
        for(int i = 0; i < l; ++i){
            if(balls[i] != target){
                if(target == 'R'){
                    res = r-i;
                } else res = b-i;
                break;
            }
        }
        if(res < ans) ans = res;
    }

    static void solveR(char[] balls, int l, char target){
        res = 0;
        for(int i = l-1; i >= 0; --i){
            if(balls[i] != target){
                if(target == 'R'){
                    res = r-(l-1-i);
                } else res = b-(l-1-i);
                break;
            }
        }
        if(res < ans) ans = res;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int l = Integer.parseInt(br.readLine());
        char[] s = br.readLine().toCharArray();
        ans = Integer.MAX_VALUE;
        r = 0;
        b = 0;
        for(int i = 0; i < l; i++){
            if(s[i] == 'R') {
                r++;
            } else b++;
        }
        solveL(s, l, 'R');
        solveL(s, l, 'B');
        solveR(s, l, 'R');
        solveR(s, l, 'B');
        System.out.println(ans);
    }
}