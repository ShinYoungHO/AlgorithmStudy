package hackerRank.kit.week1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatchingStrings {
    public static List<Integer> matchingStrings(List<String> strings, List<String> queries) {
        Map<String, Integer> map = new HashMap<>();
        List<Integer> res = new ArrayList<>();

        for(int i = 0; i < strings.size(); i++){
            String s = strings.get(i);
            if(!map.containsKey(s)) map.put(s, 0);

            map.put(s, map.get(s)+1);
        }

        for(int i = 0; i < queries.size(); i++){
            String query = queries.get(i);
            Integer v = map.get(query);
            if(v == null) res.add(0);
            else res.add(v);
        }

        return res;
    }
}
