package category.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PadovanSeq {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        ArrayList<Long> dp = new ArrayList<>();
        long[] init = new long[]{1, 1, 1, 2, 2, 3, 4, 5, 7, 9};

        for(int i = 0; i < init.length; i++)
            dp.add(init[i]);

        long n = Long.parseLong(br.readLine());

        for(int i = 1; i <= n; i++){
            int v = Integer.parseInt(br.readLine())-1;

            if(v < dp.size()) sb.append(dp.get(v)).append("\n");
            else sb.append(increaseTo(v, dp)).append("\n");
        }
        System.out.println(sb);
    }

    static Long increaseTo(int n, ArrayList<Long> dp){
        int size;
        while((size = dp.size()) <= n){
            dp.add(dp.get(size-1)+dp.get(size-5));
        }
        return dp.get(dp.size()-1);
    }
}
