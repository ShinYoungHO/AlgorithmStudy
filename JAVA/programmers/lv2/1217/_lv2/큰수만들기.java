package category.ns.programmersTMP._lv2;
import java.util.*;

public class 큰수만들기 {

    class Solution {
        public String solution(String number, int k) {
            Stack<Integer> st = new Stack<>();
            LinkedList<Integer> q = new LinkedList<>();
            StringBuilder sb = new StringBuilder();

            for(int i = 0; i < number.length(); i++){
                int v = number.charAt(i)-'0';
                if(st.isEmpty()||st.peek()>=v) st.push(v);
                else {
                    while(!st.isEmpty() && st.peek() < v && k > 0) {
                        st.pop();
                        k--;
                    }
                    st.push(v);
                }
            }
            while(!st.isEmpty()){
                int v = st.pop();
                if(k-- > 0) continue;
                q.addFirst(v);
            }
            while(!q.isEmpty()){
                sb.append(String.valueOf(q.pollFirst()));
            }

            return sb.toString();
        }
    }
}
