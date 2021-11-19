package hackerRank.kit.week3;

import java.util.List;
import java.util.Stack;

public class MinimumBribes {
    class Result {
        public void minimumBribes(List<Integer> q) {
            int ans = 0;
            Stack<Integer> stack = new Stack<>();
            Stack<Integer> tmp = new Stack<>();

            for(int i = q.size()-1; i >= 0; i--){
                int v = q.get(i);
                if(stack.isEmpty()) stack.push(v);
                else {
                    int cnt = 0;
                    if(stack.peek() > v) stack.push(v);
                    else {
                        while(!stack.isEmpty() && stack.peek() < v){
                            tmp.push(stack.pop());
                            cnt++;
                        }
                        if(cnt > 2) {
                            System.out.println("Too chaotic");
                            return;
                        }
                        stack.push(v);
                        while(!tmp.isEmpty()) stack.push(tmp.pop());
                        ans+=cnt;
                    }
                }
            }
            System.out.println(ans);
        }
    }
}
