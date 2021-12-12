package category.ns.solved;

import java.io.*;
import java.util.*;


public class 초코칩케이크_23823 {
    static int n,q;
    static int[] row;
    static int[] col;
    static int rmc,cmc,rmv,cmv;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        row = new int[n];
        col = new int[n];

        rmc = 0;
        cmc = 0;

        int t,a,v;
        for(int i = 0; i < q; i++){
            st = new StringTokenizer(br.readLine(), " ");
            t = Integer.parseInt(st.nextToken())-1;
            a = Integer.parseInt(st.nextToken())-1;
            if(t == 0){
                v = ++row[a];
                if(v > rmv){
                    rmv = v;
                    rmc = 1;
                } else if(v == rmv){
                    rmc++;
                }
            } else {
                v = ++col[a];
                if(v > cmv){
                    cmv = v;
                    cmc = 1;
                } else if(v == cmv){
                    cmc++;
                }
            }

            int r = rmc==0?n:rmc;
            int c = cmc==0?n:cmc;
            sb.append(r*c).append("\n");
        }
        System.out.println(sb);
    }
}
