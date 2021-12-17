package category.ns.programmersTMP._lv2;
import java.util.regex.*;
import java.util.*;

public class 튜플 {

    class Solution {
        public int[] solution(String s) {
            Pattern pat = Pattern.compile("\\{(\\d|\\,| )+\\}");
            Matcher match = pat.matcher(s);
            Set<Integer> set = new HashSet<>();
            List<Integer> answer = new ArrayList<>();


            PriorityQueue<String> pq = new PriorityQueue<>((s1, s2) -> s1.length()-s2.length());

            while(match.find())
                pq.add(match.group());


            while(!pq.isEmpty()){
                String tmp = pq.poll();
                StringTokenizer st = new StringTokenizer(tmp, "{,}");
                while(st.hasMoreTokens()){
                    int v = Integer.parseInt(st.nextToken());
                    if(!set.contains(v)) {
                        set.add(v);
                        answer.add(v);
                        break;
                    }
                }
            }


            return answer.stream().mapToInt(v -> v).toArray();
        }
    }
}
