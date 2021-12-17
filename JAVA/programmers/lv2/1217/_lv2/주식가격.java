package category.ns.programmersTMP._lv2;
import java.util.*;


public class 주식가격 {

    class Solution {
        public int[] solution(int[] prices) {
            int[] ans = new int[prices.length];

            Stack<int[]> st = new Stack<>();

            for(int i = 0; i < ans.length; i++)
                ans[i] = ans.length-1-i;


            for(int i = 0; i < prices.length; i++){
                int v = prices[i];
                if(st.isEmpty() || st.peek()[1] <= v) st.push(new int[]{i, v});
                else {
                    while(!st.isEmpty() && st.peek()[1] > v){
                        int[] pop = st.pop();
                        ans[pop[0]] = i - pop[0];
                    }
                    st.push(new int[]{i, v});
                }
            }

            return ans;
        }
    }
}
