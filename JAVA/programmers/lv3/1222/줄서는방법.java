package category.ns.programmersTMP;
import java.util.*;

public class 줄서는방법 {

    class Solution {
        public int[] solution(int n, long k) {
            List<Integer> li = new ArrayList<>();
            int[] ans = new int[n];
            long[] tmp = new long[n+1];
            tmp[0] = 1;



            for(int i = 1; i <= n; i++){
                li.add(i);
                tmp[i] = tmp[i-1]*i;
            }

            for(int i = n-1; i > 0; i--){
                long part = tmp[i];
                int blockOrder = (int)((k-1)/part);
                ans[n-1-i] = li.remove(blockOrder);
                k-=blockOrder*part;
            }

            ans[n-1] = li.remove(0);
            return ans;
        }
    }
}
