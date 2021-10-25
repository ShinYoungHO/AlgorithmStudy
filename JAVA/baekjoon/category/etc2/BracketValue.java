package category.etc2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Stack;

public class BracketValue {
    static Map<Integer, Integer> map = Map.of('('-'0',')'-'0','['-'0',']'-'0',')'-'0','('-'0',']'-'0','['-'0');
    static int solve(int start, int end, String input, int[] pair){
        int k = input.charAt(start) == '(' ? 2 : 3;
        if(start+1 == end){
            return k;
        }
        int res = 0;
        for(int i = start+1; i <= end-1; i++){
            if(pair[i] == 0) continue;
            res+=k*solve(i, pair[i], input, pair);
            i = pair[i];
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int res = 0;
        int len = input.length();

        Stack<int[]> stack = new Stack<>();
        int[] pair = new int[len];

        for(int i = 0; i < len; i++){
            int c = input.charAt(i)-'0';
            if(stack.isEmpty()){
                stack.push(new int[]{c, i});
                continue;
            }
            if(map.get(stack.peek()[0]) == c){
                int v = stack.pop()[1];
                pair[v] = i;
            } else {
                stack.push(new int[]{c, i});
            }
        }

        if(!stack.isEmpty()){
            System.out.println(0);
            return;
        }

        for(int i = 0; i < len; i++){
            res+=solve(i, pair[i],input,pair);
            i=pair[i];
        }
        System.out.println(res);
    }
}
