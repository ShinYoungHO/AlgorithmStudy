package category.ns.leetSolved;

import java.util.*;

public class LongestSubstring {
    class Solution {
        public int lengthOfLongestSubstring(String s) {
            Set<Character> set = new HashSet<>();
            int st,ed,ans;
            st = ed = ans = 0;

            while(true){
                while(ed < s.length() && !set.contains(s.charAt(ed))){
                    set.add(s.charAt(ed++));
                }
                ans = Math.max(ans, set.size());

                if(ed == s.length()) break;

                char c = s.charAt(ed);
                while(st < ed && s.charAt(st) != c){
                    set.remove(s.charAt(st++));
                }
                set.remove(s.charAt(st++));
            }
            return ans;
        }
    }
}
