package hackerRank.kit.week1;

import java.util.*;

public class LonelyInt {
    public static int lonelyinteger(List<Integer> a) {
        Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();

        for(int i = 0; i < a.size(); i++){
            int v = a.get(i);
            if(!map.containsKey(v)){
                map.put(v, 1);
                set.add(v);
            } else {
                map.put(v, map.get(v)+1);
                if(set.contains(v)) set.remove(v);
            }
        }

        Iterator<Integer> it = set.iterator();
        return it.next();
    }
}
