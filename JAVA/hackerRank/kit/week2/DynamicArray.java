package hackerRank.kit.week2;

import java.util.*;

public class DynamicArray {
    public static List<Integer> dynamicArray(int n, List<List<Integer>> queries) {
        List<Integer> result = new ArrayList<>();
        List[] mtx = new ArrayList[n];
        for(int i = 0; i < n; i++){
            mtx[i] = new ArrayList<>();
        }

        int lastAns = 0;
        int x,y,z;
        for(int i = 0; i < queries.size(); i++){
            x = queries.get(i).get(0);
            y = queries.get(i).get(1);
            z = queries.get(i).get(2);
            int idx = (y^lastAns)%n;

            if(x == 1){
                mtx[idx].add(z);
            } else {
                List<Integer> arr = mtx[idx];
                lastAns = arr.get(z%(mtx[idx].size()));
                result.add(lastAns);
            }

        }
        return result;

    }

}
