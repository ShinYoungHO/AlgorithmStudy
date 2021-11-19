package hackerRank.kit.week2;

import java.util.List;

public class BalancedSum {
    class Result {
        String YES = "YES";
        String NO = "NO";
        public String balancedSums(List<Integer> arr) {
            int[] pre = new int[arr.size()+1];
            for(int i = 0; i < arr.size(); i++){
                pre[i+1] = pre[i]+arr.get(i);
            }

            int len = pre.length;
            for(int i = 0; i < len-1; i++){
                if(pre[i] == pre[len-1]-pre[i+1]) return YES;
            }

            return NO;
        }

    }
}
