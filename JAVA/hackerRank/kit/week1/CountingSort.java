package hackerRank.kit.week1;

import java.util.Arrays;
import java.util.List;

public class CountingSort {
    public static List<Integer> countingSort(List<Integer> arr) {
        Integer[] ans = new Integer[100];
        Arrays.fill(ans, 0);
        for(int i = 0; i < arr.size(); i++){
            ans[arr.get(i)]++;
        }

        return Arrays.asList(ans);
    }
}
