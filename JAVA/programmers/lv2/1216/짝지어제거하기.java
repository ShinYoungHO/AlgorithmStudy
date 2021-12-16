package category.ns.programmersTMP;
import java.util.*;

public class 짝지어제거하기 {

    class Solution{
        public int solution(String s){
            Stack<Character> stack = new Stack<>();

            for(int i = 0; i < s.length(); i++){
                char c = s.charAt(i);
                if(stack.isEmpty()){
                    stack.push(c);
                } else {
                    if(stack.peek() == c) stack.pop();
                    else stack.push(c);
                }
            }
            return stack.size() > 0 ? 0 : 1;
        }
    }
}
