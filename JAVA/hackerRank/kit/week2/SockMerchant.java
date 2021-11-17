package hackerRank.kit.week2;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SockMerchant {
    public static int sockMerchant(int n, List<Integer> ar) {
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < ar.size(); i++){
            int v = ar.get(i);
            if(set.contains(v)) set.remove(v);
            else set.add(v);
        }
        return (n-set.size())/2;
    }
}
