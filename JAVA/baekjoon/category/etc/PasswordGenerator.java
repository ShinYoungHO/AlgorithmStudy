package category.etc;

import java.io.*;
import java.util.*;

public class PasswordGenerator {
    static Set<Character> set;
    static int l,c;
    static char[] candi, result;
    static StringBuilder sb;
    static void solve(int start, int idx, int d, int k){
        if(idx == l){
            if(k >= 1 && d >= 2){
                for(int i = 0; i < l; i++){
                    sb.append(result[i]);
                }
                sb.append("\n");
            }
            return;
        }
        for(int i = start; i < c; i++){
            result[idx] = candi[i];
            if(set.contains(candi[i])){
                solve(i+1, idx+1, d, k+1);
            } else {
                solve(i+1, idx+1, d+1, k);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        set = new HashSet<>();

        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        candi = new char[c];
        result = new char[l];
        sb = new StringBuilder();
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < c; i++){
            char d = st.nextToken().charAt(0);
            if(d == 'a' || d == 'e' || d == 'i' || d == 'o' || d == 'u') set.add(d);
            candi[i] = d;
        }
        Arrays.sort(candi);
        solve(0,0,0, 0);
        System.out.println(sb);
    }
}
