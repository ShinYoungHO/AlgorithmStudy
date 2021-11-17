package hackerRank.kit.week1;

import java.util.List;

public class PlusMinus {
    public static void plusMinus(List<Integer> arr) {
        double n = (double) arr.size();
        double p,q,r;
        p=q=r=0;
        for(int i = 0; i < arr.size(); i++){
            int e = arr.get(i);
            if(e > 0) p++;
            else if(e < 0) q++;
            else r++;
        }
        System.out.println(p/n);
        System.out.println(q/n);
        System.out.println(r/n);
    }

    /**
     *
     */
}
