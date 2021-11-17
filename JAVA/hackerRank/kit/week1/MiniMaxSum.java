package hackerRank.kit.week1;

import java.util.Collections;
import java.util.List;

public class MiniMaxSum {
    public static void miniMaxSum(List<Integer> arr) {
        long sum = 0;
        Collections.sort(arr);
        for(int i = 0; i < arr.size(); i++){
            long v = (long)arr.get(i);
            sum+=v;
        }
        System.out.println((sum-arr.get(4))+" "+(sum-arr.get(0)));
    }
}
