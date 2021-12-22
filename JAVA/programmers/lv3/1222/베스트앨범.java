package category.ns.programmersTMP;
import java.util.*;

public class 베스트앨범 {

    class Solution {
        public int[] solution(String[] genres, int[] plays) {
            int l = genres.length;
            Map<String, Genre> map = new HashMap<>();
            List<Genre> tmp = new ArrayList<>();

            for(int i = 0; i < l; i++){
                int[] music = new int[]{i, plays[i]};
                String genreName = genres[i];
                if(!map.containsKey(genreName)) map.put(genreName, new Genre(genreName));
                map.get(genreName).addToList(music);
            }

            int len = 0;

            for(String key : map.keySet()){
                Genre g = map.get(key);
                tmp.add(g);
                len += g.list.size() >= 2 ? 2 : g.list.size();
            }
            Collections.sort(tmp, (n1, n2) -> n2.total - n1.total);

            int[] ans = new int[len];
            int idx = 0;
            for(int i = 0; i < tmp.size(); i++){
                Genre g = tmp.get(i);
                for(int j = 0; j < 2; j++){
                    if(g.list.size() == 0) break;
                    ans[idx++]=g.list.poll()[0];
                }
            }


            return ans;
        }
    }

    class Genre{
        PriorityQueue<int[]> list = new PriorityQueue<>((n1, n2) -> n2[1] != n1[1] ? n2[1]-n1[1] : n1[0] - n2[0]);
        String genreName;
        int total = 0;

        Genre(String s){
            this.genreName = s;
        }

        void addToList(int[] music){
            list.add(music);
            total += music[1];
        }

    }
}
