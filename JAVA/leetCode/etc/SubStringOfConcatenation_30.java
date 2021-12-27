package category.ns.leetSolved;

import java.util.*;

public class SubStringOfConcatenation_30 {

    class Solution {
        Map<String, Integer> map;
        int wordLen, wordCnt, windowSize;
        String[] sub;
        public List<Integer> findSubstring(String s, String[] words) {
            List<Integer> ans = new ArrayList<>();
            map = new HashMap<>();
            for(int i = 0; i < words.length; i++)
                map.put(words[i], map.getOrDefault(words[i], 0)+1);

            wordLen = words[0].length();
            wordCnt = words.length;
            windowSize = wordCnt*wordLen;
            sub = new String[s.length()-wordLen+1];

            int iMax = s.length()-windowSize;
            for(int i = 0; i < sub.length; i++)
                sub[i] = s.substring(i, i+wordLen);
            for(int i = 0; i <= iMax; i++)
                solve(s, i, ans);

            return ans;
        }


        void solve(String s, int from, List<Integer> ans){
            Map<String, Integer> cntMap = new HashMap<>();
            int cnt = 0;
            for(int i = 0; i < wordCnt; i++){
                int idx = from+wordLen*i;
                String subString = sub[idx];
                if(map.containsKey(subString)){
                    cntMap.put(subString, cntMap.getOrDefault(subString, 0)+1);
                    int l,r;
                    l = cntMap.get(subString);
                    r = map.get(subString);
                    if(l == r) cnt++;
                    if(l > r) return;
                } else return;
            }
            if(cnt == map.size()) ans.add(from);
        }
    }

    /////////////////////////////////////////////////////////////
    class Fail {
        public List<Integer> findSubstring(String s, String[] words) {
            int k = words[0].length();
            List<Integer> ans = new ArrayList<>();
            Queue<Part> q = new LinkedList<>();

            Map<String, int[]> map = new HashMap<>();
            for(int i = 0; i < words.length; i++){
                if(!map.containsKey(words[i])) {
                    map.put(words[i], new int[2]);
                }
                map.get(words[i])[1]++;
            }



            int cnt = 0;
            for(int i = 0; i < s.length(); i++){
                int st = i;
                StringBuilder sb = new StringBuilder();
                for(; st < i+k; st++){
                    sb.append(s.charAt(st));
                }
                String cur = sb.toString();
                q.add(new Part(cur, i));
                if(map.containsKey(cur)){
                    if(map.get(cur)[0] == map.get(cur)[1]) cnt--;
                    map.get(cur)[0]++;
                    if(map.get(cur)[0] == map.get(cur)[1]) cnt++;

                }
                if(q.size() == words.length){
                    if(cnt == map.size()){
                        ans.add(q.peek().idx);
                    }

                    int[] tmp = map.get(q.peek().v);
                    if(map.containsKey(q.peek().v) && tmp[1] == tmp[0]--) cnt--;
                    if(tmp!=null && tmp[0] == tmp[1]) cnt++;
                    q.poll();
                }

                i = st-1;
            }
            return ans;
        }
        class Part{
            String v;
            int idx;
            Part (String v, int idx){
                this.idx = idx;
                this.v = v;
            }
        }
    }
}
