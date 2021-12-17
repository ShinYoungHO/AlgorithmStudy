package category.ns.programmersTMP._lv2;
import java.util.*;

public class 영어끝말잇기 {

    class Solution {
        public int[] solution(int n, String[] words) {
            Set<String> set = new HashSet<>();
            int[] answer = new int[2];

            String prev = null;
            for(int i = 0; i < words.length; i++){
                String cur = words[i];
                if(set.contains(cur) ||
                        ((prev != null) && (prev.charAt(prev.length()-1)) != cur.charAt(0))
                ){
                    answer[0] = i%n+1;
                    answer[1] = i/n+1;
                    break;
                }
                set.add(cur);
                prev = cur;
            }
            return answer;
        }
    }
}
