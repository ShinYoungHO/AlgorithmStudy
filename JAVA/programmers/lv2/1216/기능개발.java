package category.ns.programmersTMP;
import java.util.*;

public class 기능개발 {

    class Solution {
        public int[] solution(int[] progresses, int[] speeds) {
            int len = speeds.length;
            List<Integer> ans = new ArrayList<>();
            Queue<Integer> q = new LinkedList<>();

            for(int i = 0; i < len; i++){
                int v = 100-progresses[i];
                int b = speeds[i];
                q.add(v%b != 0 ? v/b+1 : v/b);
            }


            while(!q.isEmpty()){
                int v = q.poll();
                int cnt = 1;
                while(!q.isEmpty() && q.peek() <= v){
                    q.poll();
                    cnt++;
                }
                ans.add(cnt);

            }
            return ans.stream().mapToInt(i->i).toArray();
        }
    }
}
