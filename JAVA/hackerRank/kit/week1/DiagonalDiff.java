package hackerRank.kit.week1;

import java.util.List;

public class DiagonalDiff {
    public static int diagonalDifference(List<List<Integer>> arr) {
        int r,c, min;
        r = arr.size();
        c = arr.get(0).size();
        min = r > c ? c : r;
        int left=0;
        int right=0;
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                if(i == j) left+=arr.get(i).get(j);
                if(i+j==min-1) right+=arr.get(i).get(j);
            }
        }

        return Math.abs(left-right);
    }
}
