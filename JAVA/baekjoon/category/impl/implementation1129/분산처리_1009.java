package category.ns.implementation1129;

import java.io.*;
import java.util.*;

public class 분산처리_1009 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int a,b;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine()," ");
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            sb.append(ret(a, b)).append("\n");
        }
        System.out.println(sb);
    }

    static int ret(int a, int b){
        List<Integer> arr = new ArrayList<>();
        int cur = a%10;
        int start = cur;
        arr.add(cur);

        while(true){
            cur = (cur*a)%10;
            if(cur == start) break;
            arr.add(cur);
        }
        System.out.println(arr);
        int ans = arr.get((b-1) % arr.size());
        return ans == 0 ? 10 : ans;
    }
}

