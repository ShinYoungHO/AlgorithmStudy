package category.ns.programmersTMP;
import java.util.*;

public class 문자열압축_2 {
    class Solution {
        public int solution(String s) {
            int max = s.length()/2;
            int ans = 1001;
            for(int i = 1; i <= max; i++){
                int len = solve(s, i);
                ans = Math.min(ans, len);
            }

            return ans == 1001 ? s.length() : ans;
        }

        int solve(String s, int order){
            List<String> ps = getPartialString(order, s);
            StringBuilder sb = new StringBuilder();

            for(int i = 0; i < ps.size(); i++){
                String target = ps.get(i);
                int nxt = i;
                while(nxt+1<ps.size() && ps.get(nxt+1).equals(target)){
                    nxt++;
                }
                int v = nxt-i+1;
                if(v != 1) sb.append(v);
                sb.append(target);
                i = nxt;
            }
            return sb.toString().length();
        }

        List<String> getPartialString(int order, String s){
            List<String> ans = new ArrayList<>();
            for(int i = 0; i < s.length(); i++){
                StringBuilder sb = new StringBuilder();
                int start = i;
                int end = i+order;
                for(int j = start; j < end; j++){
                    if(j == s.length()) break;
                    sb.append(s.charAt(j));
                }
                ans.add(sb.toString());
                i = end-1;
            }
            return ans;
        }
    }
}
