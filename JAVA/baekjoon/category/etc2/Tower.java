package category.etc2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Tower {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int[] res = new int[n+1];
        Stack<int[]> stack = new Stack<>();
        for(int i = 1; i <= n; i++){
            int cur = Integer.parseInt(st.nextToken());
            if(stack.isEmpty()){
                stack.push(new int[]{cur, i});
                continue;
            }
            int[] top = stack.peek();
            if(top[0] >= cur){
                res[i] = top[1];
                stack.push(new int[]{cur, i});
            } else {
                while(!stack.isEmpty() && stack.peek()[0] < cur){
                    stack.pop();
                }
                if(!stack.isEmpty()){
                    res[i] = stack.peek()[1];
                }
                stack.push(new int[]{cur, i});
            }
        }

        for(int i = 1; i <= n; i++){
            sb.append(res[i]).append(" ");
        }
        System.out.println(sb);
    }
}

// 10 6 9 5 7 4
