package category.etc2;

import java.io.*;
import java.util.*;

public class CombineNums {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int res = 0;

        ArrayList<Integer> minus,plus;
        minus = new ArrayList<>();
        plus = new ArrayList<>();


        for(int i = 0; i < n; i++){
            int k = Integer.parseInt(br.readLine());
            if(k > 0) plus.add(k);
            else minus.add(k);
        }

        Collections.sort(minus);
        Collections.sort(plus);

        for(int i = plus.size()-1; i >= 0 ; i--){
            int v = plus.get(i);
            if(i != 0){
                int nxt = plus.get(i-1);
                res+=Math.max(nxt+v,nxt*v);
                i--;
            } else {
                res+=v;
            }
        }

        int mIdx = minus.size()-1;
        for(int i = 0; i <= mIdx; i++){
            int v = minus.get(i);
            if(i != mIdx){
                res+=minus.get(++i)*v;
            } else {
                res+=v;
            }
        }
        System.out.println(res);
    }
}
