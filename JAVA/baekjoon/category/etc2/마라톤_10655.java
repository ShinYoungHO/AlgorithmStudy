package category.ns.baekjoonSol;

import java.io.*;
import java.util.*;

public class 마라톤_10655 {
    static int max,ans,n,cp[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        cp = new int[n][2];
        ans = 0;
        max = 0;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine()," ");
            cp[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }

        for(int i = 1; i < n; i++){
            int d = gmd(cp[i-1], cp[i]);
            ans += d;
            if(i != n-1){
                max = Math.max(max, d+gmd(cp[i], cp[i+1])-gmd(cp[i-1],cp[i+1]));
            }
        }
        System.out.println(ans-max);
    }

    static int gmd(int[] n1, int[] n2){
        return Math.abs(n2[0]-n1[0])+Math.abs(n2[1]-n1[1]);
    }
}
