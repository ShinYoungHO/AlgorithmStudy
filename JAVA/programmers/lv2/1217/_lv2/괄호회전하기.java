package category.ns.programmersTMP._lv2;
import java.util.*;

public class 괄호회전하기 {

    class Solution {
        public int solution(String s) {
            int answer = 0;
            Stack<Character> st;
            for(int rot = 0; rot < s.length(); rot++){
                st = new Stack<>();
                for(int i = rot; i < rot+s.length(); i++){
                    char c = s.charAt(i%s.length());
                    if(st.isEmpty()) st.push(c);
                    else {
                        char nc = st.peek();
                        if((nc == '[' && c == ']')||(nc == '{' && c == '}')||(nc == '(' && c == ')')){
                            st.pop();
                        } else {
                            st.push(c);
                        }
                    }
                }
                if(st.isEmpty()) answer++;
            }
            return answer;
        }
    }
}
